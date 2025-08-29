/*
 * Class Name:  EvaluationController
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

public class EvaluationController {



  @Autowired
  private ProfileService profileService;



  private final String PAGINATION_SIZE = "2";
    // Note: Default and maximum should be "15" as measured.



  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @GetMapping("/evaluation")

  public String showEvaluation(
    Principal principal,
    Model model) {

    return "evaluation";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @GetMapping("/evaluation_members")

/********************************************************************************
 Note: Switched to simple pagination.
       See above PAGINATION_SIZE constant.
 ********************************************************************************

  public String showEvaluationMembers(
    Principal principal,
    Model model) {

    List<Profile> members = new ArrayList<>();

    members = profileService.findMembers();

    model.addAttribute("members", members);

    return "evaluation_members";

  }

 ********************************************************************************/

  public String showEvaluationMembers(
    Principal principal,
    Model model,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    List<Profile> members = new ArrayList<>();
    int pages = 0;

    Page<Profile> items = null;

    items = profileService.findMembers(page, size);

    members = items.getContent();
    pages   = items.getTotalPages();

    model.addAttribute("members", members);
    model.addAttribute("pages",   pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "evaluation_members";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @GetMapping("/evaluation_partners")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showEvaluationPartners(
    Principal principal,
    Model model) {

    List<Profile> partners = new ArrayList<>();

    partners = profileService.findPartners();

    model.addAttribute("partners", partners);

    return "evaluation_partners";

  }

 ********************************************************************************/

  public String showEvaluationPartners(
    Principal principal,
    Model model,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    List<Profile> partners = new ArrayList<>();
    int pages = 0;

    Page<Profile> items = null;

    items = profileService.findPartners(page, size);

    partners = items.getContent();
    pages    = items.getTotalPages();

    model.addAttribute("partners", partners);
    model.addAttribute("pages",    pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "evaluation_partners";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @GetMapping("/evaluation_volunteers")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showEvaluationVolunteers(
    Principal principal,
    Model model) {

    List<Profile> volunteers = new ArrayList<>();

    volunteers = profileService.findVolunteers();

    model.addAttribute("volunteers", volunteers);

    return "evaluation_volunteers";

  }

 ********************************************************************************/

  public String showEvaluationVolunteers(
    Principal principal,
    Model model,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    List<Profile> volunteers = new ArrayList<>();
    int pages = 0;

    Page<Profile> items = null;

    items = profileService.findVolunteers(page, size);

    volunteers = items.getContent();
    pages      = items.getTotalPages();

    model.addAttribute("volunteers", volunteers);
    model.addAttribute("pages",      pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "evaluation_volunteers";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @PostMapping("/evaluation_members_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteEvaluationMembers(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id) {

    Profile member = new Profile();

    List<Profile> members = new ArrayList<>();

    if (id != null && !id.equals("")) {
      member = profileService.deleteProfileById(id);
    }

    members = profileService.findMembers();

    model.addAttribute("members", members);

    return "evaluation_members";

  }

 ********************************************************************************/

  public String deleteEvaluationMembers(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    Profile member = new Profile();

    List<Profile> members = new ArrayList<>();
    int pages = 0;

    Page<Profile> items = null;

    if (id != null && !id.equals("")) {
      member = profileService.deleteProfileById(id);
    }

    items = profileService.findMembers(page, size);

    members = items.getContent();
    pages   = items.getTotalPages();

    model.addAttribute("members", members);
    model.addAttribute("pages",   pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "evaluation_members";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @PostMapping("/evaluation_partners_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @PostMapping("/evaluation_partners_deletion")

  public String deleteEvaluationPartners(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id) {

    Profile partner = new Profile();

    List<Profile> partners = new ArrayList<>();

    if (id != null && !id.equals("")) {
      partner = profileService.deleteProfileById(id);
    }

    partners = profileService.findPartners();

    model.addAttribute("partners", partners);

    return "evaluation_partners";

  }

 ********************************************************************************/

  public String deleteEvaluationPartners(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    Profile partner = new Profile();

    List<Profile> partners = new ArrayList<>();
    int pages = 0;

    Page<Profile> items = null;

    if (id != null && !id.equals("")) {
      partner = profileService.deleteProfileById(id);
    }

    items = profileService.findPartners(page, size);

    partners = items.getContent();
    pages    = items.getTotalPages();

    model.addAttribute("partners", partners);
    model.addAttribute("pages",    pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "evaluation_partners";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @PostMapping("/evaluation_volunteers_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteEvaluationVolunteers(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id) {

    Profile volunteer = new Profile();

    List<Profile> volunteers = new ArrayList<>();

    if (id != null && !id.equals("")) {
      volunteer = profileService.deleteProfileById(id);
    }

    volunteers = profileService.findVolunteers();

    model.addAttribute("volunteers", volunteers);

    return "evaluation_volunteers";

  }

 ********************************************************************************/

  public String deleteEvaluationVolunteers(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    Profile volunteer = new Profile();

    List<Profile> volunteers = new ArrayList<>();
    int pages = 0;

    Page<Profile> items = null;

    if (id != null && !id.equals("")) {
      volunteer = profileService.deleteProfileById(id);
    }

    items = profileService.findVolunteers(page, size);

    volunteers = items.getContent();
    pages      = items.getTotalPages();

    model.addAttribute("volunteers", volunteers);
    model.addAttribute("pages",      pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "evaluation_volunteers";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @GetMapping("/evaluation_members_member")

  public String showEvaluationMembersMember(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id) {

    Profile profile = new Profile();

    if (id != null && !id.equals("")) {
      profile = profileService.findProfileById(id);
    }

    if (profile.getRole() != null && profile.getRole().equals("Member")) {
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

    return "evaluation_members_member";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @GetMapping("/evaluation_partners_partner")

  public String showEvaluationPartnersPartner(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id) {

    Profile profile = new Profile();

    if (id != null && !id.equals("")) {
      profile = profileService.findProfileById(id);
    }

    if (profile.getRole() != null && (profile.getRole().equals("Partner - FSP") || profile.getRole().equals("Partner - FDR"))) {
      model.addAttribute("partner", profile);
    }

    return "evaluation_partners_partner";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @GetMapping("/evaluation_volunteers_volunteer")

  public String showEvaluationVolunteersVolunteer(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id) {

    Profile profile = new Profile();

    if (id != null && !id.equals("")) {
      profile = profileService.findProfileById(id);
    }

    if (profile.getRole() != null && (profile.getRole().equals("Volunteer - FSP") || profile.getRole().equals("Volunteer - FDR"))) {
      model.addAttribute("volunteer", profile);
    }

    return "evaluation_volunteers_volunteer";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @PostMapping("/evaluation_members_member")

  public String saveEvaluationMembersMember(
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

    return "evaluation_members_member";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @PostMapping("/evaluation_partners_partner")

  public String saveEvaluationPartnersPartner(
    Principal principal,
    Model model,
    @ModelAttribute("partner") @Valid Profile partner,
    @RequestParam(name = "menu_halal_id",    required = false) String menu_halal_id,
    @RequestParam(name = "menu_veg_id",      required = false) String menu_veg_id,
    @RequestParam(name = "menu_soft_id",     required = false) String menu_soft_id,
    @RequestParam(name = "menu_normal_id",   required = false) String menu_normal_id,
    @RequestParam(name = "provider_id",      required = false) String provider_id,
    @RequestParam(name = "password_confirm", required = true)  String password_confirm) throws Exception {

    if (partner.getPassword() != null && password_confirm != null && !partner.getPassword().equals(password_confirm)) {
      throw new Exception("Password and Password (Confirm) do not match. Please amend then re-click button.");     	
    }

    partner = profileService.saveProfile(partner, menu_halal_id, menu_veg_id, menu_soft_id, menu_normal_id, provider_id, null);

    model.addAttribute("partner", partner);

    return "evaluation_partners_partner";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @PostMapping("/evaluation_volunteers_volunteer")

  public String saveEvaluationVolunteer(
    Principal principal,
    Model model,
    @ModelAttribute("volunteer") @Valid Profile volunteer,
    @RequestParam(name = "menu_halal_id",    required = false) String menu_halal_id,
    @RequestParam(name = "menu_veg_id",      required = false) String menu_veg_id,
    @RequestParam(name = "menu_soft_id",     required = false) String menu_soft_id,
    @RequestParam(name = "menu_normal_id",   required = false) String menu_normal_id,
    @RequestParam(name = "provider_id",      required = false) String provider_id,
    @RequestParam(name = "password_confirm", required = true)  String password_confirm) throws Exception {

    if (volunteer.getPassword() != null && password_confirm != null && !volunteer.getPassword().equals(password_confirm)) {
      throw new Exception("Password and Password (Confirm) do not match. Please amend then re-click button.");     	
    }

    volunteer = profileService.saveProfile(volunteer, menu_halal_id, menu_veg_id, menu_soft_id, menu_normal_id, provider_id, null);

    model.addAttribute("volunteer", volunteer);

    return "evaluation_volunteers_volunteer";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @GetMapping("/evaluation_members_member_caregivers")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showEvaluationMemberCaregivers(
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

    return "evaluation_members_member_caregivers";

  }

 ********************************************************************************/

  public String showEvaluationMemberCaregivers(
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

    return "evaluation_members_member_caregivers";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @PostMapping("/evaluation_members_member_caregivers")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showEvaluationMembersMemberCaregivers(
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

    return "evaluation_members_member_caregivers";

  }

 ********************************************************************************/

  public String showEvaluationMembersMemberCaregivers(
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

    return "evaluation_members_member_caregivers";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @PostMapping("/evaluation_members_member_caregivers_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteEvaluationMembersMemberCaregivers(
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

    return "evaluation_members_member_caregivers";

  }

 ********************************************************************************/

  public String deleteEvaluationMembersMemberCaregivers(
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

    return "evaluation_members_member_caregivers";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @GetMapping("/evaluation_members_member_caregivers_caregiver")

  public String showEvaluationMembersMemberCaregiversCaregiver(
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

    return "evaluation_members_member_caregivers_caregiver";

  }


  @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
  @PostMapping("/evaluation_members_member_caregivers_caregiver")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String saveEvaluationMembersMemberCaregiversCaregiver(
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

    return "evaluation_members_member_caregivers";

  }

 ********************************************************************************/

  public String saveEvaluationMembersMemberCaregiversCaregiver(
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

    return "evaluation_members_member_caregivers";

  }



}
