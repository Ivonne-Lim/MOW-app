/*
 * Class Name:  RegistrationControllerAnonymousTests
 * Description: WebMvcTest
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.controller;


import org.merrymeal.mow.entity.Profile;
import org.merrymeal.mow.entity.Menu;

import org.merrymeal.mow.service.ProfileService;
import org.merrymeal.mow.service.MenuService;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;


@WebMvcTest(RegistrationController.class)
@AutoConfigureMockMvc(addFilters = false)
  // ISSUE: Security filters not working for anonymous

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class RegistrationControllerAnonymousTests {



  @MockBean
  private ProfileService profileService;

  @MockBean
  private MenuService menuService;

  
  @Autowired
  private MockMvc mockMvc;



  final String PAGINATION_SIZE = "2";
    // Default and maximum should be 15 as measured.



  @Test
  @DisplayName("Test Unit #01: Anonymous user get(URL: /registration) should get rendering of view(name: registration)")

  @WithAnonymousUser

  void shouldShowRegistrationForAnonymous() throws Exception {

    mockMvc
      .perform(get("/registration"))
      .andDo(print())
      .andExpect(view().name("registration"));

  }


  @Test
  @DisplayName("Test Unit #02: Anonymous user get(URL: /registration_member) should get rendering of view(name: registration_member)")

  @WithAnonymousUser

  void shouldShowRegistrationMemberForAnonymous() throws Exception {

    Profile member0 = new Profile();

    mockMvc
      .perform(get("/registration_member"))
      .andDo(print())
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(member0.getId()))
        )
      ))
      .andExpect(model().attribute("upload_url",
        "localhost:4000"))
      .andExpect(view().name("registration_member"));

  }


  @Test
  @DisplayName("Test Unit #03: Anonymous user get(URL: /registration_partner) should get rendering of view(name: registration_partner)")

  @WithAnonymousUser

  void shouldShowRegistrationPartnerForAnonymous() throws Exception {

    Profile partner0 = new Profile();

    mockMvc
      .perform(get("/registration_partner"))
      .andDo(print())
      .andExpect(model().attribute("partner",
        allOf(
          hasProperty("id", equalTo(partner0.getId()))
        )
      ))
      .andExpect(view().name("registration_partner"));

  }


  @Test
  @DisplayName("Test Unit #04: Anonymous user get(URL: /registration_volunteer) should get rendering of view(name: registration_volunteer)")

  @WithAnonymousUser

  void shouldShowRegistrationVolunteerForAnonymous() throws Exception {

    Profile volunteer0 = new Profile();

    mockMvc
      .perform(get("/registration_volunteer"))
      .andDo(print())
      .andExpect(model().attribute("volunteer",
        allOf(
          hasProperty("id", equalTo(volunteer0.getId()))
        )
      ))
      .andExpect(view().name("registration_volunteer"));

  }


  @Test
  @DisplayName("Test Unit #05: Anonymous user post(URL: /registration_member) should get rendering of view(name: registration_member)")

  @WithAnonymousUser

  void shouldSaveRegistrationMemberForAnonymous() throws Exception {

    Profile member0 = new Profile();

    member0.setId(0);
    member0.setNric_uen("S1000001A");
    member0.setNames("Member #1");
    member0.setRole("Member");
    member0.setEmail("member1@gmail.com");
    member0.setPhone("10000001");
    member0.setAddress("55 Pipit Road");
    member0.setUnit("#11-08");
    member0.setPostal("370055");
    member0.setStatus("Pending");
    member0.setPassword("password");
    member0.setGender("Female");
    member0.setSurname("Test #1");
    member0.setDob(LocalDate.of(1971, 1, 1));
    member0.setDisabilities("Disability #1");
    member0.setIncome(1000);
    member0.setHousehold(1);
    member0.setDiet("Halal");
    member0.setAllergies("Allergy #1");

    Profile member2 = new Profile();

    member2.setId(2);
    member2.setNric_uen("S1000001A");
    member2.setNames("Member #1");
    member2.setRole("Member");
    member2.setEmail("member1@gmail.com");
    member2.setPhone("10000001");
    member2.setAddress("55 Pipit Road");
    member2.setUnit("#11-08");
    member2.setPostal("370055");
    member2.setStatus("Pending");
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    when(profileService.saveProfile(member0, null, null, null, null, null, null)).thenReturn(member2);

    mockMvc
    .perform(post("/registration_member")
               .with(csrf())
               .flashAttr("member", member0)
    		   .param("password_confirm", member0.getPassword()))
    .andDo(print())
    .andExpect(model().attribute("member",
      allOf(
        hasProperty("id",       equalTo(member2.getId())),
        hasProperty("password", equalTo(member2.getPassword())),
        hasProperty("created",  equalTo(member2.getCreated()))
      )
    ))
    .andExpect(model().attribute("upload_url", "localhost:4000"))
    .andExpect(view().name("registration_member"));

  }


  @Test
  @DisplayName("Test Unit #06: Anonymous user post(URL: /registration_partner) should get rendering of view(name: registration_partner)")

  @WithAnonymousUser

  void shouldSaveRegistrationPartnerForAnonymous() throws Exception {

    Profile partner0 = new Profile();

    partner0.setId(0);
    partner0.setNric_uen("300000001C");
    partner0.setNames("Partner #1");
    partner0.setRole("Partner - FSP");
    partner0.setEmail("partner1@gmail.com");
    partner0.setPhone("30000001");
    partner0.setAddress("408 Pasir Ris Drive 6");
    partner0.setUnit("#08-423");
    partner0.setPostal("510408");
    partner0.setStatus("Pending");
    partner0.setPassword("password");

    Profile partner7 = new Profile();

    partner7.setId(7);
    partner7.setNric_uen("300000001C");
    partner7.setNames("Partner #1");
    partner7.setRole("Partner - FSP");
    partner7.setEmail("partner1@gmail.com");
    partner7.setPhone("30000001");
    partner7.setAddress("408 Pasir Ris Drive 6");
    partner7.setUnit("#08-423");
    partner7.setPostal("510408");
    partner7.setStatus("Pending");
    partner7.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Menu> menusHalal   = new ArrayList<>();
    List<Menu> menusVeg     = new ArrayList<>();
    List<Menu> menusSoft    = new ArrayList<>();
    List<Menu> menusNormal  = new ArrayList<>();

    when(profileService.saveProfile(partner0, null, null, null, null, null, null)).thenReturn(partner7);

    when(menuService.findMenus("Halal",      partner7.getEmail())).thenReturn(menusHalal);
    when(menuService.findMenus("Vegetarian", partner7.getEmail())).thenReturn(menusVeg);
    when(menuService.findMenus("Soft",       partner7.getEmail())).thenReturn(menusSoft);
    when(menuService.findMenus("Normal",     partner7.getEmail())).thenReturn(menusNormal);

    mockMvc
    .perform(post("/registration_partner")
               .with(csrf())
               .flashAttr("partner", partner0)
    		   .param("password_confirm", partner0.getPassword()))
    .andDo(print())
    .andExpect(model().attribute("partner",
      allOf(
        hasProperty("id",       equalTo(partner7.getId())),
        hasProperty("password", equalTo(partner7.getPassword())),
        hasProperty("created",  equalTo(partner7.getCreated()))
      )
    ))
    .andExpect(model().attribute("menusHalal",  hasSize(menusHalal.size())))
    .andExpect(model().attribute("menusVeg",    hasSize(menusVeg.size())))
    .andExpect(model().attribute("menusSoft",   hasSize(menusSoft.size())))
    .andExpect(model().attribute("menusNormal", hasSize(menusNormal.size())))
    .andExpect(view().name("registration_partner"));

  }



  @Test
  @DisplayName("Test Unit #07: Anonymous user post(URL: /registration_volunteer) should get rendering of view(name: registration_volunteer)")

  @WithAnonymousUser

  void shouldSaveRegistrationVolunteerForAnonymous() throws Exception {

    Profile volunteer0 = new Profile();

    volunteer0.setId(0);
    volunteer0.setNric_uen("400000001D");
    volunteer0.setNames("Volunteer #1");
    volunteer0.setRole("Volunteer - FDR");
    volunteer0.setEmail("volunteer1@gmail.com");
    volunteer0.setPhone("40000001");
    volunteer0.setAddress("Volunteer Address #1");
    volunteer0.setUnit("");
    volunteer0.setPostal("400001");
    volunteer0.setStatus("Pending");
    volunteer0.setPassword("password");
    volunteer0.setGender("Male");
    volunteer0.setSurname("Test #1");

    Profile volunteer8 = new Profile();

    volunteer8.setId(8);
    volunteer8.setNric_uen("400000001D");
    volunteer8.setNames("Volunteer #1");
    volunteer8.setRole("Volunteer - FDR");
    volunteer8.setEmail("volunteer1@gmail.com");
    volunteer8.setPhone("40000001");
    volunteer8.setAddress("Volunteer Address #1");
    volunteer8.setUnit("");
    volunteer8.setPostal("400001");
    volunteer8.setStatus("Pending");
    volunteer8.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer8.setGender("Male");
    volunteer8.setSurname("Test #1");
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner7 = new Profile();

    partner7.setId(7);
    partner7.setNric_uen("300000001C");
    partner7.setNames("Partner #1");
    partner7.setRole("Partner - FSP");
    partner7.setEmail("partner1@gmail.com");
    partner7.setPhone("30000001");
    partner7.setAddress("408 Pasir Ris Drive 6");
    partner7.setUnit("#08-423");
    partner7.setPostal("510408");
    partner7.setStatus("Pending");
    partner7.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> providers = new ArrayList<>();

    providers.add(partner7);

    when(profileService.saveProfile(volunteer0, null, null, null, null, null, null)).thenReturn(volunteer8);

    when(profileService.findProviders()).thenReturn(providers);

    mockMvc
    .perform(post("/registration_volunteer")
               .with(csrf())
               .flashAttr("volunteer", volunteer0)
    		   .param("password_confirm", volunteer0.getPassword()))
    .andDo(print())
    .andExpect(model().attribute("volunteer",
      allOf(
        hasProperty("id",       equalTo(volunteer8.getId())),
        hasProperty("password", equalTo(volunteer8.getPassword())),
        hasProperty("created",  equalTo(volunteer8.getCreated()))
      )
    ))
    .andExpect(model().attribute("providers", hasSize(providers.size())))
    .andExpect(model().attribute("providers", hasItem(
      allOf(
        hasProperty("id", equalTo(partner7.getId()))
      )
    )))
    .andExpect(view().name("registration_volunteer"));

  }


  @Test
  @DisplayName("Test Unit #08: Anonymous user get(URL: /registration_member_caregivers) should get rendering of view(name: registration_member_caregivers)")

  @WithAnonymousUser

  void shouldShowRegistrationMemberCaregivers1ForAnonymous() throws Exception {

    Profile member2 = new Profile();

    member2.setId(2);
    member2.setNric_uen("S1000001A");
    member2.setNames("Member #1");
    member2.setRole("Member");
    member2.setEmail("member1@gmail.com");
    member2.setPhone("10000001");
    member2.setAddress("55 Pipit Road");
    member2.setUnit("#11-08");
    member2.setPostal("370055");
    member2.setStatus("Pending");
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> caregivers = new ArrayList<>();

    when(profileService.findProfileById(Integer.toString(member2.getId()))).thenReturn(member2);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findCaregivers(Integer.toString(member2.getId()))).thenReturn(caregivers);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(caregivers);

    when(profileService.findCaregivers(Integer.toString(member2.getId()), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

    mockMvc
      .perform(get("/registration_member_caregivers")
   	            .param("member_id", Integer.toString(member2.getId())))
      .andDo(print())
      .andExpect(model().attribute("caregivers", hasSize(caregivers.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(member2.getId()))
        )
      ))
      .andExpect(view().name("registration_member_caregivers"));

  }


  @Test
  @DisplayName("Test Unit #09: Anonymous user post(URL: /registration_member_caregivers) should get rendering of view(name: registration_member_caregivers)")

  @WithAnonymousUser

  void shouldShowRegistrationMemberCaregivers2ForAnonymous() throws Exception {

    Profile member0 = new Profile();

    member0.setId(0);
    member0.setNric_uen("S1000001A");
    member0.setNames("Member #1");
    member0.setRole("Member");
    member0.setEmail("member1@gmail.com");
    member0.setPhone("10000001");
    member0.setAddress("55 Pipit Road");
    member0.setUnit("#11-08");
    member0.setPostal("370055");
    member0.setStatus("Pending");
    member0.setPassword("password");
    member0.setGender("Female");
    member0.setSurname("Test #1");
    member0.setDob(LocalDate.of(1971, 1, 1));
    member0.setDisabilities("Disability #1");
    member0.setIncome(1000);
    member0.setHousehold(1);
    member0.setDiet("Halal");
    member0.setAllergies("Allergy #1");

    Profile member2 = new Profile();

    member2.setId(2);
    member2.setNric_uen("S1000001A");
    member2.setNames("Member #1");
    member2.setRole("Member");
    member2.setEmail("member1@gmail.com");
    member2.setPhone("10000001");
    member2.setAddress("55 Pipit Road");
    member2.setUnit("#11-08");
    member2.setPostal("370055");
    member2.setStatus("Pending");
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> caregivers = new ArrayList<>();

    when(profileService.saveProfile(member0, null, null, null, null, null, null)).thenReturn(member2);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findCaregivers(Integer.toString(member2.getId()))).thenReturn(caregivers);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(caregivers);

    when(profileService.findCaregivers(Integer.toString(member2.getId()), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

    mockMvc
      .perform(post("/registration_member_caregivers")
                .with(csrf())
                .flashAttr("member", member0)
   	            .param("password_confirm", member0.getPassword()))
      .andDo(print())
      .andExpect(model().attribute("caregivers", hasSize(caregivers.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(member2.getId()))
        )
      ))
      .andExpect(view().name("registration_member_caregivers"));

  }


  @Test
  @DisplayName("Test Unit #10: Anonymous user post(URL: /registration_member_caregivers_deletion) should get rendering of view(name: registration_member_caregivers)")

  @WithAnonymousUser

  void shouldDeleteRegistrationMemberCaregiversForAnonymous() throws Exception {

    Profile member2 = new Profile();

    member2.setId(2);
    member2.setNric_uen("S1000001A");
    member2.setNames("Member #1");
    member2.setRole("Member");
    member2.setEmail("member1@gmail.com");
    member2.setPhone("10000001");
    member2.setAddress("55 Pipit Road");
    member2.setUnit("#11-08");
    member2.setPostal("370055");
    member2.setStatus("Pending");
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile member2u = new Profile();

    member2u.setId(2);
    member2u.setNric_uen("S1000001A");
    member2u.setNames("Member #1");
    member2u.setRole("Member");
    member2u.setEmail("member1@gmail.com");
    member2u.setPhone("10000001");
    member2u.setAddress("55 Pipit Road");
    member2u.setUnit("#11-08");
    member2u.setPostal("370055");
    member2u.setStatus("Pending");
    member2u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2u.setGender("Female");
    member2u.setSurname("Test #1");
    member2u.setDob(LocalDate.of(1971, 1, 1));
    member2u.setDisabilities("Disability #1");
    member2u.setIncome(1000);
    member2u.setHousehold(1);
    member2u.setDiet("Halal");
    member2u.setAllergies("Allergy #1");
    member2u.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2u.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> members0 = new ArrayList<>();
    List<Profile> members1 = new ArrayList<>();

    members1.add(member2);

    Profile caregiver3 = new Profile();

    caregiver3.setId(3);
    caregiver3.setNric_uen("S2000001B");
    caregiver3.setNames("Caregiver #1");
    caregiver3.setRole("Caregiver");
    caregiver3.setEmail("caregiver1@gmail.com");
    caregiver3.setPhone("20000001");
    caregiver3.setAddress("Caregiver Address #1");
    caregiver3.setUnit("");
    caregiver3.setPostal("200001");
    caregiver3.setStatus("Pending");
    caregiver3.setGender("Male");
    caregiver3.setSurname("Test #1");
    caregiver3.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    caregiver3.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile caregiver3d = new Profile();

    caregiver3d.setId(3);
    caregiver3d.setNric_uen("S2000001B");
    caregiver3d.setNames("Caregiver #1");
    caregiver3d.setRole("Caregiver");
    caregiver3d.setEmail("caregiver1@gmail.com");
    caregiver3d.setPhone("20000001");
    caregiver3d.setAddress("Caregiver Address #1");
    caregiver3d.setUnit("");
    caregiver3d.setPostal("200001");
    caregiver3d.setStatus("Pending");
    caregiver3d.setGender("Male");
    caregiver3d.setSurname("Test #1");
    caregiver3d.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    caregiver3d.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> caregivers0 = new ArrayList<>();
    List<Profile> caregivers1 = new ArrayList<>();

    caregivers1.add(caregiver3);

    member2.setCaregivers(caregivers1);
    member2u.setCaregivers(caregivers0);

    caregiver3.setMembers(members1);
    caregiver3d.setMembers(members0);

    when(profileService.deleteCaregiverById(Integer.toString(caregiver3.getId()), Integer.toString(member2.getId()))).thenReturn(caregiver3d);

    when(profileService.findProfileById(Integer.toString(member2u.getId()))).thenReturn(member2u);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findCaregivers(Integer.toString(member2u.getId()))).thenReturn(caregivers0);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(caregivers0);

    when(profileService.findCaregivers(Integer.toString(member2u.getId()), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

    mockMvc
      .perform(post("/registration_member_caregivers_deletion")
                .with(csrf())
   	            .param("id", Integer.toString(caregiver3.getId()))
   	            .param("member_id", Integer.toString(member2.getId())))
      .andDo(print())
      .andExpect(model().attribute("caregivers", hasSize(caregivers0.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(member2u.getId()))
        )
      ))
      .andExpect(view().name("registration_member_caregivers"));

  }


  @Test
  @DisplayName("Test Unit #11: Anonymous user get(URL: /registration_member_caregivers_caregiver) should get rendering of view(name: registration_member_caregivers_caregiver)")

  @WithAnonymousUser

  void shouldShowRegistrationMemberCaregiversCaregiverForAnonymous() throws Exception {

    Profile member2 = new Profile();

    member2.setId(2);
    member2.setNric_uen("S1000001A");
    member2.setNames("Member #1");
    member2.setRole("Member");
    member2.setEmail("member1@gmail.com");
    member2.setPhone("10000001");
    member2.setAddress("55 Pipit Road");
    member2.setUnit("#11-08");
    member2.setPostal("370055");
    member2.setStatus("Pending");
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile caregiver0 = new Profile();

    List<Profile> caregivers0 = new ArrayList<>();

    member2.setCaregivers(caregivers0);

    when(profileService.findProfileById(Integer.toString(member2.getId()))).thenReturn(member2);

    mockMvc
      .perform(get("/registration_member_caregivers_caregiver")
   	            .param("member_id", Integer.toString(member2.getId())))
      .andDo(print())
      .andExpect(model().attribute("caregiver",
   	    allOf(
          hasProperty("id", equalTo(caregiver0.getId()))
        )
      ))
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(member2.getId()))
        )
      ))
      .andExpect(view().name("registration_member_caregivers_caregiver"));

  }


  @Test
  @DisplayName("Test Unit #12: Anonymous user get(URL: /registration_member_caregivers_caregiver_retrieval) should get rendering of view(name: registration_member_caregivers_caregiver)")

  @WithAnonymousUser

  void shouldShowRegistrationMemberCaregiversCaregiverRetrievalForAnonymous() throws Exception {

    Profile member2 = new Profile();

    member2.setId(2);
    member2.setNric_uen("S1000001A");
    member2.setNames("Member #1");
    member2.setRole("Member");
    member2.setEmail("member1@gmail.com");
    member2.setPhone("10000001");
    member2.setAddress("55 Pipit Road");
    member2.setUnit("#11-08");
    member2.setPostal("370055");
    member2.setStatus("Pending");
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile member5 = new Profile();

    member5.setId(5);
    member5.setNric_uen("S1000002A");
    member5.setNames("Member #2");
    member5.setRole("Member");
    member5.setEmail("member2@gmail.com");
    member5.setPhone("10000002");
    member5.setAddress("334 Sembawang Close");
    member5.setUnit("#12-455");
    member5.setPostal("750334");
    member5.setStatus("Pending");
    member5.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member5.setGender("Female");
    member5.setSurname("Test #2");
    member5.setDob(LocalDate.of(1971, 2, 1));
    member5.setDisabilities("Disability #2");
    member5.setIncome(2000);
    member5.setHousehold(2);
    member5.setDiet("Normal");
    member5.setAllergies("");
    member5.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> members1 = new ArrayList<>();

    members1.add(member2);

    Profile caregiver3 = new Profile();

    caregiver3.setId(3);
    caregiver3.setNric_uen("S2000001B");
    caregiver3.setNames("Caregiver #1");
    caregiver3.setRole("Caregiver");
    caregiver3.setEmail("caregiver1@gmail.com");
    caregiver3.setPhone("20000001");
    caregiver3.setAddress("Caregiver Address #1");
    caregiver3.setUnit("");
    caregiver3.setPostal("200001");
    caregiver3.setStatus("Pending");
    caregiver3.setGender("Male");
    caregiver3.setSurname("Test #1");
    caregiver3.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    caregiver3.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> caregivers0 = new ArrayList<>();
    List<Profile> caregivers1 = new ArrayList<>();

    caregivers1.add(caregiver3);

 // member2.setCaregivers(caregivers1);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.
    member5.setCaregivers(caregivers0);

 // caregiver3.setMembers(members1);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.
    
    when(profileService.findProfileById(Integer.toString(member5.getId()))).thenReturn(member5);

    when(profileService.findCaregiverByNric(caregiver3.getNric_uen())).thenReturn(caregiver3);

    mockMvc
      .perform(get("/registration_member_caregivers_caregiver_retrieval")
                .param("nric_uen", caregiver3.getNric_uen())
   	            .param("member_id", Integer.toString(member5.getId())))
      .andDo(print())
      .andExpect(model().attribute("caregiver",
        allOf(
          hasProperty("id", equalTo(caregiver3.getId()))
        )
      ))
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(member5.getId()))
        )
      ))
      .andExpect(view().name("registration_member_caregivers_caregiver"));

  }


  @Test
  @DisplayName("Test Unit #13: Anonymous user post(URL: /registration_member_caregivers_caregiver) should get rendering of view(name: registration_member_caregivers)")

  @WithAnonymousUser

  void shouldSaveRegistrationMemberCaregiversCaregiverForAnonymous() throws Exception {

    Profile member2 = new Profile();

    member2.setId(2);
    member2.setNric_uen("S1000001A");
    member2.setNames("Member #1");
    member2.setRole("Member");
    member2.setEmail("member1@gmail.com");
    member2.setPhone("10000001");
    member2.setAddress("55 Pipit Road");
    member2.setUnit("#11-08");
    member2.setPostal("370055");
    member2.setStatus("Pending");
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile member2u = new Profile();

    member2u.setId(2);
    member2u.setNric_uen("S1000001A");
    member2u.setNames("Member #1");
    member2u.setRole("Member");
    member2u.setEmail("member1@gmail.com");
    member2u.setPhone("10000001");
    member2u.setAddress("55 Pipit Road");
    member2u.setUnit("#11-08");
    member2u.setPostal("370055");
    member2u.setStatus("Pending");
    member2u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2u.setGender("Female");
    member2u.setSurname("Test #1");
    member2u.setDob(LocalDate.of(1971, 1, 1));
    member2u.setDisabilities("Disability #1");
    member2u.setIncome(1000);
    member2u.setHousehold(1);
    member2u.setDiet("Halal");
    member2u.setAllergies("Allergy #1");
    member2u.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2u.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> members0 = new ArrayList<>();
    List<Profile> members1 = new ArrayList<>();

    members1.add(member2u);

    Profile caregiver0 = new Profile();

    caregiver0.setId(0);
    caregiver0.setNric_uen("S2000001B");
    caregiver0.setNames("Caregiver #1");
    caregiver0.setRole("Caregiver");
    caregiver0.setEmail("caregiver11@gmail.com");
    caregiver0.setPhone("20000001");
    caregiver0.setAddress("Caregiver Address #1");
    caregiver0.setUnit("");
    caregiver0.setPostal("200001");
    caregiver0.setStatus("Pending");
    caregiver0.setGender("Male");
    caregiver0.setSurname("Test #1");

    Profile caregiver3 = new Profile();

    caregiver3.setId(3);
    caregiver3.setNric_uen("S2000001B");
    caregiver3.setNames("Caregiver #1");
    caregiver3.setRole("Caregiver");
    caregiver3.setEmail("caregiver1@gmail.com");
    caregiver3.setPhone("20000001");
    caregiver3.setAddress("Caregiver Address #1");
    caregiver3.setUnit("");
    caregiver3.setPostal("200001");
    caregiver3.setStatus("Pending");
    caregiver3.setGender("Male");
    caregiver3.setSurname("Test #1");
    caregiver3.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> caregivers0 = new ArrayList<>();
    List<Profile> caregivers1 = new ArrayList<>();

    caregivers1.add(caregiver3);

    member2.setCaregivers(caregivers0);
 // member2u.setCaregivers(caregivers1);
      // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    caregiver0.setMembers(members0);
 // caregiver3.setMembers(members1);
      // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    when(profileService.saveProfile(caregiver0, null, null, null, null, null, Integer.toString(member2.getId()))).thenReturn(caregiver3);

    when(profileService.findProfileById(Integer.toString(member2u.getId()))).thenReturn(member2u);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findCaregivers(Integer.toString(member2u.getId()))).thenReturn(caregivers1);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(caregivers1);

    when(profileService.findCaregivers(Integer.toString(member2u.getId()), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

    mockMvc
    .perform(post("/registration_member_caregivers_caregiver")
               .with(csrf())
               .flashAttr("caregiver", caregiver0)
    		   .param("member_id", Integer.toString(member2.getId())))
    .andDo(print())
    .andExpect(model().attribute("caregivers", hasSize(caregivers1.size())))
    .andExpect(model().attribute("caregivers", hasItem(
      allOf(
        hasProperty("id", equalTo(caregiver3.getId()))
      )
    )))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
    .andExpect(model().attribute("pages",   equalTo(1)))
    .andExpect(model().attribute("current", equalTo(0)))
    .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
    .andExpect(model().attribute("member",
      allOf(
        hasProperty("id", equalTo(member2u.getId()))
      )
    ))
    .andExpect(view().name("registration_member_caregivers"));

  }



}
