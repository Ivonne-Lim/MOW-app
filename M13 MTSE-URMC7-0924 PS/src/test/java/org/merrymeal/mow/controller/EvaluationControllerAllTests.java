/*
 * Class Name:  EvaluationControllerAllTests
 * Description: WebMvcTest
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.controller;


import org.merrymeal.mow.entity.Profile;

import org.merrymeal.mow.service.ProfileService;

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

import org.springframework.security.test.context.support.WithMockUser;

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


@WebMvcTest(EvaluationController.class)
@AutoConfigureMockMvc(addFilters = true)

@TestMethodOrder(MethodOrderer.DisplayName.class)


public class EvaluationControllerAllTests {



  @MockBean
  private ProfileService profileService;


  @Autowired
  private MockMvc mockMvc;



  final String PAGINATION_SIZE = "2";
    // Default and maximum should be 15 as measured.



  @Test
  @DisplayName("Test Unit #01: Administrator user get(URL: /evaluation) should get rendering of view(name: evaluation)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowEvaluationForAdministrator() throws Exception {

    mockMvc
      .perform(get("/evaluation"))
      .andDo(print())
      .andExpect(view().name("evaluation"));

  }

 
  @Test
  @DisplayName("Test Unit #02: Administrator user get(URL: /evaluation_members) should get rendering of view(name: evaluation_members)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowEvaluationMembersForAdministrator() throws Exception {

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

    List<Profile> members = new ArrayList<>();

    members.add(member2);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findMembers()).thenReturn(members);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(members);

    when(profileService.findMembers(0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/evaluation_members"))
      .andDo(print())
      .andExpect(model().attribute("members", hasSize(members.size())))
      .andExpect(model().attribute("members", hasItem(
        allOf(
          hasProperty("id", equalTo(member2.getId()))
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
      .andExpect(view().name("evaluation_members"));

  }


  @Test
  @DisplayName("Test Unit #03: Administrator user get(URL: /evaluation_partners) should get rendering of view(name: evaluation_partners)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowEvaluationPartnersForAdministrator() throws Exception {

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

    List<Profile> partners = new ArrayList<>();

    partners.add(partner7);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findPartners()).thenReturn(partners);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(partners);

    when(profileService.findPartners(0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/evaluation_partners"))
      .andDo(print())
      .andExpect(model().attribute("partners", hasSize(partners.size())))
      .andExpect(model().attribute("partners", hasItem(
        allOf(
          hasProperty("id", equalTo(partner7.getId()))
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
      .andExpect(view().name("evaluation_partners"));

  }


  @Test
  @DisplayName("Test Unit #04: Administrator user get(URL: /evaluation_volunteers) should get rendering of view(name: evaluation_volunteers)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowEvaluationVolunteersForAdministrator() throws Exception {

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

    volunteer8.setProvider(partner7);

    List<Profile> volunteers = new ArrayList<>();

    volunteers.add(volunteer8);

 /********************************************************************************
    Note: Switched to simple pagination.
          See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findVolunteers()).thenReturn(volunteers);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(volunteers);

    when(profileService.findVolunteers(0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/evaluation_volunteers"))
      .andDo(print())
      .andExpect(model().attribute("volunteers", hasSize(volunteers.size())))
      .andExpect(model().attribute("volunteers", hasItem(
        allOf(
          hasProperty("id", equalTo(volunteer8.getId()))
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
      .andExpect(view().name("evaluation_volunteers"));

  }


  @Test
  @DisplayName("Test Unit #05: Administrator user post(URL: /evaluation_members_deletion) should get rendering of view(name: evaluation_members)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldDeleteEvaluationMembersForAdministrator() throws Exception {

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

    Profile member2d = new Profile();

    member2d.setId(2);
    member2d.setNric_uen("S1000001A");
    member2d.setNames("Member #1");
    member2d.setRole("Member");
    member2d.setEmail("member1@gmail.com");
    member2d.setPhone("10000001");
    member2d.setAddress("55 Pipit Road");
    member2d.setUnit("#11-08");
    member2d.setPostal("370055");
    member2d.setStatus("Deleted");
    member2d.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2d.setGender("Female");
    member2d.setSurname("Test #1");
    member2d.setDob(LocalDate.of(1971, 1, 1));
    member2d.setDisabilities("Disability #1");
    member2d.setIncome(1000);
    member2d.setHousehold(1);
    member2d.setDiet("Halal");
    member2d.setAllergies("Allergy #1");
    member2d.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2d.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> members0 = new ArrayList<>();
    List<Profile> members1 = new ArrayList<>();

    members1.add(member2);

    when(profileService.deleteProfileById(Integer.toString(member2.getId()))).thenReturn(member2d);

 /********************************************************************************
   Note: Switched to simple pagination.
         See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findMembers()).thenReturn(members0);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(members0);

    when(profileService.findMembers(0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/evaluation_members_deletion")
                .with(csrf())
   		        .param("id", Integer.toString(member2.getId())))
      .andDo(print())
      .andExpect(model().attribute("members", hasSize(members0.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("evaluation_members"));

  }


  @Test
  @DisplayName("Test Unit #06: Administrator user post(URL: /evaluation_partners_deletion) should get rendering of view(name: evaluation_partners)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldDeleteEvaluationPartnersForAdministrator() throws Exception {

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

    Profile partner7d = new Profile();

    partner7d.setId(7);
    partner7d.setNric_uen("300000001C");
    partner7d.setNames("Partner #1");
    partner7d.setRole("Partner - FSP");
    partner7d.setEmail("partner1@gmail.com");
    partner7d.setPhone("30000001");
    partner7d.setAddress("408 Pasir Ris Drive 6");
    partner7d.setUnit("#08-423");
    partner7d.setPostal("510408");
    partner7d.setStatus("Deleted");
    partner7d.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner7d.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> partners0 = new ArrayList<>();
    List<Profile> partners1 = new ArrayList<>();

    partners1.add(partner7);

    when(profileService.deleteProfileById(Integer.toString(partner7.getId()))).thenReturn(partner7d);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findPartners()).thenReturn(partners0);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(partners0);

    when(profileService.findPartners(0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/evaluation_partners_deletion")
                .with(csrf())
	            .param("id", Integer.toString(partner7.getId())))
      .andDo(print())
      .andExpect(model().attribute("partners", hasSize(partners0.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("evaluation_partners"));

  }


  @Test
  @DisplayName("Test Unit #07: Administrator user post(URL: /evaluation_volunteers_deletion) should get rendering of view(name: evaluation_volunteers)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldDeleteEvaluationVolunteersForAdministrator() throws Exception {

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

    Profile volunteer8d = new Profile();

    volunteer8d.setId(8);
    volunteer8d.setNric_uen("400000001D");
    volunteer8d.setNames("Volunteer #1");
    volunteer8d.setRole("Volunteer - FDR");
    volunteer8d.setEmail("volunteer1@gmail.com");
    volunteer8d.setPhone("40000001");
    volunteer8d.setAddress("Volunteer Address #1");
    volunteer8d.setUnit("");
    volunteer8d.setPostal("400001");
    volunteer8d.setStatus("Deleted");
    volunteer8d.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer8d.setGender("Male");
    volunteer8d.setSurname("Test #1");
    volunteer8d.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

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

    volunteer8.setProvider(partner7);
    volunteer8d.setProvider(partner7);

    List<Profile> volunteers0 = new ArrayList<>();
    List<Profile> volunteers1 = new ArrayList<>();

    volunteers1.add(volunteer8);

    when(profileService.deleteProfileById(Integer.toString(volunteer8.getId()))).thenReturn(volunteer8d);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(volunteers0);

    when(profileService.findVolunteers(0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/evaluation_volunteers_deletion")
                 .with(csrf())
	             .param("id", Integer.toString(volunteer8.getId())))
      .andDo(print())
      .andExpect(model().attribute("volunteers", hasSize(volunteers0.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("evaluation_volunteers"));

  }


  @Test
  @DisplayName("Test Unit #08: Administrator user get(URL: /evaluation_members_member) should get rendering of view(name: evaluation_members_member)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowEvaluationMembersMemberForAdministrator() throws Exception {

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

    when(profileService.findProfileById(Integer.toString(member2.getId()))).thenReturn(member2);

    mockMvc
      .perform(get("/evaluation_members_member")
                .param("id", Integer.toString(member2.getId())))
      .andDo(print())
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(member2.getId()))
        )
      ))
      .andExpect(model().attribute("upload_url",
        "localhost:4000"))
      .andExpect(view().name("evaluation_members_member"));

  }


  @Test
  @DisplayName("Test Unit #09: Administrator user get(URL: /evaluation_partners_partner) should get rendering of view(name: evaluation_partners_partner)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowEvaluationPartnersPartnerForAdministrator() throws Exception {

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

    when(profileService.findProfileById(Integer.toString(partner7.getId()))).thenReturn(partner7);

    mockMvc
      .perform(get("/evaluation_partners_partner")
                .param("id", Integer.toString(partner7.getId())))
      .andDo(print())
      .andExpect(model().attribute("partner",
        allOf(
          hasProperty("id", equalTo(partner7.getId()))
        )
      ))
      .andExpect(view().name("evaluation_partners_partner"));

  }


  @Test
  @DisplayName("Test Unit #10: Administrator user get(URL: /evaluation_volunteers_volunteer) should get rendering of view(name: evaluation_volunteers_volunteer)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowEvaluationVolunteersVolunteerForAdministrator() throws Exception {

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

    volunteer8.setProvider(partner7);

    when(profileService.findProfileById(Integer.toString(volunteer8.getId()))).thenReturn(volunteer8);

    mockMvc
      .perform(get("/evaluation_volunteers_volunteer")
                .param("id", Integer.toString(volunteer8.getId())))
      .andDo(print())
      .andExpect(model().attribute("volunteer",
        allOf(
          hasProperty("id", equalTo(volunteer8.getId()))
        )
      ))
      .andExpect(view().name("evaluation_volunteers_volunteer"));

  }


  @Test
  @DisplayName("Test Unit #11: Administrator user post(URL: /evaluation_members_member) should get rendering of view(name: evaluation_members_member)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldSaveEvaluationMembersMemberForAdministrator() throws Exception {

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
    member2.setStatus("Active");
    member2.setNotes("Evaluated on 11/06/2025 00:00 am");
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
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
    member2u.setStatus("Active");
    member2u.setNotes("Evaluated on 11/06/2025 00:00 am");
    member2u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2u.setGender("Female");
    member2u.setSurname("Test #1");
    member2u.setDob(LocalDate.of(1971, 1, 1));
    member2u.setDisabilities("Disability #1");
    member2u.setIncome(1000);
    member2u.setHousehold(1);
    member2u.setDiet("Halal");
    member2u.setAllergies("Allergy #1");
    member2u.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member2u.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2u.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    when(profileService.saveProfile(member2, null, null, null, null, null, null)).thenReturn(member2u);

    mockMvc
      .perform(post("/evaluation_members_member")
                 .with(csrf())
                 .flashAttr("member", member2)
                 .param("password_confirm", member2.getPassword()))
      .andDo(print())
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id",      equalTo(member2u.getId())),
          hasProperty("updated", equalTo(member2u.getUpdated()))
        )
      ))
      .andExpect(model().attribute("upload_url", "localhost:4000"))
      .andExpect(view().name("evaluation_members_member"));

  }


  @Test
  @DisplayName("Test Unit #12: Administrator user post(URL: /evaluation_partners_partner) should get rendering of view(name: evaluation_partners_partner)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldSaveEvaluationPartnersPartnerForAdministrator() throws Exception {

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
    partner7.setStatus("Active");
    partner7.setNotes("Evaluated on 11/06/2025 00:00 am");
    partner7.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner7u = new Profile();

    partner7u.setId(7);
    partner7u.setNric_uen("300000001C");
    partner7u.setNames("Partner #1");
    partner7u.setRole("Partner - FSP");
    partner7u.setEmail("partner1@gmail.com");
    partner7u.setPhone("30000001");
    partner7u.setAddress("408 Pasir Ris Drive 6");
    partner7u.setUnit("#08-423");
    partner7u.setPostal("510408");
    partner7u.setStatus("Active");
    partner7u.setNotes("Evaluated on 11/06/2025 00:00 am");
    partner7u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner7u.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner7u.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    when(profileService.saveProfile(partner7, "", "", "", "", "", null)).thenReturn(partner7u);

    mockMvc
      .perform(post("/evaluation_partners_partner")
                 .with(csrf())
                 .flashAttr("partner", partner7)
                 .param("menu_halal_id",  "")
                 .param("menu_veg_id",    "")
                 .param("menu_soft_id",   "")
                 .param("menu_normal_id", "")
                 .param("provider_id",    "")
    		     .param("password_confirm", partner7.getPassword()))
      .andDo(print())
      .andExpect(model().attribute("partner",
        allOf(
          hasProperty("id",      equalTo(partner7u.getId())),
          hasProperty("updated", equalTo(partner7u.getUpdated()))
        )
      ))
      .andExpect(view().name("evaluation_partners_partner"));

  }


  @Test
  @DisplayName("Test Unit #13: Administrator user post(URL: /evaluation_volunteers_volunteer) should get rendering of view(name: evaluation_volunteers_volunteer)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldSaveEvaluationVolunteersVolunteerForAdministrator() throws Exception {

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
    volunteer8.setStatus("Active");
    volunteer8.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer8.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer8.setGender("Male");
    volunteer8.setSurname("Test #1");
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile volunteer8u = new Profile();

    volunteer8u.setId(8);
    volunteer8u.setNric_uen("400000001D");
    volunteer8u.setNames("Volunteer #1");
    volunteer8u.setRole("Volunteer - FDR");
    volunteer8u.setEmail("volunteer1@gmail.com");
    volunteer8u.setPhone("40000001");
    volunteer8u.setAddress("Volunteer Address #1");
    volunteer8u.setUnit("");
    volunteer8u.setPostal("400001");
    volunteer8u.setStatus("Active");
    volunteer8u.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer8u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer8u.setGender("Male");
    volunteer8u.setSurname("Test #1");
    volunteer8u.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8u.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setStatus("Active");
    partner7.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    volunteer8.setProvider(partner7);
    
    when(profileService.saveProfile(volunteer8, "", "", "", "", Integer.toString(partner7.getId()), null)).thenReturn(volunteer8u);

    mockMvc
    .perform(post("/evaluation_volunteers_volunteer")
               .with(csrf())
               .flashAttr("volunteer", volunteer8)
               .param("menu_halal_id",  "")
               .param("menu_veg_id",    "")
               .param("menu_soft_id",   "")
               .param("menu_normal_id", "")
               .param("provider_id",    Integer.toString(partner7.getId()))
    		   .param("password_confirm", volunteer8.getPassword()))
    .andDo(print())
    .andExpect(model().attribute("volunteer",
      allOf(
        hasProperty("id",      equalTo(volunteer8u.getId())),
        hasProperty("updated", equalTo(volunteer8u.getUpdated()))
      )
    ))
    .andExpect(view().name("evaluation_volunteers_volunteer"));

  }


  @Test
  @DisplayName("Test Unit #14: Administrator user get(URL: /evaluation_members_member_caregivers) should get rendering of view(name: evaluation_members_member_caregivers)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowEvaluationMembersMemberCaregivers1ForAdministrator() throws Exception {

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

 /********************************************************************************/

    when(profileService.findCaregivers(Integer.toString(member2.getId()))).thenReturn(caregivers);

    mockMvc
      .perform(get("/evaluation_members_member_caregivers")
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
      .andExpect(view().name("evaluation_members_member_caregivers"));

  }


  @Test
  @DisplayName("Test Unit #15: Administrator user post(URL: /evaluation_members_member_caregivers) should get rendering of view(name: evaluation_members_member_caregivers)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowEvaluationMembersMemberCaregivers2ForAdministrator() throws Exception {

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

    List<Profile> caregivers = new ArrayList<>();

    when(profileService.saveProfile(member2, null, null, null, null, null, null)).thenReturn(member2u);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findCaregivers(Integer.toString(member2u.getId()))).thenReturn(caregivers);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(caregivers);

    when(profileService.findCaregivers(Integer.toString(member2u.getId()), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

    mockMvc
      .perform(post("/evaluation_members_member_caregivers")
                .with(csrf())
                .flashAttr("member", member2)
   	            .param("password_confirm", member2.getPassword()))
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
          hasProperty("id", equalTo(member2u.getId()))
        )
      ))
      .andExpect(view().name("evaluation_members_member_caregivers"));

  }


  @Test
  @DisplayName("Test Unit #16: Administrator user post(URL: /evaluation_members_member_caregivers_deletion) should get rendering of view(name: evaluation_members_member_caregivers)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldDeleteEvaluationMembersMemberCaregiversForAdministrator() throws Exception {

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
      .perform(post("/evaluation_members_member_caregivers_deletion")
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
      .andExpect(view().name("evaluation_members_member_caregivers"));

  }


  @Test
  @DisplayName("Test Unit #17: Administrator user get(URL: /evaluation_members_member_caregivers_caregiver) should get rendering of view(name: evaluation_members_member_caregivers_caregiver)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowEvaluationMembersMemberCaregiversCaregiverForAdministrator() throws Exception {

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

    List<Profile> caregivers1 = new ArrayList<>();

    caregivers1.add(caregiver3);

 // member2.setCaregivers(caregivers1);
      // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

 // caregiver3.setMembers(members1);
      // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    when(profileService.findProfileById(Integer.toString(member2.getId()))).thenReturn(member2);

    when(profileService.findCaregiverById(Integer.toString(caregiver3.getId()))).thenReturn(caregiver3);

    mockMvc
      .perform(get("/evaluation_members_member_caregivers_caregiver")
    		    .param("id", Integer.toString(caregiver3.getId()))
   	            .param("member_id", Integer.toString(member2.getId())))
      .andDo(print())
      .andExpect(model().attribute("caregiver",
   	    allOf(
          hasProperty("id", equalTo(caregiver3.getId()))
        )
      ))
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(member2.getId()))
        )
      ))
      .andExpect(view().name("evaluation_members_member_caregivers_caregiver"));

  }


  @Test
  @DisplayName("Test Unit #18: Administrator user post(URL: /evaluation_members_member_caregivers_caregiver) should get rendering of view(name: evaluation_members_member_caregivers)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldSaveEvaluationMembersMemberCaregiversCaregiverForAdministrator() throws Exception {

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
    member2.setStatus("Active");
    member2.setNotes("Evaluated on 11/06/2025 00:00 am");
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setScheduled(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    caregiver3.setStatus("Active");
    caregiver3.setNotes("Evaluated on 11/06/2025 00:00 am");
    caregiver3.setGender("Male");
    caregiver3.setSurname("Test #1");
    caregiver3.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    caregiver3.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> caregivers1 = new ArrayList<>();

    caregivers1.add(caregiver3);

 // member2.setCaregivers(caregivers1);
      // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

 // caregiver3.setMembers(members1);
      // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    when(profileService.saveProfile(caregiver3, null, null, null, null, null, Integer.toString(member2.getId()))).thenReturn(caregiver3);

    when(profileService.findProfileById(Integer.toString(member2.getId()))).thenReturn(member2);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findCaregivers(Integer.toString(member2.getId()))).thenReturn(caregivers1);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(caregivers1);
    
    when(profileService.findCaregivers(Integer.toString(member2.getId()), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

    mockMvc
      .perform(post("/evaluation_members_member_caregivers_caregiver")
                 .with(csrf())
                 .flashAttr("caregiver", caregiver3)
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
          hasProperty("id", equalTo(member2.getId()))
        )
      ))
      .andExpect(view().name("evaluation_members_member_caregivers"));

  }



}
