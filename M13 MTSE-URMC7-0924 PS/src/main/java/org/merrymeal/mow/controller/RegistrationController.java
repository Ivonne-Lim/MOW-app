/*
 * Class Name:  RegistrationController
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
import org.merrymeal.mow.service.MenuService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;

import jakarta.validation.Valid;

import java.io.File;
import java.io.InputStream;
import java.security.Principal;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


@Controller

public class RegistrationController {



  @Autowired
  private ProfileService profileService;

  @Autowired
  private MenuService    menuService;



  private final String PAGINATION_SIZE = "2";
    // Note: Default and maximum should be "15" as measured.



  @GetMapping("/registration")

  public String showRegistration(
    Principal principal,
    Model model) {

    return "registration";

  }


  @GetMapping("/registration_member")

  public String showRegistrationMember(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
       // Note: username parameter for testing

    Profile profile = new Profile();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {
      profile = profileService.findProfileByEmail(username);
    }

    if (profile.getRole() == null || profile.getRole().equals("Member")) {
      model.addAttribute("member", profile);
    }

    // Note: Try scanning upload_url file

    final String UPLOAD_URL  = "localhost:4000";

//  File uploadUrlFile          = null;
//  Scanner uploadUrlScanner    = null;
    InputStream uploadUrlStream = null;
    String uploadUrlLine        = null;

    String upload_url           = null;

    try {

//    uploadUrlFile     = new ClassPathResource("config/upload_url.txt").getFile();
//    uploadUrlScanner  = new Scanner(uploadUrlFile);

//    if (uploadUrlScanner.hasNextLine()) {
//      uploadUrlLine   = uploadUrlScanner.nextLine();
//    }

      uploadUrlStream = new ClassPathResource("config/upload_url.txt").getInputStream();
        // Note: Switched to this technique which also works for WAR packaging

      uploadUrlLine   = new String(uploadUrlStream.readAllBytes());
        // Note: Switched to this technique which also works for WAR packaging 

      System.out.println("Scanned upload_url.txt in config: " + uploadUrlLine);

//    uploadUrlScanner.close();

    } catch(Exception e) {

      System.out.println(e.getMessage());

//    if (uploadUrlScanner != null) {
//      uploadUrlScanner.close();
//    }

    }

    // Note: If successful use overriding Upload URL
    //                else use default Upload URL

    if (uploadUrlLine == null || uploadUrlLine.equals("")) {
      upload_url = UPLOAD_URL;
    } else {
      upload_url = uploadUrlLine;
    }

    model.addAttribute("upload_url", upload_url);

    return "registration_member";

  }


  @GetMapping("/registration_partner")

  public String showRegistrationPartner(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    Profile profile = new Profile();

    List<Menu> menusHalal  = new ArrayList<>();
    List<Menu> menusVeg    = new ArrayList<>();
    List<Menu> menusSoft   = new ArrayList<>();
    List<Menu> menusNormal = new ArrayList<>();

    List<Profile> providers = new ArrayList<>();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {
      profile = profileService.findProfileByEmail(username);
    }

    if (profile.getRole() == null || profile.getRole().equals("Partner - FSP") || profile.getRole().equals("Partner - FDR")) {
      model.addAttribute("partner", profile);
    }

    if (profile.getRole() != null && profile.getRole().equals("Partner - FSP")) {
      menusHalal  = menuService.findMenus("Halal",      username);
      menusVeg    = menuService.findMenus("Vegetarian", username);
      menusSoft   = menuService.findMenus("Soft",       username);
      menusNormal = menuService.findMenus("Normal",     username);
      model.addAttribute("menusHalal",  menusHalal);
      model.addAttribute("menusVeg",    menusVeg);
      model.addAttribute("menusSoft",   menusSoft);
      model.addAttribute("menusNormal", menusNormal);
    }

    if (profile.getRole() != null && profile.getRole().equals("Partner - FDR")) {
      providers = profileService.findProviders();
      model.addAttribute("providers", providers);
    }

    return "registration_partner";

  }


  @GetMapping("/registration_volunteer")

  public String showRegistrationVolunteer(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    Profile profile = new Profile();

    List<Menu> menusHalal   = new ArrayList<>();
    List<Menu> menusVeg     = new ArrayList<>();
    List<Menu> menusSoft    = new ArrayList<>();
    List<Menu> menusNormal  = new ArrayList<>();

    List<Profile> providers = new ArrayList<>();

    if (principal != null) {
      username = principal.getName();	
    }

    if (username != null && !username.equals("")) {
      profile = profileService.findProfileByEmail(username);
    }

    if (profile.getRole() == null || profile.getRole().equals("Volunteer - FSP") || profile.getRole().equals("Volunteer - FDR")) {
      model.addAttribute("volunteer", profile);
    }

    if (profile.getRole() != null && profile.getRole().equals("Volunteer - FSP")) {
      menusHalal  = menuService.findMenus("Halal",      username);
      menusVeg    = menuService.findMenus("Vegetarian", username);
      menusSoft   = menuService.findMenus("Soft",       username);
      menusNormal = menuService.findMenus("Normal",     username);
      model.addAttribute("menusHalal",  menusHalal);
      model.addAttribute("menusVeg",    menusVeg);
      model.addAttribute("menusSoft",   menusSoft);
      model.addAttribute("menusNormal", menusNormal);
    }

    if (profile.getRole() != null && profile.getRole().equals("Volunteer - FDR")) {
      providers = profileService.findProviders();
      model.addAttribute("providers", providers);
    }

    return "registration_volunteer";

  }


  @PostMapping("/registration_member")

  public String saveRegistrationMember(
    Principal principal,
    Model model,
    @ModelAttribute("member") @Valid Profile member,
    @RequestParam(name = "password_confirm", required = true) String password_confirm) throws Exception {

    if (member.getPassword() != null && password_confirm != null && !member.getPassword().equals(password_confirm)) {
      throw new Exception("Password and Password (Confirm) do not match. Please amend then re-click button.");     	
    }

    member = profileService.saveProfile(member, null, null, null, null, null, null);

    model.addAttribute("member", member);

    // Note: Try scanning upload_url file

    final String UPLOAD_URL  = "localhost:4000";

//  File uploadUrlFile          = null;
//  Scanner uploadUrlScanner    = null;
    InputStream uploadUrlStream = null;
    String uploadUrlLine        = null;

    String upload_url           = null;

    try {

//    uploadUrlFile     = new ClassPathResource("config/upload_url.txt").getFile();
//    uploadUrlScanner  = new Scanner(uploadUrlFile);

//    if (uploadUrlScanner.hasNextLine()) {
//      uploadUrlLine   = uploadUrlScanner.nextLine();
//    }

      uploadUrlStream = new ClassPathResource("config/upload_url.txt").getInputStream();
        // Note: Switched to this technique which also works for WAR packaging

      uploadUrlLine   = new String(uploadUrlStream.readAllBytes());
        // Note: Switched to this technique which also works for WAR packaging 

      System.out.println("Scanned upload_url.txt in config: " + uploadUrlLine);

//    uploadUrlScanner.close();

    } catch(Exception e) {

      System.out.println(e.getMessage());

//    if (uploadUrlScanner != null) {
//      uploadUrlScanner.close();
//    }

    }

    // Note: If successful use overriding Upload URL
    //                else use default Upload URL

    if (uploadUrlLine == null || uploadUrlLine.equals("")) {
      upload_url = UPLOAD_URL;
    } else {
      upload_url = uploadUrlLine;
    }

    model.addAttribute("upload_url", upload_url);

    return "registration_member";

  }


  @PostMapping("/registration_partner")

  public String saveRegistrationPartner(
    Principal principal,
    Model model,
    @ModelAttribute("partner") @Valid Profile partner,
    @RequestParam(name = "menu_halal_id",    required = false) String menu_halal_id,
    @RequestParam(name = "menu_veg_id",      required = false) String menu_veg_id,
    @RequestParam(name = "menu_soft_id",     required = false) String menu_soft_id,
    @RequestParam(name = "menu_normal_id",   required = false) String menu_normal_id,
    @RequestParam(name = "provider_id",      required = false) String provider_id,
    @RequestParam(name = "password_confirm", required = true)  String password_confirm) throws Exception {

    List<Menu> menusHalal   = new ArrayList<>();
    List<Menu> menusVeg     = new ArrayList<>();
    List<Menu> menusSoft    = new ArrayList<>();
    List<Menu> menusNormal  = new ArrayList<>();

    List<Profile> providers = new ArrayList<>();

    if (partner.getPassword() != null && password_confirm != null && !partner.getPassword().equals(password_confirm)) {
        throw new Exception("Password and Password (Confirm) do not match. Please amend then re-click button.");     	
    }

    partner = profileService.saveProfile(partner, menu_halal_id, menu_veg_id, menu_soft_id, menu_normal_id, provider_id, null);

    model.addAttribute("partner", partner);

    if (partner.getRole() != null && partner.getRole().equals("Partner - FSP")) {
      menusHalal  = menuService.findMenus("Halal",      partner.getEmail());
      menusVeg    = menuService.findMenus("Vegetarian", partner.getEmail());
      menusSoft   = menuService.findMenus("Soft",       partner.getEmail());
      menusNormal = menuService.findMenus("Normal",     partner.getEmail());
      model.addAttribute("menusHalal",  menusHalal);
      model.addAttribute("menusVeg",    menusVeg);
      model.addAttribute("menusSoft",   menusSoft);
      model.addAttribute("menusNormal", menusNormal);
    }

    if (partner.getRole() != null && partner.getRole().equals("Partner - FDR")) {
      providers = profileService.findProviders();
      model.addAttribute("providers", providers);
    }

    return "registration_partner";

  }


  @PostMapping("/registration_volunteer")

  public String saveRegistrationVolunteer(
    Principal principal,
    Model model,
    @ModelAttribute("volunteer") @Valid Profile volunteer,
    @RequestParam(name = "menu_halal_id",    required = false) String menu_halal_id,
    @RequestParam(name = "menu_veg_id",      required = false) String menu_veg_id,
    @RequestParam(name = "menu_soft_id",     required = false) String menu_soft_id,
    @RequestParam(name = "menu_normal_id",   required = false) String menu_normal_id,
    @RequestParam(name = "provider_id",      required = false) String provider_id,
    @RequestParam(name = "password_confirm", required = true)  String password_confirm) throws Exception {

    List<Menu> menusHalal   = new ArrayList<>();
    List<Menu> menusVeg     = new ArrayList<>();
    List<Menu> menusSoft    = new ArrayList<>();
    List<Menu> menusNormal  = new ArrayList<>();

    List<Profile> providers = new ArrayList<>();

    if (volunteer.getPassword() != null && password_confirm != null && !volunteer.getPassword().equals(password_confirm)) {
        throw new Exception("Password and Password (Confirm) do not match. Please amend then re-click button.");     	
    }

    volunteer = profileService.saveProfile(volunteer, menu_halal_id, menu_veg_id, menu_soft_id, menu_normal_id, provider_id, null);

    model.addAttribute("volunteer", volunteer);

    if (volunteer.getRole() != null && volunteer.getRole().equals("Volunteer - FSP")) {
      menusHalal  = menuService.findMenus("Halal",      volunteer.getEmail());
      menusVeg    = menuService.findMenus("Vegetarian", volunteer.getEmail());
      menusSoft   = menuService.findMenus("Soft",       volunteer.getEmail());
      menusNormal = menuService.findMenus("Normal",     volunteer.getEmail());
      model.addAttribute("menusHalal",  menusHalal);
      model.addAttribute("menusVeg",    menusVeg);
      model.addAttribute("menusSoft",   menusSoft);
      model.addAttribute("menusNormal", menusNormal);
    }

    if (volunteer.getRole() != null && volunteer.getRole().equals("Volunteer - FDR")) {
      providers = profileService.findProviders();
      model.addAttribute("providers", providers);
    }

    return "registration_volunteer";

  }


  @GetMapping("/registration_member_caregivers")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showRegistrationMemberCaregivers(
    Principal principal,
    Model model,
    @RequestParam(name = "member_id", required = true) String member_id) {

    Profile member = new Profile();

    List<Profile> caregivers = new ArrayList<>();

    if (member_id != null && !member_id.equals("")) {

      member = profileService.findProfileById(member_id);

      caregivers = profileService.findCaregivers(member_id);

    }

    model.addAttribute("caregivers", caregivers);

    model.addAttribute("member", member);

    return "registration_member_caregivers";

  }

 ********************************************************************************/

  public String showRegistrationMemberCaregivers(
    Principal principal,
    Model model,
    @RequestParam(name = "member_id", required = true) String member_id,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    Profile member = new Profile();

    List<Profile> caregivers = new ArrayList<>();
    int pages = 0;

    Page<Profile> items = null;

    if (member_id != null && !member_id.equals("")) {

      member = profileService.findProfileById(member_id);

      items = profileService.findCaregivers(member_id, page, size);

      caregivers = items.getContent();
      pages      = items.getTotalPages();

    }

    model.addAttribute("caregivers", caregivers);
    model.addAttribute("pages",      pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    model.addAttribute("member", member);

    return "registration_member_caregivers";

  }


  @PostMapping("/registration_member_caregivers")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showRegistrationMemberCaregivers(
    Principal principal,
    Model model,
    @ModelAttribute("member") @Valid Profile member,
    @RequestParam(name = "password_confirm", required = true) String password_confirm) throws Exception {

    List<Profile> caregivers = new ArrayList<>();

    if (member.getPassword() != null && password_confirm != null && !member.getPassword().equals(password_confirm)) {
      throw new Exception("Password and Password (Confirm) do not match. Please amend then re-click button.");     	
    }

    member = profileService.saveProfile(member, null, null, null, null, null, null);

    caregivers = profileService.findCaregivers(Integer.toString(member.getId()));
 
    model.addAttribute("caregivers", caregivers);

    model.addAttribute("member", member);

    return "registration_member_caregivers";

  }

 ********************************************************************************/

  public String showRegistrationMemberCaregivers(
    Principal principal,
    Model model,
    @ModelAttribute("member") @Valid Profile member,
    @RequestParam(name = "password_confirm", required = true) String password_confirm) throws Exception {

    List<Profile> caregivers = new ArrayList<>();
    int pages = 0;

    int page  = 0;
    int size  = Integer.parseInt(PAGINATION_SIZE);

    Page<Profile> items = null;

    if (member.getPassword() != null && password_confirm != null && !member.getPassword().equals(password_confirm)) {
      throw new Exception("Password and Password (Confirm) do not match. Please amend then re-click button.");     	
    }

    member = profileService.saveProfile(member, null, null, null, null, null, null);

    items = profileService.findCaregivers(Integer.toString(member.getId()), page, size);

    caregivers = items.getContent();
    pages      = items.getTotalPages();

    model.addAttribute("caregivers", caregivers);
    model.addAttribute("pages",      pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    model.addAttribute("member", member);

    return "registration_member_caregivers";

  }


  @PostMapping("/registration_member_caregivers_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteRegistrationMemberCaregivers(
    Principal principal,
    Model model,
    @RequestParam(name = "id",        required = true) String id,
    @RequestParam(name = "member_id", required = true) String member_id) {

    Profile member    = new Profile();
    Profile caregiver = new Profile();

    List<Profile> caregivers = new ArrayList<>();

    if (id != null & !id.equals("")) {

      caregiver = profileService.deleteCaregiverById(id, member_id);

    }

    if (member_id != null && !member_id.equals("")) {

      member = profileService.findProfileById(member_id);

      caregivers = profileService.findCaregivers(member_id);

    }

    model.addAttribute("caregivers", caregivers);

    model.addAttribute("member", member);

    return "registration_member_caregivers";

  }

 ********************************************************************************/

  public String deleteRegistrationMemberCaregivers(
    Principal principal,
    Model model,
    @RequestParam(name = "id",        required = true) String id,
    @RequestParam(name = "member_id", required = true) String member_id,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    Profile member    = new Profile();
    Profile caregiver = new Profile();

    List<Profile> caregivers = new ArrayList<>();
    int pages = 0;

    Page<Profile> items = null;

    if (id != null && !id.equals("")) {

      caregiver = profileService.deleteCaregiverById(id, member_id);

    }

    if (member_id != null && !member_id.equals("")) {

      member = profileService.findProfileById(member_id);

      items = profileService.findCaregivers(member_id, page, size);

      caregivers = items.getContent();
      pages      = items.getTotalPages();

    }

    model.addAttribute("caregivers", caregivers);
    model.addAttribute("pages",      pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    model.addAttribute("member", member);

    return "registration_member_caregivers";

  }


  @GetMapping("/registration_member_caregivers_caregiver")

  public String showRegistrationMemberCaregiversCaregiver(
    Principal principal,
    Model model,
    @RequestParam(name = "id",        required = false) String id,
    @RequestParam(name = "member_id", required = true)  String member_id) {

    Profile member    = new Profile();
    Profile caregiver = new Profile();

    if (member_id != null && !member_id.equals("")) {

      member = profileService.findProfileById(member_id);

    }

    if (id != null && !id.equals("")) {

      caregiver = profileService.findCaregiverById(id);

    }

    model.addAttribute("caregiver", caregiver);

    model.addAttribute("member", member);

    return "registration_member_caregivers_caregiver";

  }


  @GetMapping("/registration_member_caregivers_caregiver_retrieval")

  public String showRegistrationMemberCaregiversCaregiverRetrieval(
    Principal principal,
    Model model,
    @RequestParam(name = "nric_uen",  required = true) String nric_uen,
    @RequestParam(name = "member_id", required = true) String member_id) {

    Profile member    = new Profile();
    Profile profile   = new Profile();
    Profile caregiver = new Profile();

    if (member_id != null && !member_id.equals("")) {

      member = profileService.findProfileById(member_id);

    }

    if (nric_uen != null && !nric_uen.equals("")) {

      profile = profileService.findCaregiverByNric(nric_uen);

      if (profile != null) {
        caregiver = profile;
      } else {
        model.addAttribute("error", "Caregiver not found.");
      }

    }

    model.addAttribute("caregiver", caregiver);

    model.addAttribute("member", member);

    return "registration_member_caregivers_caregiver";

  }


  @PostMapping("/registration_member_caregivers_caregiver")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String saveRegistrationMemberCaregiversCaregiver(
    Principal principal,
    Model model,
    @ModelAttribute("caregiver") @Valid Profile caregiver,
    @RequestParam(name = "member_id", required = true) String member_id) {

    Profile member = new Profile();

    List<Profile> caregivers = new ArrayList<>();

    caregiver = profileService.saveProfile(caregiver, null, null, null, null, null, member_id);

    if (member_id != null && !member_id.equals("")) {

      member = profileService.findProfileById(member_id);

      caregivers = profileService.findCaregivers(member_id);

    }

    model.addAttribute("caregivers", caregivers);

    model.addAttribute("member", member);

    return "registration_member_caregivers";

  }

 ********************************************************************************/

  public String saveRegistrationMemberCaregiversCaregiver(
    Principal principal,
    Model model,
    @ModelAttribute("caregiver") @Valid Profile caregiver,
    @RequestParam(name = "member_id", required = true) String member_id) {

    Profile member = new Profile();

    List<Profile> caregivers = new ArrayList<>();
    int pages = 0;

    int page  = 0;
    int size  = Integer.parseInt(PAGINATION_SIZE);

    Page<Profile> items = null;

    caregiver = profileService.saveProfile(caregiver, null, null, null, null, null, member_id);

    if (member_id != null && !member_id.equals("")) {

      member = profileService.findProfileById(member_id);

      items = profileService.findCaregivers(member_id, page, size);

      caregivers = items.getContent();
      pages      = items.getTotalPages();

    }

    model.addAttribute("caregivers", caregivers);
    model.addAttribute("pages",      pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    model.addAttribute("member", member);

    return "registration_member_caregivers";

  }



}
