/*
 * Class Name:  MealService
 * Description: Service
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.service;


import org.merrymeal.mow.entity.Profile;
import org.merrymeal.mow.entity.Menu;
import org.merrymeal.mow.entity.Meal;
import org.merrymeal.mow.entity.Pickup;
import org.merrymeal.mow.entity.Feedback;

import org.merrymeal.mow.model.SelectWrapper;

import org.merrymeal.mow.repository.ProfileRepository;
import org.merrymeal.mow.repository.MealRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
  // Note: Replacing java.sql.Timestamp;

import java.awt.print.PrinterJob;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import java.net.URLEncoder;
import java.net.URL;
import java.net.HttpURLConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.InputStream;

import org.json.JSONObject;
  // Note: Java JSON requires java-json.jar (Java JSON)
  //       added into the User Library added into the Java Build Path under the Project Properties
  // Update: Switched to Maven dependencies for WAR packaging since exported runnable JAR will not work with Spring Boot


@Service
@Transactional

public class MealService {



  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private MealRepository    mealRepository;



  // Note: Following services for Meals controller


  public List<Meal> findMeals(String diet, String username) {

    Profile profile = new Profile();

    List<Meal> meals = new ArrayList<>();

    profile = profileRepository.findByEmail(username);

    if (profile.getRole() != null && profile.getRole().equals("Administrator")) {

      meals = mealRepository.findByDiet(diet);

    } else if (profile.getRole() != null && profile.getRole().equals("Member")) {

      meals = mealRepository.findByDietAndMember_Id(diet, profile.getId());

    } else if (profile.getRole() != null) {

      meals = mealRepository.findByDietAndProvider_Id(diet, profile.getId());

    }
 
    return meals;

  }

  public Page<Meal> findMeals(String diet, String username, int page, int size) {

    Profile profile = new Profile();

    Page<Meal> items = null;

    profile = profileRepository.findByEmail(username);

    if (profile.getRole() != null && profile.getRole().equals("Administrator")) {

      items = mealRepository.findByDiet(diet, PageRequest.of(page,  size));

    } else if (profile.getRole() != null && profile.getRole().equals("Member")) {

      items = mealRepository.findByDietAndMember_Id(diet, profile.getId(), PageRequest.of(page,  size));

    } else if (profile.getRole() != null) {

      items = mealRepository.findByDietAndProvider_Id(diet, profile.getId(), PageRequest.of(page,  size));

    }
 
    return items;

  }


  public List<Meal> generateMeals(String diet, String username) {

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    double[] providerCoordinates = new double[2];
    double[] memberCoordinates   = new double[2];

    List<Profile> members = new ArrayList<>();

    List<String> roles    = new ArrayList<>();
    roles.add("Member");

//  File uenFile          = null;
//  Scanner uenScanner    = null;
    InputStream uenStream = null;
    String uenLine        = null;

    String uen            = null;

    final String UEN      = "200202841R";

/********************************************************************************/
    final LocalDateTime TODAY = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
