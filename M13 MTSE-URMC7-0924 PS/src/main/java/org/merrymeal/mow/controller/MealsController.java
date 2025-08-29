/*
 * Class Name:  MealsController
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
import org.merrymeal.mow.service.MealService;

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

public class MealsController {



  @Autowired
  private ProfileService profileService;

  @Autowired
  private MealService mealService;



  private final String PAGINATION_SIZE = "2";
    // Note: Default and maximum should be "15" as measured.



  @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/meal-preparation")

  public String showMealPreparation(
    Principal principal,
    Model model) {

    return "meal-preparation";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/meal-preparation_halal")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showMealPreparationHalal(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.findMeals("Halal", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_halal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_halal().getSeq_day(),
               profile.getMenu_halal().getSeq_time(),
               profile.getMenu_halal().getName(),
               profile.getMenu_halal().getFrozen(),
               profile.getMenu_halal().getStatus());
      }

    }

    model.addAttribute("meals", meals);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_halal";

  }

 ********************************************************************************/

  public String showMealPreparationHalal(
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

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = mealService.findMeals("Halal", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_halal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_halal().getSeq_day(),
               profile.getMenu_halal().getSeq_time(),
               profile.getMenu_halal().getName(),
               profile.getMenu_halal().getFrozen(),
               profile.getMenu_halal().getStatus());
      }

    }

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_halal";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/meal-preparation_veg")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showMealPreparationVeg(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.findMeals("Vegetarian", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_veg() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_veg().getSeq_day(),
               profile.getMenu_veg().getSeq_time(),
               profile.getMenu_veg().getName(),
               profile.getMenu_veg().getFrozen(),
               profile.getMenu_veg().getStatus());
      }

    }

    model.addAttribute("meals", meals);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_veg";

  }

 ********************************************************************************/

  public String showMealPreparationVeg(
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

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = mealService.findMeals("Vegetarian", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_veg() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_veg().getSeq_day(),
               profile.getMenu_veg().getSeq_time(),
               profile.getMenu_veg().getName(),
               profile.getMenu_veg().getFrozen(),
               profile.getMenu_veg().getStatus());
      }

    }

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_veg";

  }


  @PreAuthorize("hasAnyRole('ADMINSTRATOR', 'MEMBER', 'PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/meal-preparation_soft")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showMealPreparationSoft(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.findMeals("Soft", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_soft() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_soft().getSeq_day(),
               profile.getMenu_soft().getSeq_time(),
               profile.getMenu_soft().getName(),
               profile.getMenu_soft().getFrozen(),
               profile.getMenu_soft().getStatus());
      }

    }

    model.addAttribute("meals", meals);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_soft";

  }

 ********************************************************************************/

  public String showMealPreparationSoft(
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

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = mealService.findMeals("Soft", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_soft() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_soft().getSeq_day(),
               profile.getMenu_soft().getSeq_time(),
               profile.getMenu_soft().getName(),
               profile.getMenu_soft().getFrozen(),
               profile.getMenu_soft().getStatus());
      }

    }

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_soft";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/meal-preparation_normal")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showMealPreparationNormal(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.findMeals("Normal", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_normal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_normal().getSeq_day(),
               profile.getMenu_normal().getSeq_time(),
               profile.getMenu_normal().getName(),
               profile.getMenu_normal().getFrozen(),
               profile.getMenu_normal().getStatus());
      }

    }

    model.addAttribute("meals", meals);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_normal";

  }

 ********************************************************************************/

  public String showMealPreparationNormal(
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

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = mealService.findMeals("Normal", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_normal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_normal().getSeq_day(),
               profile.getMenu_normal().getSeq_time(),
               profile.getMenu_normal().getName(),
               profile.getMenu_normal().getFrozen(),
               profile.getMenu_normal().getStatus());
      }

    }

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_normal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_halal_generation")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String generateMealPreparationHalal(
    Principal principal,
    Model model) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.generateMeals("Halal", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_halal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_halal().getSeq_day(),
               profile.getMenu_halal().getSeq_time(),
               profile.getMenu_halal().getName(),
               profile.getMenu_halal().getFrozen(),
               profile.getMenu_halal().getStatus());
      }

    }

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_halal";

  }

 ********************************************************************************/

  public String generateMealPreparationHalal(
    Principal principal,
    Model model) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    int page  = 0;
    int size  = Integer.parseInt(PAGINATION_SIZE);

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.generateMeals("Halal", username);

      items = mealService.findMeals("Halal", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_halal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_halal().getSeq_day(),
               profile.getMenu_halal().getSeq_time(),
               profile.getMenu_halal().getName(),
               profile.getMenu_halal().getFrozen(),
               profile.getMenu_halal().getStatus());
      }

    }

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_halal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_veg_generation")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String generateMealPreparationVeg(
    Principal principal,
    Model model) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.generateMeals("Vegetarian", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_veg() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_veg().getSeq_day(),
               profile.getMenu_veg().getSeq_time(),
               profile.getMenu_veg().getName(),
               profile.getMenu_veg().getFrozen(),
               profile.getMenu_veg().getStatus());
      }

    }

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_veg";

  }

 ********************************************************************************/

  public String generateMealPreparationVeg(
    Principal principal,
    Model model) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    int page  = 0;
    int size  = Integer.parseInt(PAGINATION_SIZE);

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.generateMeals("Vegetarian", username);

      items = mealService.findMeals("Vegetarian", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_veg() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_veg().getSeq_day(),
               profile.getMenu_veg().getSeq_time(),
               profile.getMenu_veg().getName(),
               profile.getMenu_veg().getFrozen(),
               profile.getMenu_veg().getStatus());
      }

    }

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_veg";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_soft_generation")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String generateMealPreparationSoft(
    Principal principal,
    Model model) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.generateMeals("Soft", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_soft() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_soft().getSeq_day(),
               profile.getMenu_soft().getSeq_time(),
               profile.getMenu_soft().getName(),
               profile.getMenu_soft().getFrozen(),
               profile.getMenu_soft().getStatus());
      }

    }

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_soft";

  }

 ********************************************************************************/
 
  public String generateMealPreparationSoft(
    Principal principal,
    Model model) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    int page  = 0;
    int size  = Integer.parseInt(PAGINATION_SIZE);

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.generateMeals("Soft", username);

      items = mealService.findMeals("Soft", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_soft() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_soft().getSeq_day(),
               profile.getMenu_soft().getSeq_time(),
               profile.getMenu_soft().getName(),
               profile.getMenu_soft().getFrozen(),
               profile.getMenu_soft().getStatus());
      }

    }

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_soft";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_normal_generation")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String generateMealPreparationNormal(
    Principal principal,
    Model model) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.generateMeals("Normal", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_normal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_normal().getSeq_day(),
               profile.getMenu_normal().getSeq_time(),
               profile.getMenu_normal().getName(),
               profile.getMenu_normal().getFrozen(),
               profile.getMenu_normal().getStatus());
      }

    }

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_normal";

  }

 ********************************************************************************/

  public String generateMealPreparationNormal(
    Principal principal,
    Model model) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    int page  = 0;
    int size  = Integer.parseInt(PAGINATION_SIZE);

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    SelectWrapper selectWrapper = new SelectWrapper();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.generateMeals("Normal", username);

      items = mealService.findMeals("Normal", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_normal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_normal().getSeq_day(),
               profile.getMenu_normal().getSeq_time(),
               profile.getMenu_normal().getName(),
               profile.getMenu_normal().getFrozen(),
               profile.getMenu_normal().getStatus());
      }

    }

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_normal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_halal_updation")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String updateMealPreparationHalal(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "status", required = true) String status) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, status);

      meals = mealService.findMeals("Halal", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_halal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_halal().getSeq_day(),
               profile.getMenu_halal().getSeq_time(),
               profile.getMenu_halal().getName(),
               profile.getMenu_halal().getFrozen(),
               profile.getMenu_halal().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_halal";

  }

 ********************************************************************************/

  public String updateMealPreparationHalal(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "status", required = true)  String status,
    @RequestParam(name = "page",   required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",   required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, status);

      items = mealService.findMeals("Halal", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_halal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_halal().getSeq_day(),
               profile.getMenu_halal().getSeq_time(),
               profile.getMenu_halal().getName(),
               profile.getMenu_halal().getFrozen(),
               profile.getMenu_halal().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_halal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_veg_updation")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String updateMealPreparationVeg(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "status", required = true) String status) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, status);

      meals = mealService.findMeals("Vegetarian", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_veg() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_veg().getSeq_day(),
               profile.getMenu_veg().getSeq_time(),
               profile.getMenu_veg().getName(),
               profile.getMenu_veg().getFrozen(),
               profile.getMenu_veg().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_veg";

  }

 ********************************************************************************/

  public String updateMealPreparationVeg(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "status", required = true)  String status,
    @RequestParam(name = "page",   required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",   required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, status);

      items = mealService.findMeals("Vegetarian", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_veg() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_veg().getSeq_day(),
               profile.getMenu_veg().getSeq_time(),
               profile.getMenu_veg().getName(),
               profile.getMenu_veg().getFrozen(),
               profile.getMenu_veg().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_veg";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_soft_updation")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String updateMealPreparationSoft(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "status", required = true) String status) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, status);

      meals = mealService.findMeals("Soft", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_soft() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_soft().getSeq_day(),
               profile.getMenu_soft().getSeq_time(),
               profile.getMenu_soft().getName(),
               profile.getMenu_soft().getFrozen(),
               profile.getMenu_soft().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_soft";

  }

 ********************************************************************************/

  public String updateMealPreparationSoft(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "status", required = true)  String status,
    @RequestParam(name = "page",   required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",   required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, status);

      items = mealService.findMeals("Soft", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_soft() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_soft().getSeq_day(),
               profile.getMenu_soft().getSeq_time(),
               profile.getMenu_soft().getName(),
               profile.getMenu_soft().getFrozen(),
               profile.getMenu_soft().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_soft";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_normal_updation")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String updateMealPreparationNormal(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "status", required = true) String status) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, status);

      meals = mealService.findMeals("Normal", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_normal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_normal().getSeq_day(),
               profile.getMenu_normal().getSeq_time(),
               profile.getMenu_normal().getName(),
               profile.getMenu_normal().getFrozen(),
               profile.getMenu_normal().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_normal";

  }

 ********************************************************************************/

  public String updateMealPreparationNormal(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "status", required = true)  String status,
    @RequestParam(name = "page",   required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",   required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, status);

      items = mealService.findMeals("Normal", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_normal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_normal().getSeq_day(),
               profile.getMenu_normal().getSeq_time(),
               profile.getMenu_normal().getName(),
               profile.getMenu_normal().getFrozen(),
               profile.getMenu_normal().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_normal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_halal_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteMealPreparationHalal(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, "Deleted");

      meals = mealService.findMeals("Halal", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_halal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_halal().getSeq_day(),
               profile.getMenu_halal().getSeq_time(),
               profile.getMenu_halal().getName(),
               profile.getMenu_halal().getFrozen(),
               profile.getMenu_halal().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_halal";

  }

 ********************************************************************************/

  public String deleteMealPreparationHalal(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, "Deleted");

      items = mealService.findMeals("Halal", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_halal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_halal().getSeq_day(),
               profile.getMenu_halal().getSeq_time(),
               profile.getMenu_halal().getName(),
               profile.getMenu_halal().getFrozen(),
               profile.getMenu_halal().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_halal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_veg_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteMealPreparationVeg(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, "Deleted");

      meals = mealService.findMeals("Vegetarian", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_veg() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_veg().getSeq_day(),
               profile.getMenu_veg().getSeq_time(),
               profile.getMenu_veg().getName(),
               profile.getMenu_veg().getFrozen(),
               profile.getMenu_veg().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_veg";

  }

 ********************************************************************************/

  public String deleteMealPreparationVeg(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, "Deleted");

      items = mealService.findMeals("Vegetarian", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_veg() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_veg().getSeq_day(),
               profile.getMenu_veg().getSeq_time(),
               profile.getMenu_veg().getName(),
               profile.getMenu_veg().getFrozen(),
               profile.getMenu_veg().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_veg";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_soft_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteMealPreparationSoft(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, "Deleted");

      meals = mealService.findMeals("Soft", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_soft() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_soft().getSeq_day(),
               profile.getMenu_soft().getSeq_time(),
               profile.getMenu_soft().getName(),
               profile.getMenu_soft().getFrozen(),
               profile.getMenu_soft().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_soft";

  }

 ********************************************************************************/

  public String deleteMealPreparationSoft(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, "Deleted");

      items = mealService.findMeals("Soft", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_soft() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_soft().getSeq_day(),
               profile.getMenu_soft().getSeq_time(),
               profile.getMenu_soft().getName(),
               profile.getMenu_soft().getFrozen(),
               profile.getMenu_soft().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_soft";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_normal_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteMealPreparationNormal(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, "Deleted");

      meals = mealService.findMeals("Normal", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_normal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_normal().getSeq_day(),
               profile.getMenu_normal().getSeq_time(),
               profile.getMenu_normal().getName(),
               profile.getMenu_normal().getFrozen(),
               profile.getMenu_normal().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_normal";

  }

 ********************************************************************************/

  public String deleteMealPreparationNormal(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.updateMeals(selectWrapper, "Deleted");

      items = mealService.findMeals("Normal", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_normal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_normal().getSeq_day(),
               profile.getMenu_normal().getSeq_time(),
               profile.getMenu_normal().getName(),
               profile.getMenu_normal().getFrozen(),
               profile.getMenu_normal().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_normal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_halal_printing")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String printMealPreparationHalal(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.printMeals(selectWrapper, username);

      meals = mealService.findMeals("Halal", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_halal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_halal().getSeq_day(),
               profile.getMenu_halal().getSeq_time(),
               profile.getMenu_halal().getName(),
               profile.getMenu_halal().getFrozen(),
               profile.getMenu_halal().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_halal";

  }

 ********************************************************************************/

  public String printMealPreparationHalal(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.printMeals(selectWrapper, username);

      items = mealService.findMeals("Halal", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_halal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_halal().getSeq_day(),
               profile.getMenu_halal().getSeq_time(),
               profile.getMenu_halal().getName(),
               profile.getMenu_halal().getFrozen(),
               profile.getMenu_halal().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_halal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_veg_printing")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String printMealPreparationVeg(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.printMeals(selectWrapper, username);

      meals = mealService.findMeals("Vegetarian", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_veg() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_veg().getSeq_day(),
               profile.getMenu_veg().getSeq_time(),
               profile.getMenu_veg().getName(),
               profile.getMenu_veg().getFrozen(),
               profile.getMenu_veg().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_veg";

  }

 ********************************************************************************/

  public String printMealPreparationVeg(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.printMeals(selectWrapper, username);

      items = mealService.findMeals("Vegetarian", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_veg() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_veg().getSeq_day(),
               profile.getMenu_veg().getSeq_time(),
               profile.getMenu_veg().getName(),
               profile.getMenu_veg().getFrozen(),
               profile.getMenu_veg().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_veg";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_soft_printing")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String printMealPreparationSoft(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.printMeals(selectWrapper, username);

      meals = mealService.findMeals("Soft", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_soft() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_soft().getSeq_day(),
               profile.getMenu_soft().getSeq_time(),
               profile.getMenu_soft().getName(),
               profile.getMenu_soft().getFrozen(),
               profile.getMenu_soft().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("menu",  menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_soft";

  }

 ********************************************************************************/

  public String printMealPreparationSoft(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.printMeals(selectWrapper, username);

      items = mealService.findMeals("Soft", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_soft() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_soft().getSeq_day(),
               profile.getMenu_soft().getSeq_time(),
               profile.getMenu_soft().getName(),
               profile.getMenu_soft().getFrozen(),
               profile.getMenu_soft().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_soft";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/meal-preparation_normal_printing")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String printMealPreparationNormal(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper) {

    String username = null;

    List<Meal> meals = new ArrayList<>();

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.printMeals(selectWrapper, username);

      meals = mealService.findMeals("Normal", username);

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_normal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_normal().getSeq_day(),
               profile.getMenu_normal().getSeq_time(),
               profile.getMenu_normal().getName(),
               profile.getMenu_normal().getFrozen(),
               profile.getMenu_normal().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_normal";

  }

 ********************************************************************************/

  public String printMealPreparationNormal(
    Principal principal,
    Model model,
    @ModelAttribute("selectWrapper") SelectWrapper selectWrapper,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    String username = null;

    List<Meal> meals = new ArrayList<>();
    int  pages    = 0;
    long elements = 0;

    Page<Meal> items = null;

    Profile profile = new Profile();

    String menu = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      meals = mealService.printMeals(selectWrapper, username);

      items = mealService.findMeals("Normal", username, page, size);

      meals    = items.getContent();
      pages    = items.getTotalPages();
      elements = items.getTotalElements();

      profile = profileService.findProfileByEmail(username);
      if (profile.getMenu_normal() != null) {
        menu = String.format("Day:%d Time:%d %s Frozen:%s %s",
               profile.getMenu_normal().getSeq_day(),
               profile.getMenu_normal().getSeq_time(),
               profile.getMenu_normal().getName(),
               profile.getMenu_normal().getFrozen(),
               profile.getMenu_normal().getStatus());
      }

    }

    selectWrapper = new SelectWrapper();

    model.addAttribute("meals", meals);
    model.addAttribute("pages", pages);
    model.addAttribute("elements", elements);

    model.addAttribute("current", page);
    model.addAttribute("size", size);

    model.addAttribute("menu", menu);
    model.addAttribute("selectWrapper", selectWrapper);

    return "meal-preparation_normal";

  }



}
