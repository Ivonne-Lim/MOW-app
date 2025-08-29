/*
 * Class Name:  RegistrationControllerOtherTests
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


@WebMvcTest(RegistrationController.class)
@AutoConfigureMockMvc(addFilters = true)

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class RegistrationControllerOtherTests {



  @MockBean
  private ProfileService profileService;

  @MockBean
  private MenuService menuService;

	  
  @Autowired
  private MockMvc mockMvc;



  final String PAGINATION_SIZE = "2";
    // Default and maximum should be 15 as measured.



  @Test
  @DisplayName("Test Unit #01: Member user get(URL: /registration) should get rendering of view(name: registration)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowRegistrationForMember() throws Exception {

    mockMvc
      .perform(get("/registration"))
      .andDo(print())
      .andExpect(view().name("registration"));

  }


  @Test
  @DisplayName("Test Unit #02: Member user get(URL: /registration_member) should get rendering of view(name: registration_member)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowRegistrationMemberForMember() throws Exception {

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

    when(profileService.findProfileByEmail(member2.getEmail())).thenReturn(member2);

    mockMvc
      .perform(get("/registration_member"))
      .andDo(print())
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(member2.getId()))
        )
      ))
      .andExpect(model().attribute("upload_url",
        "localhost:4000"))
      .andExpect(view().name("registration_member"));

  }


  @Test
  @DisplayName("Test Unit #03: Member user post(URL: /registration_member) should get rendering of view(name: registration_member)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldSaveRegistrationMemberForMember() throws Exception {

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

    when(profileService.saveProfile(member2, null, null, null, null, null, null)).thenReturn(member2u);

    mockMvc
    .perform(post("/registration_member")
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
    .andExpect(view().name("registration_member"));

  }


  @Test
  @DisplayName("Test Unit #04: Member user get(URL: /registration_member_caregivers) should get rendering of view(name: registration_member_caregivers)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowRegistrationMemberCaregivers1ForMember() throws Exception {

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
  @DisplayName("Test Unit #05: Member user post(URL: /registration_member_caregivers) should get rendering of view(name: registration_member_caregivers)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowRegistrationMemberCaregivers2ForMember() throws Exception {

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
      .perform(post("/registration_member_caregivers")
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
      .andExpect(view().name("registration_member_caregivers"));

  }


  @Test
  @DisplayName("Test Unit #06: Member user post(URL: /registration_member_caregivers_deletion) should get rendering of view(name: registration_member_caregivers)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldDeleteRegistrationMemberCaregiversForMember() throws Exception {

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
  @DisplayName("Test Unit #07: Member user get(URL: /registration_member_caregivers_caregiver) should get rendering of view(name: registration_member_caregivers_caregiver)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowRegistrationMemberCaregiversCaregiverForMember() throws Exception {

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
  @DisplayName("Test Unit #08: Member user get(URL: /registration_member_caregivers_caregiver_retrieval) should get rendering of view(name: registration_member_caregivers_caregiver)")

  @WithMockUser(username = "member2@gmail.com", roles = "MEMBER")

  void shouldShowRegistrationMemberCaregiversCaregiverRetrievalForMember() throws Exception {

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
  @DisplayName("Test Unit #09: Member user post(URL: /registration_member_caregivers_caregiver) should get rendering of view(name: registration_member_caregivers)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldSaveRegistrationMemberCaregiversCaregiverForMember() throws Exception {

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


  @Test
  @DisplayName("Test Unit #10: Partner-FSP user get(URL: /registration) should get rendering of view(name: registration)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowRegistrationForPartnerFsp() throws Exception {

    mockMvc
      .perform(get("/registration"))
      .andDo(print())
      .andExpect(view().name("registration"));

  }


  @Test
  @DisplayName("Test Unit #11: Partner-FSP user get(URL: /registration_partner) should get rendering of view(name: registration_partner)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowRegistrationPartnerForPartnerFsp() throws Exception {

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

    List<Menu> menusHalal  = new ArrayList<>();
    List<Menu> menusVeg    = new ArrayList<>();
    List<Menu> menusSoft   = new ArrayList<>();
    List<Menu> menusNormal = new ArrayList<>();

    when(profileService.findProfileByEmail(partner7.getEmail())).thenReturn(partner7);

    when(menuService.findMenus("Halal",      partner7.getEmail())).thenReturn(menusHalal);
    when(menuService.findMenus("Vegetarian", partner7.getEmail())).thenReturn(menusVeg);
    when(menuService.findMenus("Soft",       partner7.getEmail())).thenReturn(menusSoft);
    when(menuService.findMenus("Normal",     partner7.getEmail())).thenReturn(menusNormal);

    mockMvc
      .perform(get("/registration_partner"))
      .andDo(print())
      .andExpect(model().attribute("partner",
        allOf(
          hasProperty("id", equalTo(partner7.getId()))
        )
      ))
      .andExpect(model().attribute("menusHalal",  hasSize(menusHalal.size())))
      .andExpect(model().attribute("menusVeg",    hasSize(menusVeg.size())))
      .andExpect(model().attribute("menusSoft",   hasSize(menusSoft.size())))
      .andExpect(model().attribute("menusNormal", hasSize(menusNormal.size())))
      .andExpect(view().name("registration_partner"));

  }


  @Test
  @DisplayName("Test Unit #12: Partner-FSP user post(URL: /registration_partner) should get rendering of view(name: registration_partner)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldSaveRegistrationPartnerForPartnerFsp() throws Exception {

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
    partner7u.setStatus("Pending");
    partner7u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner7u.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner7u.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Menu> menusHalal   = new ArrayList<>();
    List<Menu> menusVeg     = new ArrayList<>();
    List<Menu> menusSoft    = new ArrayList<>();
    List<Menu> menusNormal  = new ArrayList<>();

    when(profileService.saveProfile(partner7, "", "", "", "", "", null)).thenReturn(partner7u);

    when(menuService.findMenus("Halal",      partner7u.getEmail())).thenReturn(menusHalal);
    when(menuService.findMenus("Vegetarian", partner7u.getEmail())).thenReturn(menusVeg);
    when(menuService.findMenus("Soft",       partner7u.getEmail())).thenReturn(menusSoft);
    when(menuService.findMenus("Normal",     partner7u.getEmail())).thenReturn(menusNormal);

    mockMvc
    .perform(post("/registration_partner")
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
    .andExpect(model().attribute("menusHalal",  hasSize(menusHalal.size())))
    .andExpect(model().attribute("menusVeg",    hasSize(menusVeg.size())))
    .andExpect(model().attribute("menusSoft",   hasSize(menusSoft.size())))
    .andExpect(model().attribute("menusNormal", hasSize(menusNormal.size())))
    .andExpect(view().name("registration_partner"));

  }


  @Test
  @DisplayName("Test Unit #13: Partner-FDR user get(URL: /registration) should get rendering of view(name: registration)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldShowRegistrationForPartnerFdr() throws Exception {

    mockMvc
      .perform(get("/registration"))
      .andDo(print())
      .andExpect(view().name("registration"));

  }


  @Test
  @DisplayName("Test Unit #14: Partner-FDR user get(URL: /registration_partner) should get rendering of view(name: registration_partner)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldShowRegistrationPartnerForPartnerFdr() throws Exception {

    Profile partner12 = new Profile();

    partner12.setId(12);
    partner12.setNric_uen("300000002C");
    partner12.setNames("Partner #2");
    partner12.setRole("Partner - FDR");
    partner12.setEmail("partner2@gmail.com");
    partner12.setPhone("30000002");
    partner12.setAddress("408 Pasir Ris Drive 6");
    partner12.setUnit("#08-423");
    partner12.setPostal("510408");
    partner12.setStatus("Pending");
    partner12.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner12.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile volunteer13 = new Profile();

    volunteer13.setId(13);
    volunteer13.setNric_uen("400000003D");
    volunteer13.setNames("Volunteer #3");
    volunteer13.setRole("Volunteer - FSP");
    volunteer13.setEmail("volunteer3@gmail.com");
    volunteer13.setPhone("40000003");
    volunteer13.setAddress("Volunteer Address #3");
    volunteer13.setUnit("");
    volunteer13.setPostal("400003");
    volunteer13.setStatus("Pending");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> providers = new ArrayList<>();

    providers.add(volunteer13);

    when(profileService.findProfileByEmail(partner12.getEmail())).thenReturn(partner12);

    when(profileService.findProviders()).thenReturn(providers);

    mockMvc
      .perform(get("/registration_partner"))
      .andDo(print())
      .andExpect(model().attribute("partner",
        allOf(
          hasProperty("id", equalTo(partner12.getId()))
        )
      ))
      .andExpect(model().attribute("providers", hasSize(providers.size())))
      .andExpect(model().attribute("providers", hasItem(
        allOf(
          hasProperty("id", equalTo(volunteer13.getId()))
        )
      )))
      .andExpect(view().name("registration_partner"));

  }


  @Test
  @DisplayName("Test Unit #15: Partner-FDR user post(URL: /registration_partner) should get rendering of view(name: registration_partner)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldSaveRegistrationPartnerForPartnerFdr() throws Exception {

    Profile partner12 = new Profile();

    partner12.setId(12);
    partner12.setNric_uen("300000002C");
    partner12.setNames("Partner #2");
    partner12.setRole("Partner - FDR");
    partner12.setEmail("partner2@gmail.com");
    partner12.setPhone("30000002");
    partner12.setAddress("408 Pasir Ris Drive 6");
    partner12.setUnit("#08-423");
    partner12.setPostal("510408");
    partner12.setStatus("Pending");
    partner12.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner12.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner12.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner12u = new Profile();

    partner12u.setId(12);
    partner12u.setNric_uen("300000002C");
    partner12u.setNames("Partner #2");
    partner12u.setRole("Partner - FDR");
    partner12u.setEmail("partner2@gmail.com");
    partner12u.setPhone("30000002");
    partner12u.setAddress("408 Pasir Ris Drive 6");
    partner12u.setUnit("#08-423");
    partner12u.setPostal("510408");
    partner12u.setStatus("Pending");
    partner12u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner12u.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner12u.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile volunteer13 = new Profile();

    volunteer13.setId(13);
    volunteer13.setNric_uen("400000003D");
    volunteer13.setNames("Volunteer #3");
    volunteer13.setRole("Volunteer - FSP");
    volunteer13.setEmail("volunteer3@gmail.com");
    volunteer13.setPhone("40000003");
    volunteer13.setAddress("Volunteer Address #3");
    volunteer13.setUnit("");
    volunteer13.setPostal("400003");
    volunteer13.setStatus("Pending");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> providers = new ArrayList<>();

    providers.add(volunteer13);

    when(profileService.saveProfile(partner12, "", "", "", "", Integer.toString(volunteer13.getId()), null)).thenReturn(partner12u);

    when(profileService.findProviders()).thenReturn(providers);

    mockMvc
    .perform(post("/registration_partner")
               .with(csrf())
               .flashAttr("partner", partner12)
               .param("menu_halal_id",  "")
               .param("menu_veg_id",    "")
               .param("menu_soft_id",   "")
               .param("menu_normal_id", "")
               .param("provider_id",    Integer.toString(volunteer13.getId()))
    		   .param("password_confirm", partner12.getPassword()))
    .andDo(print())
    .andExpect(model().attribute("partner",
      allOf(
        hasProperty("id",      equalTo(partner12u.getId())),
        hasProperty("updated", equalTo(partner12u.getUpdated()))
      )
    ))
    .andExpect(model().attribute("providers", hasItem(
            allOf(
              hasProperty("id", equalTo(volunteer13.getId()))
            )
          )))
    .andExpect(view().name("registration_partner"));

  }


  @Test
  @DisplayName("Test Unit #16: Volunteer-FDR user get(URL: /registration) should get rendering of view(name: registration)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldShowRegistrationForVolunteerFdr() throws Exception {

    mockMvc
      .perform(get("/registration"))
      .andDo(print())
      .andExpect(view().name("registration"));

  }


  @Test
  @DisplayName("Test Unit #17: Volunteer-FDR user get(URL: /registration_volunteer) should get rendering of view(name: registration_volunteer)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldShowRegistrationVolunteerForVolunteerFdr() throws Exception {

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

    when(profileService.findProfileByEmail(volunteer8.getEmail())).thenReturn(volunteer8);

    when(profileService.findProviders()).thenReturn(providers);

    mockMvc
      .perform(get("/registration_volunteer"))
      .andDo(print())
      .andExpect(model().attribute("volunteer",
        allOf(
          hasProperty("id", equalTo(volunteer8.getId()))
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
  @DisplayName("Test Unit #18: Volunteer-FDR user post(URL: /registration_volunteer) should get rendering of view(name: registration_volunteer)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldSaveRegistrationVolunteerForVolunteerFdr() throws Exception {

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
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8u.setStatus("Pending");
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
    partner7.setStatus("Pending");
    partner7.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> providers = new ArrayList<>();

    providers.add(partner7);

    when(profileService.saveProfile(volunteer8, "", "", "", "", Integer.toString(partner7.getId()), null)).thenReturn(volunteer8u);

    when(profileService.findProviders()).thenReturn(providers);

    mockMvc
    .perform(post("/registration_volunteer")
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
    .andExpect(model().attribute("providers", hasSize(providers.size())))
    .andExpect(model().attribute("providers", hasItem(
      allOf(
        hasProperty("id", equalTo(partner7.getId()))
      )
    )))
    .andExpect(view().name("registration_volunteer"));

  }


  @Test
  @DisplayName("Test Unit #19: Volunteer-FSP user get(URL: /registration) should get rendering of view(name: registration)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowRegistrationForVolunteerFsp() throws Exception {

    mockMvc
      .perform(get("/registration"))
      .andDo(print())
      .andExpect(view().name("registration"));

  }


  @Test
  @DisplayName("Test Unit #20: Volunteer-FSP user get(URL: /registration_volunteer) should get rendering of view(name: registration_volunteer)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowRegistrationVolunteerForVolunteerFsp() throws Exception {

    Profile volunteer13 = new Profile();

    volunteer13.setId(13);
    volunteer13.setNric_uen("400000003D");
    volunteer13.setNames("Volunteer #3");
    volunteer13.setRole("Volunteer - FSP");
    volunteer13.setEmail("volunteer3@gmail.com");
    volunteer13.setPhone("40000003");
    volunteer13.setAddress("Volunteer Address #3");
    volunteer13.setUnit("");
    volunteer13.setPostal("400003");
    volunteer13.setStatus("Pending");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Menu> menusHalal  = new ArrayList<>();
    List<Menu> menusVeg    = new ArrayList<>();
    List<Menu> menusSoft   = new ArrayList<>();
    List<Menu> menusNormal = new ArrayList<>();

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    when(menuService.findMenus("Halal",      volunteer13.getEmail())).thenReturn(menusHalal);
    when(menuService.findMenus("Vegetarian", volunteer13.getEmail())).thenReturn(menusVeg);
    when(menuService.findMenus("Soft",       volunteer13.getEmail())).thenReturn(menusSoft);
    when(menuService.findMenus("Normal",     volunteer13.getEmail())).thenReturn(menusNormal);

    mockMvc
      .perform(get("/registration_volunteer"))
      .andDo(print())
      .andExpect(model().attribute("volunteer",
        allOf(
          hasProperty("id", equalTo(volunteer13.getId()))
        )
      ))
      .andExpect(model().attribute("menusHalal",  hasSize(menusHalal.size())))
      .andExpect(model().attribute("menusVeg",    hasSize(menusVeg.size())))
      .andExpect(model().attribute("menusSoft",   hasSize(menusSoft.size())))
      .andExpect(model().attribute("menusNormal", hasSize(menusNormal.size())))
      .andExpect(view().name("registration_volunteer"));

  }


  @Test
  @DisplayName("Test Unit #21: Volunteer-FSP user post(URL: /registration_volunteer) should get rendering of view(name: registration_volunteer)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldSaveRegistrationVolunteerForVolunteerFsp() throws Exception {

    Profile volunteer13 = new Profile();

    volunteer13.setId(13);
    volunteer13.setNric_uen("400000003D");
    volunteer13.setNames("Volunteer #3");
    volunteer13.setRole("Volunteer - FSP");
    volunteer13.setEmail("volunteer3@gmail.com");
    volunteer13.setPhone("40000003");
    volunteer13.setAddress("Volunteer Address #3");
    volunteer13.setUnit("");
    volunteer13.setPostal("400003");
    volunteer13.setStatus("Pending");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile volunteer13u = new Profile();

    volunteer13u.setId(13);
    volunteer13u.setNric_uen("400000003D");
    volunteer13u.setNames("Volunteer #3");
    volunteer13u.setRole("Volunteer - FSP");
    volunteer13u.setEmail("volunteer3@gmail.com");
    volunteer13u.setPhone("40000003");
    volunteer13u.setAddress("Volunteer Address #3");
    volunteer13u.setUnit("");
    volunteer13u.setPostal("400003");
    volunteer13u.setStatus("Pending");
    volunteer13u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13u.setGender("Male");
    volunteer13u.setSurname("Test #3");
    volunteer13u.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13u.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Menu> menusHalal   = new ArrayList<>();
    List<Menu> menusVeg     = new ArrayList<>();
    List<Menu> menusSoft    = new ArrayList<>();
    List<Menu> menusNormal  = new ArrayList<>();

    when(profileService.saveProfile(volunteer13, "", "", "", "", "", null)).thenReturn(volunteer13u);

    when(menuService.findMenus("Halal",      volunteer13u.getEmail())).thenReturn(menusHalal);
    when(menuService.findMenus("Vegetarian", volunteer13u.getEmail())).thenReturn(menusVeg);
    when(menuService.findMenus("Soft",       volunteer13u.getEmail())).thenReturn(menusSoft);
    when(menuService.findMenus("Normal",     volunteer13u.getEmail())).thenReturn(menusNormal);

    mockMvc
    .perform(post("/registration_volunteer")
               .with(csrf())
               .flashAttr("volunteer", volunteer13)
               .param("menu_halal_id",  "")
               .param("menu_veg_id",    "")
               .param("menu_soft_id",   "")
               .param("menu_normal_id", "")
               .param("provider_id",    "")
               .param("password_confirm", volunteer13.getPassword()))
    .andDo(print())
    .andExpect(model().attribute("volunteer",
      allOf(
        hasProperty("id",      equalTo(volunteer13u.getId())),
        hasProperty("updated", equalTo(volunteer13u.getUpdated()))
      )
    ))
    .andExpect(model().attribute("menusHalal",  hasSize(menusHalal.size())))
    .andExpect(model().attribute("menusVeg",    hasSize(menusVeg.size())))
    .andExpect(model().attribute("menusSoft",   hasSize(menusSoft.size())))
    .andExpect(model().attribute("menusNormal", hasSize(menusNormal.size())))
    .andExpect(view().name("registration_volunteer"));

  }



}
