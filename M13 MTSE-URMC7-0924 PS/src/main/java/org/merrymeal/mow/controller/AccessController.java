/*
 * Class Name:  AccessController
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

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import java.security.Principal;

import java.util.List;
import java.util.ArrayList;

import jakarta.validation.constraints.Email;


@Controller

public class AccessController {



  @Autowired
  private ProfileService profileService;



  @GetMapping("/access")

  public String showAccess() {

    return "access";

  }


  // Note: No @PostMapping("/access")
  //       public String showAccess()


  @PostMapping("/login")

  public String loginAccess(
    @RequestParam(name = "email",    required = true) @Email String email,
    @RequestParam(name = "password", required = true)        String password) {

    Profile profile = new Profile();

    profile = profileService.findProfileByEmailAndPassword(email, password);

    if (profile == null) {
      return "redirect:/login_failure";
    }

    return "redirect:/login_success";
      // Note: Setting of principal done automatically by Spring Security

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')")
  @PostMapping("/logout")

  public String logoutAccess(
    Principal principal,
    Model model) {

    return "redirect:/logout_success";
       // Note: Clearing of principal done automatically by Spring Security

  }


  @GetMapping("/login_failure")

  public String showLoginFailure(
    Principal principal,
    Model model) {

    model.addAttribute("login_failure", "Login failed due to incorrect Email Address or Password.");
    return "access";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')")
  @GetMapping("/login_success")

  public String showLoginSuccess(
    Principal principal,
    Model model) {

    model.addAttribute("login_success", "Login successful.");
    return "access";

  }


  // Note: No @GetMapping("/logout_failure")
  //       public String showLogoutFailure()
  

  @GetMapping("/logout_success")

  public String showLogoutSuccess(
    Principal principal,
    Model model) {

    model.addAttribute("logout_success", "Logout successful.");
    return "access";

  }



}
