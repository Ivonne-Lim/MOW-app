/*
 * Class Name:  PickupService
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
import org.merrymeal.mow.repository.PickupRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
  // Note: Replace java.sql.Timestamp;


@Service
@Transactional

public class PickupService {



  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private MealRepository    mealRepository;

  @Autowired
  private PickupRepository  pickupRepository;



  // Note: Following services for Pickups controller


  public List<Meal> findMealsAvailable(String username) {

	Profile rider    = new Profile();

	Profile provider = new Profile();

    List<Meal> meals = new ArrayList<>();

    rider = profileRepository.findByEmail(username);

    provider = rider.getProvider();

    if (provider != null) {
      meals = mealRepository.findByStatusAndPickupAndProvider_Id("Ended", provider.getId());
        // Note: Find meals with "Ended" status and without pickup and with matching provider_id
    }
    
    return meals;
  
  }

  public Page<Meal> findMealsAvailable(String username, int page, int size) {

	Profile rider    = new Profile();

	Profile provider = new Profile();

    Page<Meal> items = null;

    rider = profileRepository.findByEmail(username);

    provider = rider.getProvider();

    if (provider != null) {
      items = mealRepository.findByStatusAndPickupAndProvider_Id("Ended", provider.getId(), PageRequest.of(page,  size));
        // Note: Find meals with "Ended" status and without pickup and with matching provider_id
    }
    
    return items;
  
  }


  public List<Meal> findMealsAvailable() {

    List<Meal> meals = new ArrayList<>();

    meals = mealRepository.findByStatusAndPickup("Ended");
      // Note: Find meals with "Ended" status and without pickup
    
    return meals;
  
  }

  public Page<Meal> findMealsAvailable(int page, int size) {

	Page<Meal> items = null;

    items = mealRepository.findByStatusAndPickup("Ended", PageRequest.of(page, size));
      // Note: Find meals with "Ended" status and without pickup
	    
    return items;
	  
  }


  public List<Pickup> findPickupsCurrentByRider(String username) {

    Profile rider = new Profile();

    List<String> statuses = new ArrayList<>();
    statuses.add("Accepted");
    statuses.add("Started");

    List<Pickup> pickups = new ArrayList<>();

    rider   = profileRepository.findByEmail(username);

    pickups = pickupRepository.findByRider_IdAndStatusIn(rider.getId(), statuses);

    return pickups;

  }

  public Page<Pickup> findPickupsCurrentByRider(String username, int page, int size) {

    Profile rider = new Profile();

    List<String> statuses = new ArrayList<>();
    statuses.add("Accepted");
    statuses.add("Started");

	Page<Pickup> items = null;

    rider   = profileRepository.findByEmail(username);

    items = pickupRepository.findByRider_IdAndStatusIn(rider.getId(), statuses, PageRequest.of(page, size));

    return items;

  }


  public List<Pickup> findPickupsCurrentByMember(String username) {

    Profile member = new Profile();

    List<String> statuses = new ArrayList<>();
    statuses.add("Accepted");
    statuses.add("Started");

    List<Pickup> pickups = new ArrayList<>();

    member  = profileRepository.findByEmail(username);

    pickups = pickupRepository.findByMember_IdAndStatusIn(member.getId(), statuses);

    return pickups;

  }

  public Page<Pickup> findPickupsCurrentByMember(String username, int page, int size) {

    Profile member = new Profile();

    List<String> statuses = new ArrayList<>();
    statuses.add("Accepted");
    statuses.add("Started");

	Page<Pickup> items = null;

    member  = profileRepository.findByEmail(username);

    items = pickupRepository.findByMember_IdAndStatusIn(member.getId(), statuses, PageRequest.of(page, size));

    return items;

  }


  public List<Pickup> generatePickups(SelectWrapper selectWrapper, String username) {

    List<String> ids     = new ArrayList<>();

    Profile rider = new Profile();

    List<Pickup> pickups = new ArrayList<>();

    ids = selectWrapper.getIds();

    rider = profileRepository.findByEmail(username);

    if (ids != null) {

      for (String id: ids) {

        Meal meal     = new Meal();

        Pickup pickup = new Pickup();

        meal = mealRepository.findById(Integer.parseInt(id));

        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

/********************************************************************************
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedLocalDateTime = localDateTime.format(formatter);
          // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/

/********************************************************************************
        pickup.setTime_accept(formattedLocalDateTime);
           // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/
        pickup.setTime_accept(localDateTime);
          // Note: Using String data type instead is a temporary workaround

        pickup.setStatus("Accepted");

        pickup.setRider(rider);
        pickup.setMeal(meal);

        pickup = pickupRepository.saveAndFlush(pickup);
        pickups.add(pickup);

      }

    }

    return pickups;
	  
  }


  public Pickup findPickupById(String id) {

    Pickup pickup = new Pickup();

    pickup = pickupRepository.findById(Integer.parseInt(id));

    return pickup;

  }


  public Pickup savePickup(Pickup pickup, String rider_id, String meal_id) {

    Profile rider = new Profile();

    Meal meal     = new Meal();

    rider = profileRepository.findById(Integer.parseInt(rider_id));
    pickup.setRider(rider);

    meal  = mealRepository.findById(Integer.parseInt(meal_id));
    pickup.setMeal(meal);

    LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
 
/********************************************************************************
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedLocalDateTime = localDateTime.format(formatter);
      // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/

    if (pickup.getStatus() != null && pickup.getStatus().equals("Ended")) {
/********************************************************************************
   	  pickup.setTime_end(formattedLocalDateTime);
        // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/
      pickup.setTime_end(localDateTime);
      // Note: Using String data type instead is a temporary workaround
    } else if (pickup.getStatus() != null && pickup.getStatus().equals("Started")) {
/********************************************************************************
      pickup.setTime_start(formattedLocalDateTime);
        // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/
      pickup.setTime_start(localDateTime);
        // Note: Using String data type instead is a temporary workaround
    } else if (pickup.getStatus() != null && pickup.getStatus().equals("Accepted")) {
/********************************************************************************
      pickup.setTime_start(null);
      pickup.setTime_accept(formattedLocalDateTime);
        // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/
      pickup.setTime_start(null);
      pickup.setTime_accept(localDateTime);
        // Note: Using String data type instead is a temporary workaround
    }

    pickup = pickupRepository.saveAndFlush(pickup);

    return pickup;

  }


  // Note: Following services for Feedback controller

  
  public List<Pickup> findDeliveries(String username) {

    Profile member = new Profile();

    List<Pickup> deliveries = new ArrayList<>();

    List<String> statuses   = new ArrayList<>();
    statuses.add("Ended");

    member = profileRepository.findByEmail(username);

    deliveries = pickupRepository.findByMember_IdAndStatusIn(member.getId(), statuses);

    return deliveries;

  }


  // Note: Following services for Home controller

  
  public long countMealsAvailable() {

    long count = 0;

    count = mealRepository.countByStatusAndPickup("Ended");
      // Note: Count meals with "Ended" status and without pickup

    return count;

  }


  public long countPickupsOpen() {

    long count = 0;

    List<String> statuses = new ArrayList<>();
    statuses.add("Accepted");
    statuses.add("Started");

    count = pickupRepository.countByStatusIn(statuses);

    return count;

  }


  public long countPickupsClosed() {

    long count = 0;

    List<String> statuses = new ArrayList<>();
    statuses.add("Ended");

    count = pickupRepository.countByStatusIn(statuses);

    return count;

  }



}
