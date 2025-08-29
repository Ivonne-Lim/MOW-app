/*
 * Class Name:  PickupController
 * Description: Controller
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.controller;


import org.merrymeal.mow.entity.Profile;
import org.merrymeal.mow.entity.Menu;
import org.merrymeal.mow.entity.Meal;
import org.merrymeal.mow.entity.Pickup;
import org.merrymeal.mow.entity.Feedback;

import org.merrymeal.mow.model.SelectWrapper;

import org.merrymeal.mow.service.ProfileService;
import org.merrymeal.mow.service.PickupService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;

import jakarta.validation.Valid;

import java.security.Principal;

import java.util.List;
import java.util.ArrayList;


@Controller

public class PickupsController {



  @Autowired
  private ProfileService profileService;

  @Autowired
  private PickupService  pickupService;



  private final String PAGINATION_SIZE = "2";
  // Note: Default and maximum should be "15" as measured.



  @PreAuthorize("hasAnyRole('ADMININSTRATOR', 'MEMBER', 'PARTNER - FDR', 'VOLUNTEER - FDR')")
  @GetMapping("/meal-delivery")

  public String showMealDelivery(
    Principal principal,
    Model model) {

    return "meal-delivery";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FDR', 'VOLUNTEER - FDR')")
  @GetMapping("/meal-delivery_acceptance")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showMealDeliveryAcceptance(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Meal> meals = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = pickupService.findMealsAvailable(username);

    }

    model.addAttribute("meals", meals);

    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-delivery_acceptance";

  }

 ********************************************************************************/

  public String showMealDeliveryAcceptance(
    Principal principal,
    Model model,
    @RequestParam(name = "page",     required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",     required = false, defaultValue = PAGINATION_SIZE) int size,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = pickupService.findMealsAvailable(username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

    }

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-delivery_acceptance";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @GetMapping("/meal-delivery_assignment")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showMealDeliveryAssignment(
    Principal principal,
    Model model) {

    List<Meal> meals     = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

    List<Profile> riders = new ArrayList<>();

    meals = pickupService.findMealsAvailable();

    riders  = profileService.findRiders();
 
    model.addAttribute("meals", meals);

    model.addAttribute("riders", riders);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-delivery_assignment";

  }

 ********************************************************************************/

  public String showMealDeliveryAssignment(
    Principal principal,
    Model model,
    @RequestParam(name = "page",     required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",     required = false, defaultValue = PAGINATION_SIZE) int size) {

    List<Meal> meals     = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    List<Profile> riders = new ArrayList<>();

    items = pickupService.findMealsAvailable(page, size);

    meals    = items.getContent();
    pages    = items.getTotalPages();
    elements = items.getTotalElements();

    riders  = profileService.findRiders();
		 
    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("riders", riders);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-delivery_assignment";

  }


  @PreAuthorize("hasAnyRole('MEMBER', 'PARTNER - FDR', 'VOLUNTEER - FDR')")
  @GetMapping("/meal-delivery_current")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showMealDeliveryCurrent(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Pickup> pickups = new ArrayList<>();

    Profile profile = new Profile();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      profile = profileService.findProfileByEmail(username);

      if (profile.getRole() != null && (profile.getRole().equals("Partner - FDR") || profile.getRole().equals("Volunteer - FDR"))) {

        pickups = pickupService.findPickupsCurrentByRider(username);

      } else if (profile.getRole() != null && profile.getRole().equals("Member")){

    	pickups = pickupService.findPickupsCurrentByMember(username);

      }

    }

    model.addAttribute("pickups", pickups);

    return "meal-delivery_current";

  }

 ********************************************************************************/

  public String showMealDeliveryCurrent(
    Principal principal,
    Model model,
    @RequestParam(name = "page",     required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",     required = false, defaultValue = PAGINATION_SIZE) int size,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Pickup> pickups = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Pickup> items = null;

    Profile profile = new Profile();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      profile = profileService.findProfileByEmail(username);

      if (profile.getRole() != null && (profile.getRole().equals("Partner - FDR") || profile.getRole().equals("Volunteer - FDR"))) {

        items = pickupService.findPickupsCurrentByRider(username, page, size);

      } else if (profile.getRole() != null && profile.getRole().equals("Member")){

    	items = pickupService.findPickupsCurrentByMember(username, page, size);

      }

      pickups  = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

    }

    model.addAttribute("pickups", pickups);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    return "meal-delivery_current";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FDR', 'VOLUNTEER - FDR')")
  @PostMapping("/meal-delivery_acceptance")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String generateMealDeliveryAcceptance(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper) {

    String username = null;

    List<Pickup> pickups = new ArrayList<>();

    List<Meal> meals     = new ArrayList<>();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      pickups = pickupService.generatePickups(selectWrapper, username);

      meals = pickupService.findMealsAvailable(username);

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);

    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-delivery_acceptance";

  }

 ********************************************************************************/

  public String generateMealDeliveryAcceptance(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "page",     required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",     required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Pickup> pickups = new ArrayList<>();

    List<Meal> meals     = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      pickups = pickupService.generatePickups(selectWrapper, username);

      items = pickupService.findMealsAvailable(username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-delivery_acceptance";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @PostMapping("/meal-delivery_assignment")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String generateMealDeliveryAssignment(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "rider_id", required = true) String rider_id) {

    Profile rider = new Profile();

    List<Pickup> pickups = new ArrayList<>();

    List<Meal> meals     = new ArrayList<>();

    List<Profile> riders = new ArrayList<>();

    rider   = profileService.findProfileById(rider_id);
    pickups = pickupService.generatePickups(selectWrapper, rider.getEmail());

    meals = pickupService.findMealsAvailable();

    riders  = profileService.findRiders();

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);

    model.addAttribute("riders", riders);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-delivery_assignment";

  }

 ********************************************************************************/

  public String generateMealDeliveryAssignment(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "rider_id", required = true) String rider_id,
    @RequestParam(name = "page",     required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",     required = false, defaultValue = PAGINATION_SIZE) int size) {

    Profile rider = new Profile();

    List<Pickup> pickups = new ArrayList<>();

    List<Meal> meals     = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    List<Profile> riders = new ArrayList<>();

    rider   = profileService.findProfileById(rider_id);

    pickups = pickupService.generatePickups(selectWrapper, rider.getEmail());

    items = pickupService.findMealsAvailable(page, size);

    meals    = items.getContent();
    pages    = items.getTotalPages();
    elements = items.getTotalElements();

    riders  = profileService.findRiders();

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("riders", riders);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-delivery_assignment";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FDR', 'VOLUNTEER - FDR')")
  @GetMapping("/meal-delivery_current_pickup")

  public String showMealDeliveryCurrentPickup(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id) {

    Pickup pickup = new Pickup();

    String username = null;

    if (id != null && !id.equals("")) {

      pickup = pickupService.findPickupById(id);

    }

    model.addAttribute("pickup", pickup);

    return "meal-delivery_current_pickup";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FDR', 'VOLUNTEER - FDR')")
  @PostMapping("/meal-delivery_current_pickup")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String saveMealDeliveryCurrentPickup(
    Principal principal,
    Model model,
    @ModelAttribute("pickup") @Valid Pickup pickup,
    @RequestParam(name = "rider_id", required = true) String rider_id,
    @RequestParam(name = "meal_id",  required = true) String meal_id) {

    String username = null;

    List<Pickup> pickups = new ArrayList<>();

    pickup = pickupService.savePickup(pickup, rider_id, meal_id);

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      pickups = pickupService.findPickupsCurrentByRider(username);

    }

    model.addAttribute("pickups", pickups);

    return "meal-delivery_current";

  }

 ********************************************************************************/

  public String saveMealDeliveryCurrentPickup(
    Principal principal,
    Model model,
    @ModelAttribute("pickup") @Valid Pickup pickup,
    @RequestParam(name = "rider_id", required = true) String rider_id,
    @RequestParam(name = "meal_id",  required = true) String meal_id) {

    String username = null;

    List<Pickup> pickups = new ArrayList<>();
    int  pages  = 0;
    long elements = 0;

    int page  = 0;
    int size  = Integer.parseInt(PAGINATION_SIZE);

    Page<Pickup> items = null;

    pickup = pickupService.savePickup(pickup, rider_id, meal_id);

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = pickupService.findPickupsCurrentByRider(username, page, size);

      pickups  = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

    }

    model.addAttribute("pickups", pickups);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    return "meal-delivery_current";

  }


  @PreAuthorize("hasAnyRole('MEMBER', 'PARTNER - FDR', 'VOLUNTEER - FDR')")
  @GetMapping("/meal-delivery_current_pickup_caregivers")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showMealDeliveryCurrentPickupCaregivers(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id) {

    Pickup pickup  = new Pickup();

    Profile member = new Profile();

    List<Profile> caregivers = new ArrayList<>();

    if (id != null && !id.equals("")) {

      pickup = pickupService.findPickupById(id);

      member = pickup.getMeal().getMember();

      caregivers = profileService.findCaregivers(Integer.toString(member.getId()));

    }

    model.addAttribute("member", member);

    model.addAttribute("caregivers", caregivers);

    return "meal-delivery_current_pickup_caregivers";

  }

 ********************************************************************************/

  public String showMealDeliveryCurrentPickupCaregivers(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    Pickup pickup  = new Pickup();

    Profile member = new Profile();

    List<Profile> caregivers = new ArrayList<>();
    int  pages    = 0;

    Page<Profile> items = null;

    if (id != null && !id.equals("")) {

      pickup = pickupService.findPickupById(id);

      member = pickup.getMeal().getMember();

      items = profileService.findCaregivers(Integer.toString(member.getId()), page, size);

      caregivers = items.getContent();
      pages      = items.getTotalPages();

    }

    model.addAttribute("id", id);

    model.addAttribute("member", member);

    model.addAttribute("caregivers", caregivers);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    return "meal-delivery_current_pickup_caregivers";

  }



}
