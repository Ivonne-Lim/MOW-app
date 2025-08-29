/*
 * Class Name:  FeedbackController
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

import org.merrymeal.mow.service.ProfileService;
import org.merrymeal.mow.service.PickupService;
import org.merrymeal.mow.service.FeedbackService;

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

public class FeedbackController {



  @Autowired
  private ProfileService  profileService;

  @Autowired
  private PickupService   pickupService;

  @Autowired
  private FeedbackService feedbackService;



  private final String PAGINATION_SIZE = "2";
    // Note: Default and maximum should be "15" as measured.



  @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')")
  @GetMapping("/service-feedback")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showServiceFeedback(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Feedback> submissions = new ArrayList<>();
    
    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      submissions = feedbackService.findSubmissions(username);

    }

    model.addAttribute("submissions", submissions);

    return "service-feedback";

  }

 ********************************************************************************/

  public String showServiceFeedback(
    Principal principal,
    Model model,
    @RequestParam(name = "page",     required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",     required = false, defaultValue = PAGINATION_SIZE) int size,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Feedback> submissions = new ArrayList<>();
    int pages = 0;

    Page<Feedback> items = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = feedbackService.findSubmissions(username, page, size);

      submissions = items.getContent();
      pages       = items.getTotalPages();

    }

    model.addAttribute("submissions", submissions);
    model.addAttribute("pages",       pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "service-feedback";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MEMBER')")
  @PostMapping("/service-feedback_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteServiceFeedback(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    Feedback submission = new Feedback();

    List<Feedback> submissions = new ArrayList<>();

    if (id != null && !id.equals("")) {
      submission = feedbackService.deleteSubmissionById(id);
    }

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      submissions = feedbackService.findSubmissions(username);

    }

    model.addAttribute("submissions", submissions);

    return "service-feedback";

  }

 ********************************************************************************/

  public String deleteServiceFeedback(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id,
    @RequestParam(name = "page",     required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",     required = false, defaultValue = PAGINATION_SIZE) int size,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    Feedback submission = new Feedback();

    int pages = 0;

    Page<Feedback> items = null;

    List<Feedback> submissions = new ArrayList<>();

    if (id != null && !id.equals("")) {
      submission = feedbackService.deleteSubmissionById(id);
    }

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = feedbackService.findSubmissions(username, page, size);

      submissions = items.getContent();
      pages       = items.getTotalPages();

    }

    model.addAttribute("submissions", submissions);
    model.addAttribute("pages",       pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "service-feedback";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR','MEMBER', 'PARTNER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')")
  @GetMapping("/service-feedback_submission")

  public String showServiceFeedbackSubmission(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = false) String id) {

    String username = null;

    Feedback submission = new Feedback();

    Profile member      = new Profile();

    List<Pickup> deliveries = new ArrayList<>();

    if (id != null && !id.equals("")) {
      submission = feedbackService.findSubmissionById(id);
      member = submission.getMember();
    } else {
      if (principal != null) {
        username = principal.getName();
      }
      if (username != null && !username.equals("")) {
        member = profileService.findProfileByEmail(username);
      }
    }
    model.addAttribute("submission", submission);
    model.addAttribute("member", member);

    if (member != null && member.getEmail() != null && !member.getEmail().equals("")) {
      deliveries = pickupService.findDeliveries(member.getEmail());
    }
    model.addAttribute("deliveries", deliveries);

    return "service-feedback_submission";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MEMBER')")
  @PostMapping("/service-feedback_submission")

  public String saveServiceFeedbackSubmission(
    Principal principal,
    Model model,
    @ModelAttribute("submission") @Valid Feedback submission,
    @RequestParam(name = "member_id", required = true) String member_id,
    @RequestParam(name = "pickup_id", required = true) String pickup_id) {

    Profile member  = new Profile();

    List<Pickup> deliveries = new ArrayList<>();

    submission = feedbackService.saveSubmission(submission, member_id, pickup_id);
    model.addAttribute("submission", submission);

    member = submission.getMember();
    model.addAttribute("member", member);

    if (member != null && member.getEmail() != null && !member.getEmail().equals("")) {
      deliveries = pickupService.findDeliveries(member.getEmail());
    }
    model.addAttribute("deliveries", deliveries);

    return "service-feedback_submission";

  }



}
