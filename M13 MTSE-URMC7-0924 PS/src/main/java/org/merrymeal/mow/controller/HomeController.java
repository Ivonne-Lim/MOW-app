/*
 * Class Name:  HomeController
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

import org.merrymeal.mow.model.RepertoireDTO;

import org.merrymeal.mow.service.ProfileService;
import org.merrymeal.mow.service.MenuService;
import org.merrymeal.mow.service.MealService;
import org.merrymeal.mow.service.PickupService;
import org.merrymeal.mow.service.FeedbackService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.io.File;
import java.io.InputStream;
import java.security.Principal;

import java.util.List;
import java.util.Scanner;


@Controller

public class HomeController {



  @Autowired
  private ProfileService  profileService;

  @Autowired
  private MenuService     menuService;

  @Autowired
  private MealService     mealService;

  @Autowired
  private PickupService   pickupService;

  @Autowired
  private FeedbackService feedbackService;



  @GetMapping("/")

  public String showDefault() {

    return "redirect:/home";

  }


  @GetMapping("/home")

  public String showHome(
    Principal principal,
    Model model) {

    String username = null;

    Profile profile = new Profile();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {
      profile = profileService.findProfileByEmail(username);
    }

    if (profile.getRole() != null && profile.getRole().equals("Administrator")) {
      return "redirect:/dashboard";
    }

    return "index";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @GetMapping("/dashboard")

  public String showDashboard(
    Principal principal,
    Model model) {

    // Registration Data

    long members    = profileService.countMembers();
    model.addAttribute("members", members);

      long membersHalal       = profileService.countMembersByDiet("Halal");
      model.addAttribute("membersHalal", membersHalal);

      long membersVeg         = profileService.countMembersByDiet("Vegetarian");
      model.addAttribute("membersVeg", membersVeg);

      long membersSoft        = profileService.countMembersByDiet("Soft");
      model.addAttribute("membersSoft", membersSoft);

      long membersNormal      = profileService.countMembersByDiet("Normal");
      model.addAttribute("membersNormal", membersNormal);

    long caregivers = profileService.countCaregivers();
    model.addAttribute("caregivers", caregivers);

    long partners   = profileService.countPartners();
    model.addAttribute("partners", partners);

      long partnersProvider   = profileService.countPartnersByRole("Provider");
      model.addAttribute("partnersProvider", partnersProvider);

      long partnersRider      = profileService.countPartnersByRole("Rider");
      model.addAttribute("partnersRider", partnersRider);

    long volunteers = profileService.countVolunteers();
    model.addAttribute("volunteers", volunteers);

      long volunteersProvider = profileService.countVolunteersByRole("Provider");
      model.addAttribute("volunteersProvider", volunteersProvider);

      long volunteersRider    = profileService.countVolunteersByRole("Rider");
      model.addAttribute("volunteersRider", volunteersRider);

    // Evaluation Data

    // Note: Try scanning notice file

//  File noticeFile          = null;
//  Scanner noticeScanner    = null;
    InputStream noticeStream = null;
    String noticeLine        = null;

    int notice = 3;
      // Note: Default is 3 days.

    try {

//    noticeFile    = new ClassPathResource("config/notice.txt").getFile();
//    noticeScanner = new Scanner(noticeFile);

//    if (noticeScanner.hasNextLine()) {
//      noticeLine = noticeScanner.nextLine();
//    }

      noticeStream = new ClassPathResource("config/notice.txt").getInputStream();
        // Note: Switched to this technique which also works for WAR packaging

      noticeLine   = new String(noticeStream.readAllBytes());
        // Note: Switched to this technique which also works for WAR packaging 

      System.out.println("Scanned notice.txt in config: " + noticeLine);

//    noticeScanner.close();

    } catch(Exception e) {

      System.out.println(e.getMessage());

//    if (noticeScanner != null) {
//      noticeScanner.close();
//    }

    }

    // Note: If successful use overriding notice
    //                else use default notice

    if (noticeLine  != null && !noticeLine .equals("")) {
      notice = Integer.parseInt(noticeLine);
    }

    model.addAttribute("notice", notice);

    List<Profile> membersScheduled  = profileService.findMembersScheduled(notice);
    model.addAttribute("membersScheduled", membersScheduled);

    List<Profile> membersPending    = profileService.findMembersPending();
    model.addAttribute("membersPending", membersPending);

    List<Profile> caregiversPending = profileService.findCaregiversPending();
    model.addAttribute("caregiversPending", caregiversPending);

    List<Profile> partnersPending   = profileService.findPartnersPending();
    model.addAttribute("partnersPending", partnersPending);

    List<Profile> volunteersPending = profileService.findVolunteersPending();
    model.addAttribute("volunteersPending", volunteersPending);

    // Menu Planning Data

    List<RepertoireDTO> repertoiresMenuHalal  = menuService.groupMenuByDietAndProvider("Halal");
    model.addAttribute("repertoiresMenuHalal", repertoiresMenuHalal);

    List<RepertoireDTO> repertoiresMenuVeg    = menuService.groupMenuByDietAndProvider("Vegetarian");
    model.addAttribute("repertoiresMenuVeg", repertoiresMenuVeg);

    List<RepertoireDTO> repertoiresMenuSoft   = menuService.groupMenuByDietAndProvider("Soft");
    model.addAttribute("repertoiresMenuSoft", repertoiresMenuSoft);

    List<RepertoireDTO> repertoiresMenuNormal = menuService.groupMenuByDietAndProvider("Normal");
    model.addAttribute("repertoiresMenuNormal", repertoiresMenuNormal);

    // Meal Preparation Data

    long mealsOpen   = mealService.countMealsOpen();
    model.addAttribute("mealsOpen", mealsOpen);

    long mealsClosed = mealService.countMealsClosed();
    model.addAttribute("mealsClosed", mealsClosed);

    // Meal Delivery Data

    long mealsAvailable = pickupService.countMealsAvailable();
    model.addAttribute("mealsAvailable", mealsAvailable);

    long pickupsOpen    = pickupService.countPickupsOpen();
    model.addAttribute("pickupsOpen", pickupsOpen);

    long pickupsClosed  = pickupService.countPickupsClosed();
    model.addAttribute("pickupsClosed", pickupsClosed);

    // Service Feedback Data

    long submissions = feedbackService.countSubmissions();
    model.addAttribute("submissions", submissions);

      long submissionsGood = feedbackService.countSubmissionsGood();
      model.addAttribute("submissionsGood", submissionsGood);

      long submissionsPoor = feedbackService.countSubmissionsPoor();
      model.addAttribute("submissionsPoor", submissionsPoor);

    return "dashboard";

  }



}