/********************************************************************************
    final LocalDateTime TODAY = LocalDateTime.of(2025, 06, 13, 06, 13, 0, 0);
      // Note: Day of Week = Fri
      // Note: For testing only
 ********************************************************************************/

    // Note: Try scanning uen file

    try {

//    uenFile     = new ClassPathResource("config/uen.txt").getFile();
//    uenScanner  = new Scanner(uenFile);

//    if (uenScanner.hasNextLine()) {
//      uenLine   = uenScanner.nextLine();
//    }

      uenStream = new ClassPathResource("config/uen.txt").getInputStream();
        // Note: Switched to this technique which also works for WAR packaging

      uenLine   = new String(uenStream.readAllBytes());
        // Note: Switched to this technique which also works for WAR packaging 

      System.out.println("Scanned uen.txt in config: " + uenLine);

//    uenScanner.close();

    } catch(Exception e) {

      System.out.println(e.getMessage());

//    if (uenScanner != null) {
//      uenScanner.close();
//    }

    }

    // Note: If not successful use default MerryMeal's UEN
    //                    else use overriding MerryMeal's UEN 

    if (uenLine == null || uenLine.equals("")) {
      uen = UEN;
    } else {
      uen = uenLine;
    }

    profile = profileRepository.findByEmail(username);

    if (profile.getRole() != null && !profile.getRole().equals("Member")) {

      // Note: Get existing non-ended meals involving provider

      meals = mealRepository.findByDietAndProvider_Id(diet, profile.getId());

      // Note: Get location of provider

      providerCoordinates = this.getLatitudeLongitude(profile.getAddress() + " " + "Singapore");

      // Note: Find active members with matching diet sorted from oldest to youngest

      members = profileRepository.findByDietAndStatusAndRoleIn(diet, "Active", roles, Sort.by(Sort.Direction.ASC, "dob"));

      if (members != null) {

        for (Profile member : members) {

          // Note: Get location of member

          memberCoordinates = this.getLatitudeLongitude(member.getAddress() + " " + "Singapore");
    	
          // Note: Eligible to generate meal for non-MerryMeal provider if today is weekday and distance between locations <= 10.0 km
          //       Eligible to generate meal for MerryMeal     provider if today is weekend or  meal not provided by other providers

          if (!profile.getNric_uen().equals(uen) && (providerCoordinates != null && memberCoordinates != null)) {

            if ((!TODAY.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !TODAY.getDayOfWeek().equals(DayOfWeek.SUNDAY))
              && (this.getDistance(providerCoordinates[0], providerCoordinates[1], memberCoordinates[0], memberCoordinates[1]) <= 10.0)) {

              // Note: Generate meal if this non-ended meal does not exist

              if (mealRepository.findByDietAndMember_Id(diet, member.getId()).isEmpty()) {

                Meal meal = new Meal();

                meal.setId(0);

                meal.setTime_avail(null);
                meal.setTime_start(null);
                meal.setTime_end(null);

                meal.setStatus("Generated");

                if (diet.equals("Halal")) {
                  meal.setMenu(profile.getMenu_halal());
                } else if (diet.equals("Vegetarian")) {
        	      meal.setMenu(profile.getMenu_veg());
                } else if (diet.equals("Soft")) {
                  meal.setMenu(profile.getMenu_soft());
                } else if (diet.equals("Normal")) {
                  meal.setMenu(profile.getMenu_normal());
                } else {
        	      meal.setMenu(null);
                }

                meal.setProvider(profile);

                meal.setMember(member);

                meal.setPickup(null);

                if (meal.getMenu() != null) {
                  meal = mealRepository.saveAndFlush(meal);
                  meals.add(meal);
                }

              }

            }

          } else if (profile.getNric_uen().equals(uen) && (memberCoordinates != null)) {

            List<Profile> otherProviders = new ArrayList<>();

            List<String> otherRoles = new ArrayList<>();
            otherRoles.add("Partner - FSP");
            otherRoles.add("Volunteer - FSP");

            double[] otherProviderCoordinates = new double[2];

            boolean mealProvided = false;

            // Note: Check whether meal provided by other providers

            otherProviders = profileRepository.findByStatusAndRoleIn("Active", otherRoles, Sort.by(Sort.Direction.ASC, "names"));

            if (otherProviders != null) {

              for (Profile otherProvider : otherProviders) {

                otherProviderCoordinates = this.getLatitudeLongitude(otherProvider.getAddress() + " " + "Singapore");

                if (!otherProvider.getNric_uen().equals(uen) && (otherProviderCoordinates != null)
                  && ((!TODAY.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !TODAY.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                   && (this.getDistance(otherProviderCoordinates[0], otherProviderCoordinates[1], memberCoordinates[0], memberCoordinates[1]) <= 10.0))) {

                  mealProvided = true;
                  break;

                }

              }

            }

            if ((TODAY.getDayOfWeek().equals(DayOfWeek.SATURDAY) || TODAY.getDayOfWeek().equals(DayOfWeek.SUNDAY))
              || (mealProvided == false)) {

              // Note: Generate meal if this non-ended meal does not exist

              if (mealRepository.findByDietAndMember_Id(diet, member.getId()).isEmpty()) {

                Meal meal = new Meal();

                meal.setId(0);

                meal.setTime_avail(null);
                meal.setTime_start(null);
                meal.setTime_end(null);

                meal.setStatus("Generated");

                if (diet.equals("Halal")) {
                  meal.setMenu(profile.getMenu_halal());
                } else if (diet.equals("Vegetarian")) {
                  meal.setMenu(profile.getMenu_veg());
                } else if (diet.equals("Soft")) {
                  meal.setMenu(profile.getMenu_soft());
                } else if (diet.equals("Normal")) {
                  meal.setMenu(profile.getMenu_normal());
                } else {
                  meal.setMenu(null);
                }

                meal.setProvider(profile);

                meal.setMember(member);

                meal.setPickup(null);

                if (meal.getMenu() != null) {
                  meal = mealRepository.saveAndFlush(meal);
                  meals.add(meal);
                }

              }

            }

          }

        }

      }

    } else if (profile.getRole() != null){

      // Note: Get existing non-ended meals involving member

      meals = mealRepository.findByDietAndMember_Id(diet, profile.getId());

    }

    return meals;

  }


  public List<Meal> updateMeals(SelectWrapper selectWrapper, String status) {

    List<String> ids = new ArrayList<>();

    Meal meal = new Meal();

    List<Meal> meals = new ArrayList<>();

    ids = selectWrapper.getIds();

    if (ids != null) {

      for (String id: ids) {

        meal = mealRepository.findById(Integer.parseInt(id));
        meal.setStatus(status);

/********************************************************************************/
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
/********************************************************************************
        LocalDateTime localDateTime = LocalDateTime.of(2025, 06, 13, 06, 13, 0, 0);
          // Note: Day of Week = Fri
          // Note: For testing only
 ********************************************************************************/

/********************************************************************************
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedLocalDateTime = localDateTime.format(formatter);
          // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/

        if (meal.getStatus() != null && meal.getStatus().equals("Generated")) {
          meal.setTime_avail(null);
          meal.setTime_start(null);
          meal.setTime_end(null);
        } else if (meal.getStatus() != null && meal.getStatus().equals("Available")) {
/********************************************************************************
          meal.setTime_avail(formattedLocalDateTime);
          meal.setTime_start(null);
          meal.setTime_end(null);
            // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/
          meal.setTime_avail(localDateTime);
          meal.setTime_start(null);
          meal.setTime_end(null);
            // Note: Using String data type instead is a temporary workaround
        } else if (meal.getStatus() != null && meal.getStatus().equals("Started")) {
/********************************************************************************
          meal.setTime_start(formattedLocalDateTime);
          meal.setTime_end(null);
          // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
********************************************************************************/
        meal.setTime_start(localDateTime);
        meal.setTime_end(null);
          // Note: Using String data type instead is a temporary workaround
        } else if (meal.getStatus() != null && meal.getStatus().equals("Ended")) {
/********************************************************************************
        meal.setTime_end(formattedLocalDateTime);
          // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
********************************************************************************/
        meal.setTime_end(localDateTime);
          // Note: Using String data type instead is a temporary workaround
        }

        meal = mealRepository.saveAndFlush(meal);
        meals.add(meal);

      }

    }

    return meals;

  }


  public List<Meal> printMeals(SelectWrapper selectWrapper, String username) {

    final int FIELD_WIDTH   = 20;
      // Note: To amend according to printer (minimum: 20 characters).

    final int LABEL_START_X = 10;
    final int LABEL_START_Y = 10;
      // Note: To amend coordinates according to printer.

    Profile provider = new Profile();

    List<String> ids = new ArrayList<>();

    Meal meal = new Meal();

    List<Meal> meals = new ArrayList<>();

    provider = profileRepository.findByEmail(username);

    ids = selectWrapper.getIds();

    if (ids != null) {

      for (String id: ids) {

        meal = mealRepository.findById(Integer.parseInt(id));

        String content = String.format("%s\n%s\n%s\n%s\n%s\n",
                                       "Diet:      " + meal.getMenu().getDiet(),
                                       "Allergies: " + meal.getMember().getAllergies().substring(0, FIELD_WIDTH),
                                       "NRIC:      " + meal.getMember().getNric_uen(),
                                       "Frozen:    " + meal.getMenu().getFrozen(),
                                       "Available: " + meal.getTime_end());

        try {

          PrinterJob printerJob = PrinterJob.getPrinterJob();
          printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
              return Printable.NO_SUCH_PAGE;
            }
            graphics.drawString(content, LABEL_START_X, LABEL_START_Y);
            return Printable.PAGE_EXISTS;
          });

          PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
          printerJob.setPrintService(printServices[Integer.parseInt(provider.getRegion())]);

          printerJob.print();

          System.out.println(String.format("%s %s.\n",    "Label printing successful for meal", meal.getId()));

        } catch(PrinterException e) {

          System.out.println(String.format("%s %s: %s\n", "Label printing failed for meal",     meal.getId(), e.getMessage()));

        }

        meals.add(meal);

      }

    }

    return meals;

  }


  // Note: Following services for Home controller


  public long countMealsOpen() {

    long count = 0;

    List<String> statuses = new ArrayList<>();
    statuses.add("Generated");
    statuses.add("Available");
    statuses.add("Started");

    count = mealRepository.countByStatusIn(statuses);

    return count;

  }


  public long countMealsClosed() {

    long count = 0;

    List<String> statuses = new ArrayList<>();
    statuses.add("Ended");

    count = mealRepository.countByStatusIn(statuses);

    return count;

  }



  // Note: Following methods within Meal service


  private double[] getLatitudeLongitude(String address) {

//  File apiKeyFile          = null;
//  Scanner apiKeyScanner    = null;
    InputStream apiKeyStream = null;
    String apiKeyLine        = null;

    String api_key           = null;

    final String API_KEY     = "placeholder";

    // Note: Try scanning api_key file

    try {

//    apiKeyFile     = new ClassPathResource("config/api_key.txt").getFile();
//    apiKeyScanner  = new Scanner(apiKeyFile);

//    if (apiKeyScanner.hasNextLine()) {
//      apiKeyLine   = apiKeyScanner.nextLine();
//    }

      apiKeyStream = new ClassPathResource("config/api_key.txt").getInputStream();
        // Note: Switched to this technique which also works for WAR packaging

      apiKeyLine   = new String(apiKeyStream.readAllBytes());
        // Note: Switched to this technique which also works for WAR packaging 

      System.out.println("Scanned api_key.txt in config: " + apiKeyLine);

//    apiKeyScanner.close();

    } catch(Exception e) {

      System.out.println(e.getMessage());

//    if (apiKeyScanner != null) {
//      apiKeyScanner.close();
//    }

    }

    // Note: If not successful use default API Key
    //                    else use overriding API Key 

    if (apiKeyLine == null || apiKeyLine.equals("")) {
      api_key = API_KEY;
    } else {
      api_key = apiKeyLine;
    }

    try {

      final String ENCODED_ADDRESS = URLEncoder.encode(address, "UTF-8");

      // Note: Construct the URL for calling the Geocoding API provided by Google Maps

      URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json" + "?" + "address=" + ENCODED_ADDRESS + "&" + "key=" + api_key);

      // Note: Open URL's HTTP connection and set its request method

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      // Note: Get the HTTP JSON response

      BufferedReader reader        = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line                  = null;
      StringBuilder stringResponse = new StringBuilder();

      while ((line = reader.readLine()) != null) {
        stringResponse.append(line);
      }

      reader.close();

      // Note: Parse the HTTP JSON response

      JSONObject jsonResponse = new JSONObject(stringResponse.toString());

      if (jsonResponse.getString("status").equals("OK")) {
    	  
        JSONObject jsonLocation = jsonResponse.getJSONArray("results")
                                              .getJSONObject(0)
                                              .getJSONObject("geometry")
                                              .getJSONObject("location");

        double latitude  = jsonLocation.getDouble("lat");
        double longitude = jsonLocation.getDouble("lng");

        return new double[] {latitude, longitude};

      } else {

        return null;

      }

    } catch(Exception e) {

      return null;

    }

  }


  private double getDistance(double lat1, double long1,
		                     double lat2, double long2) {

    // Note: Get differences between latitudes and longitudes in radians
    double latDifferenceRadians  = Math.toRadians(lat2  - lat1);
    double longDifferenceRadians = Math.toRadians(long2 - long1);

    // Note: Convert latitudes to radians
    double lat1Radians = Math.toRadians(lat1);
    double lat2Radians = Math.toRadians(lat2);

    // Note: Get Haversine of central angle which is between 0 and 1 (Step 1 of Haversine Formula)
    double centralAngleHaversine = Math.pow(Math.sin(latDifferenceRadians  / 2.0), 2.0)
	                             + Math.pow(Math.sin(longDifferenceRadians / 2.0), 2.0) * Math.cos(lat1Radians) * Math.cos(lat2Radians);

    // Note: Get great-circle distance between both locations in km (Step 2 of Haversine Formula)
    double averageEarthRadiusKm  = 6371.2;
    double greatCircleDistanceKm = 2.0 * Math.asin(Math.sqrt(centralAngleHaversine)) * averageEarthRadiusKm;  

    return greatCircleDistanceKm;

  }



}
