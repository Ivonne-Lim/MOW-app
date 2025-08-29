/*
 * Class Name:  FeedbackControllerAllTests
 * Description: WebMvcTest
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
import org.merrymeal.mow.service.MealService;
import org.merrymeal.mow.service.PickupService;
import org.merrymeal.mow.service.FeedbackService;

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


@WebMvcTest(FeedbackController.class)
@AutoConfigureMockMvc(addFilters = true)

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class FeedbackControllerAllTests {



  @MockBean
  private ProfileService profileService;

  @MockBean
  private MenuService menuService;

  @MockBean
  private MealService mealService;

  @MockBean
  private PickupService pickupService;

  @MockBean
  private FeedbackService feedbackService;


  @Autowired
  private MockMvc mockMvc;



  final String PAGINATION_SIZE = "2";
  // Default and maximum should be 15 as measured.



  @Test
  @DisplayName("Test Unit #01: Administrator user get(URL: /service-feedback) should get rendering of view(name: service-feedback)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowServiceFeedbackForAdministrator() throws Exception {

    Profile administrator1 = new Profile();

    administrator1.setId(1);
    administrator1.setNric_uen("S7120218F");
    administrator1.setNames("E-wen Ivonne");
    administrator1.setRole("Administrator");
    administrator1.setEmail("management.merrymeal@gmail.com");
    administrator1.setPhone("63249730");
    administrator1.setAddress("11 Eunos Road 8");
    administrator1.setUnit("#07-02");
    administrator1.setPostal("370055");
    administrator1.setStatus("Active");
    administrator1.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    administrator1.setGender("Female");
    administrator1.setSurname("Lim");
    administrator1.setCreated(LocalDateTime.of(2025, 6, 3, 0, 0));

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
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8.setProvider(partner7);
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
    menu5.setProvider(partner7);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();

    submissions1.add(feedback1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(feedbackService.findSubmissions(administrator1.getEmail())).thenReturn(submissions1);

  ********************************************************************************/

    Page<Feedback> items = new PageImpl<>(submissions1);

    when(feedbackService.findSubmissions(administrator1.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
    .perform(get("/service-feedback"))
    .andDo(print())
    .andExpect(model().attribute("submissions", hasSize(submissions1.size())))
    .andExpect(model().attribute("submissions", hasItem(
      allOf(
        hasProperty("id", equalTo(feedback1.getId()))
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
    .andExpect(view().name("service-feedback"));

  }


  @Test
  @DisplayName("Test Unit #02: Administrator user post(URL: /service-feedback_deletion) should get rendering of view(name: service-feedback)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldDeleteServiceFeedbackForAdministrator() throws Exception {

    Profile administrator1 = new Profile();

    administrator1.setId(1);
    administrator1.setNric_uen("S7120218F");
    administrator1.setNames("E-wen Ivonne");
    administrator1.setRole("Administrator");
    administrator1.setEmail("management.merrymeal@gmail.com");
    administrator1.setPhone("63249730");
    administrator1.setAddress("11 Eunos Road 8");
    administrator1.setUnit("#07-02");
    administrator1.setPostal("370055");
    administrator1.setStatus("Active");
    administrator1.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    administrator1.setGender("Female");
    administrator1.setSurname("Lim");
    administrator1.setCreated(LocalDateTime.of(2025, 6, 3, 0, 0));

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
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8.setProvider(partner7);
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
    menu5.setProvider(partner7);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    Feedback feedback1d = new Feedback();

    feedback1d.setId(1);
    feedback1d.setRate_meal(3);
    feedback1d.setRemarks_meal("Meal Remarks #1");
    feedback1d.setRate_delivery(3);
    feedback1d.setRemarks_delivery("Delivery Remarks #1");
    feedback1d.setStatus("Deleted");
    feedback1d.setMember(member2);
    feedback1d.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();

    submissions1.add(feedback1);

    when(feedbackService.deleteSubmissionById(Integer.toString(feedback1.getId()))).thenReturn(feedback1d);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(feedbackService.findSubmissions(administrator1.getEmail())).thenReturn(submissions1);

  ********************************************************************************/

    Page<Feedback> items = new PageImpl<>(submissions1);

    when(feedbackService.findSubmissions(administrator1.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/
    mockMvc
    .perform(post("/service-feedback_deletion")
               .with(csrf())
	           .param("id", Integer.toString(feedback1.getId())))
    .andDo(print())
    .andExpect(model().attribute("submissions", hasSize(submissions1.size())))
    .andExpect(model().attribute("submissions", hasItem(
      allOf(
        hasProperty("id", equalTo(feedback1d.getId()))
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
    .andExpect(view().name("service-feedback"));

  }


  @Test
  @DisplayName("Test Unit #03: Administrator user get(URL: /service-feedback_submission) should get rendering of view(name: service-feedback_submission)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowServiceFeedbackSubmissionforAdministrator() throws Exception {

    Profile administrator1 = new Profile();

    administrator1.setId(1);
    administrator1.setNric_uen("S7120218F");
    administrator1.setNames("E-wen Ivonne");
    administrator1.setRole("Administrator");
    administrator1.setEmail("management.merrymeal@gmail.com");
    administrator1.setPhone("63249730");
    administrator1.setAddress("11 Eunos Road 8");
    administrator1.setUnit("#07-02");
    administrator1.setPostal("370055");
    administrator1.setStatus("Active");
    administrator1.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    administrator1.setGender("Female");
    administrator1.setSurname("Lim");
    administrator1.setCreated(LocalDateTime.of(2025, 6, 3, 0, 0));

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
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8.setProvider(partner7);
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
    menu5.setProvider(partner7);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    when(feedbackService.findSubmissionById(Integer.toString(feedback1.getId()))).thenReturn(feedback1);

    when(pickupService.findDeliveries(feedback1.getMember().getEmail())).thenReturn(pickups1);

    mockMvc
      .perform(get("/service-feedback_submission")
	           .param("id", Integer.toString(feedback1.getId())))
      .andDo(print())
      .andExpect(model().attribute("submission",
        allOf(
          hasProperty("id", equalTo(feedback1.getId()))
        )
      ))
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(feedback1.getMember().getId()))
        )
      ))
      .andExpect(model().attribute("deliveries", hasSize(pickups1.size())))
      .andExpect(model().attribute("deliveries", hasItem(
        allOf(
          hasProperty("id", equalTo(pickup1.getId()))
        )
      )))
      .andExpect(view().name("service-feedback_submission"));


  }


  @Test
  @DisplayName("Test Unit #04: Administrator user post(URL: /service-feedback_submission) should get rendering of view(name: service-feedback_submission)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldSaveServiceFeedbackSubmissionforAdministrator() throws Exception {

    Profile administrator1 = new Profile();

    administrator1.setId(1);
    administrator1.setNric_uen("S7120218F");
    administrator1.setNames("E-wen Ivonne");
    administrator1.setRole("Administrator");
    administrator1.setEmail("management.merrymeal@gmail.com");
    administrator1.setPhone("63249730");
    administrator1.setAddress("11 Eunos Road 8");
    administrator1.setUnit("#07-02");
    administrator1.setPostal("370055");
    administrator1.setStatus("Active");
    administrator1.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    administrator1.setGender("Female");
    administrator1.setSurname("Lim");
    administrator1.setCreated(LocalDateTime.of(2025, 6, 3, 0, 0));

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
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8.setProvider(partner7);
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
    menu5.setProvider(partner7);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1);

    Feedback feedback0 = new Feedback();

    feedback0.setId(0);
    feedback0.setRate_meal(3);
    feedback0.setRemarks_meal("Meal Remarks #1");
    feedback0.setRate_delivery(3);
    feedback0.setRemarks_delivery("Delivery Remarks #1");
    feedback0.setStatus("Active");

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    when(feedbackService.saveSubmission(feedback0, Integer.toString(member2.getId()), Integer.toString(pickup1.getId()))).thenReturn(feedback1);

    when(pickupService.findDeliveries(feedback1.getMember().getEmail())).thenReturn(pickups1);

    mockMvc
    .perform(post("/service-feedback_submission")
               .with(csrf())
               .flashAttr("submission", feedback0)
	           .param("member_id", Integer.toString(member2.getId()))
	           .param("pickup_id", Integer.toString(pickup1.getId())))
    .andDo(print())
    .andExpect(model().attribute("submission",
      allOf(
        hasProperty("id", equalTo(feedback1.getId()))
      )
    ))
    .andExpect(model().attribute("member",
       allOf(
         hasProperty("id", equalTo(feedback1.getMember().getId()))
       )
    ))
    .andExpect(model().attribute("deliveries", hasSize(pickups1.size())))
    .andExpect(model().attribute("deliveries", hasItem(
      allOf(
        hasProperty("id", equalTo(pickup1.getId()))
      )
    )))
    .andExpect(view().name("service-feedback_submission"));

  }


  @Test
  @DisplayName("Test Unit #05: Member user get(URL: /service-feedback) should get rendering of view(name: service-feedback)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowServiceFeedbackForMember() throws Exception {

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
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8.setProvider(partner7);
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
    menu5.setProvider(partner7);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();

    submissions1.add(feedback1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(feedbackService.findSubmissions(member2.getEmail())).thenReturn(submissions1);

  ********************************************************************************/

    Page<Feedback> items = new PageImpl<>(submissions1);

    when(feedbackService.findSubmissions(member2.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
    .perform(get("/service-feedback"))
    .andDo(print())
    .andExpect(model().attribute("submissions", hasSize(submissions1.size())))
    .andExpect(model().attribute("submissions", hasItem(
      allOf(
        hasProperty("id", equalTo(feedback1.getId()))
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
    .andExpect(view().name("service-feedback"));

  }


  @Test
  @DisplayName("Test Unit #06: Member user post(URL: /service-feedback_deletion) should get rendering of view(name: service-feedback)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldDeleteServiceFeedbackForMember() throws Exception {

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
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8.setProvider(partner7);
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
    menu5.setProvider(partner7);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    Feedback feedback1d = new Feedback();

    feedback1d.setId(1);
    feedback1d.setRate_meal(3);
    feedback1d.setRemarks_meal("Meal Remarks #1");
    feedback1d.setRate_delivery(3);
    feedback1d.setRemarks_delivery("Delivery Remarks #1");
    feedback1d.setStatus("Deleted");
    feedback1d.setMember(member2);
    feedback1d.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();

    submissions1.add(feedback1);

    when(feedbackService.deleteSubmissionById(Integer.toString(feedback1.getId()))).thenReturn(feedback1d);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(feedbackService.findSubmissions(member2.getEmail())).thenReturn(submissions1);

  ********************************************************************************/

    Page<Feedback> items = new PageImpl<>(submissions1);

    when(feedbackService.findSubmissions(member2.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
    .perform(post("/service-feedback_deletion")
               .with(csrf())
	           .param("id", Integer.toString(feedback1.getId())))
    .andDo(print())
    .andExpect(model().attribute("submissions", hasSize(submissions1.size())))
    .andExpect(model().attribute("submissions", hasItem(
      allOf(
        hasProperty("id", equalTo(feedback1d.getId()))
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
    .andExpect(view().name("service-feedback"));

  }


  @Test
  @DisplayName("Test Unit #07: Member user get(URL: /service-feedback_submission) should get rendering of view(name: service-feedback_submission)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowServiceFeedbackSubmissionforMember() throws Exception {

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
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8.setProvider(partner7);
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
    menu5.setProvider(partner7);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    when(feedbackService.findSubmissionById(Integer.toString(feedback1.getId()))).thenReturn(feedback1);

    when(pickupService.findDeliveries(feedback1.getMember().getEmail())).thenReturn(pickups1);

    mockMvc
      .perform(get("/service-feedback_submission")
	           .param("id", Integer.toString(feedback1.getId())))
      .andDo(print())
      .andExpect(model().attribute("submission",
        allOf(
          hasProperty("id", equalTo(feedback1.getId()))
        )
      ))
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(feedback1.getMember().getId()))
        )
      ))
      .andExpect(model().attribute("deliveries", hasSize(pickups1.size())))
      .andExpect(model().attribute("deliveries", hasItem(
        allOf(
          hasProperty("id", equalTo(pickup1.getId()))
        )
      )))
      .andExpect(view().name("service-feedback_submission"));


  }


  @Test
  @DisplayName("Test Unit #08: Member user post(URL: /service-feedback_submission) should get rendering of view(name: service-feedback_submission)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldSaveServiceFeedbackSubmissionforMember() throws Exception {

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
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8.setProvider(partner7);
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
    menu5.setProvider(partner7);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1);

    Feedback feedback0 = new Feedback();

    feedback0.setId(0);
    feedback0.setRate_meal(3);
    feedback0.setRemarks_meal("Meal Remarks #1");
    feedback0.setRate_delivery(3);
    feedback0.setRemarks_delivery("Delivery Remarks #1");
    feedback0.setStatus("Active");

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    when(feedbackService.saveSubmission(feedback0, Integer.toString(member2.getId()), Integer.toString(pickup1.getId()))).thenReturn(feedback1);

    when(pickupService.findDeliveries(feedback1.getMember().getEmail())).thenReturn(pickups1);

    mockMvc
    .perform(post("/service-feedback_submission")
               .with(csrf())
               .flashAttr("submission", feedback0)
	           .param("member_id", Integer.toString(member2.getId()))
	           .param("pickup_id", Integer.toString(pickup1.getId())))
    .andDo(print())
    .andExpect(model().attribute("submission",
      allOf(
        hasProperty("id", equalTo(feedback1.getId()))
      )
    ))
    .andExpect(model().attribute("member",
       allOf(
         hasProperty("id", equalTo(feedback1.getMember().getId()))
       )
    ))
    .andExpect(model().attribute("deliveries", hasSize(pickups1.size())))
    .andExpect(model().attribute("deliveries", hasItem(
      allOf(
        hasProperty("id", equalTo(pickup1.getId()))
      )
    )))
    .andExpect(view().name("service-feedback_submission"));

  }


  @Test
  @DisplayName("Test Unit #09: Partner-FSP user get(URL: /service-feedback) should get rendering of view(name: service-feedback)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowServiceFeedbackForPartnerFsp() throws Exception {

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
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8.setProvider(partner7);
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
    menu5.setProvider(partner7);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();

    submissions1.add(feedback1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(feedbackService.findSubmissions(partner7.getEmail())).thenReturn(submissions1);

  ********************************************************************************/

    Page<Feedback> items = new PageImpl<>(submissions1);

    when(feedbackService.findSubmissions(partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
    .perform(get("/service-feedback"))
    .andDo(print())
    .andExpect(model().attribute("submissions", hasSize(submissions1.size())))
    .andExpect(model().attribute("submissions", hasItem(
      allOf(
        hasProperty("id", equalTo(feedback1.getId()))
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
    .andExpect(view().name("service-feedback"));

  }


  @Test
  @DisplayName("Test Unit #10: Partner-FSP user get(URL: /service-feedback_submission) should get rendering of view(name: service-feedback_submission)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowServiceFeedbackSubmissionforPartnerFsp() throws Exception {

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
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8.setProvider(partner7);
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
    menu5.setProvider(partner7);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    when(feedbackService.findSubmissionById(Integer.toString(feedback1.getId()))).thenReturn(feedback1);

    when(pickupService.findDeliveries(feedback1.getMember().getEmail())).thenReturn(pickups1);

    mockMvc
      .perform(get("/service-feedback_submission")
	           .param("id", Integer.toString(feedback1.getId())))
      .andDo(print())
      .andExpect(model().attribute("submission",
        allOf(
          hasProperty("id", equalTo(feedback1.getId()))
        )
      ))
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(feedback1.getMember().getId()))
        )
      ))
      .andExpect(model().attribute("deliveries", hasSize(pickups1.size())))
      .andExpect(model().attribute("deliveries", hasItem(
        allOf(
          hasProperty("id", equalTo(pickup1.getId()))
        )
      )))
      .andExpect(view().name("service-feedback_submission"));


  }


  @Test
  @DisplayName("Test Unit #11: Partner-FDR user get(URL: /service-feedback) should get rendering of view(name: service-feedback)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldShowServiceFeedbackForPartnerFdr() throws Exception {

    Profile member4 = new Profile();

    member4.setId(4);
    member4.setNric_uen("S1000004A");
    member4.setNames("Member #4");
    member4.setRole("Member");
    member4.setEmail("member4@gmail.com");
    member4.setPhone("10000004");
    member4.setAddress("Member Address #4");
    member4.setUnit("");
    member4.setPostal("100004");
    member4.setStatus("Active");
    member4.setNotes("Evaluated on 11/06/2025 00:00 am");
    member4.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member4.setGender("Female");
    member4.setSurname("Test #4");
    member4.setDob(LocalDate.of(1971, 1, 1));
    member4.setDisabilities("Disability #4");
    member4.setIncome(4000);
    member4.setHousehold(4);
    member4.setDiet("Halal");
    member4.setAllergies("");
    member4.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member4.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member4.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile volunteer13 = new Profile();

    volunteer13.setId(7);
    volunteer13.setNric_uen("400000003D");
    volunteer13.setNames("Volunteer #3");
    volunteer13.setRole("Volunteer - FSP");
    volunteer13.setEmail("volunteer3@gmail.com");
    volunteer13.setPhone("40000003");
    volunteer13.setAddress("Volunteer Address #3");
    volunteer13.setUnit("");
    volunteer13.setPostal("400003");
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner12 = new Profile();

    partner12.setId(12);
    partner12.setNric_uen("400000002D");
    partner12.setNames("Partner #2");
    partner12.setRole("Partner - FDR");
    partner12.setEmail("partner2@gmail.com");
    partner12.setPhone("30000002");
    partner12.setAddress("408 Pasir Ris Drive 6");
    partner12.setUnit("#08-423");
    partner12.setPostal("510408");
    partner12.setStatus("Active");
    partner12.setNotes("Evaluated on 11/06/2025 00:00 am");
    partner12.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner12.setProvider(volunteer13);
    partner12.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner12.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu6 = new Menu();

    menu6.setId(6);
    menu6.setSeq_day(1);
    menu6.setSeq_time(2);
    menu6.setName("Nasi Briyani");
    menu6.setDiet("Halal");
    menu6.setFrozen("No");
    menu6.setStatus("Active");
    menu6.setProvider(volunteer13);

    Meal meal6 = new Meal();

    meal6.setId(6);
    meal6.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setStatus("Ended");
    meal6.setMenu(menu6);
    meal6.setProvider(volunteer13);
    meal6.setMember(member4);

    Pickup pickup3 = new Pickup();

    pickup3.setId(3);
    pickup3.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup3.setStatus("Ended");
    pickup3.setRider(partner12);
    pickup3.setMeal(meal6);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup3);

    Feedback feedback2 = new Feedback();

    feedback2.setId(2);
    feedback2.setRate_meal(3);
    feedback2.setRemarks_meal("Meal Remarks #1");
    feedback2.setRate_delivery(3);
    feedback2.setRemarks_delivery("Delivery Remarks #1");
    feedback2.setStatus("Active");
    feedback2.setMember(member4);
    feedback2.setPickup(pickup3);

    List<Feedback> submissions1 = new ArrayList<>();

    submissions1.add(feedback2);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(feedbackService.findSubmissions(partner12.getEmail())).thenReturn(submissions1);

 ********************************************************************************/

    Page<Feedback> items = new PageImpl<>(submissions1);

    when(feedbackService.findSubmissions(partner12.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
    .perform(get("/service-feedback"))
    .andDo(print())
    .andExpect(model().attribute("submissions", hasSize(submissions1.size())))
    .andExpect(model().attribute("submissions", hasItem(
      allOf(
        hasProperty("id", equalTo(feedback2.getId()))
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
    .andExpect(view().name("service-feedback"));

  }


  @Test
  @DisplayName("Test Unit #12: Partner-FDR user get(URL: /service-feedback_submission) should get rendering of view(name: service-feedback_submission)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldShowServiceFeedbackSubmissionforPartnerFdr() throws Exception {

    Profile member4 = new Profile();

    member4.setId(4);
    member4.setNric_uen("S1000004A");
    member4.setNames("Member #4");
    member4.setRole("Member");
    member4.setEmail("member4@gmail.com");
    member4.setPhone("10000004");
    member4.setAddress("Member Address #4");
    member4.setUnit("");
    member4.setPostal("100004");
    member4.setStatus("Active");
    member4.setNotes("Evaluated on 11/06/2025 00:00 am");
    member4.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member4.setGender("Female");
    member4.setSurname("Test #4");
    member4.setDob(LocalDate.of(1971, 1, 1));
    member4.setDisabilities("Disability #4");
    member4.setIncome(4000);
    member4.setHousehold(4);
    member4.setDiet("Halal");
    member4.setAllergies("");
    member4.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member4.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member4.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile volunteer13 = new Profile();

    volunteer13.setId(7);
    volunteer13.setNric_uen("400000003D");
    volunteer13.setNames("Volunteer #3");
    volunteer13.setRole("Volunteer - FSP");
    volunteer13.setEmail("volunteer3@gmail.com");
    volunteer13.setPhone("40000003");
    volunteer13.setAddress("Volunteer Address #3");
    volunteer13.setUnit("");
    volunteer13.setPostal("400003");
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner12 = new Profile();

    partner12.setId(12);
    partner12.setNric_uen("400000002D");
    partner12.setNames("Partner #2");
    partner12.setRole("Partner - FDR");
    partner12.setEmail("partner2@gmail.com");
    partner12.setPhone("30000002");
    partner12.setAddress("408 Pasir Ris Drive 6");
    partner12.setUnit("#08-423");
    partner12.setPostal("510408");
    partner12.setStatus("Active");
    partner12.setNotes("Evaluated on 11/06/2025 00:00 am");
    partner12.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner12.setProvider(volunteer13);
    partner12.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner12.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu6 = new Menu();

    menu6.setId(6);
    menu6.setSeq_day(1);
    menu6.setSeq_time(2);
    menu6.setName("Nasi Briyani");
    menu6.setDiet("Halal");
    menu6.setFrozen("No");
    menu6.setStatus("Active");
    menu6.setProvider(volunteer13);

    Meal meal6 = new Meal();

    meal6.setId(6);
    meal6.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setStatus("Ended");
    meal6.setMenu(menu6);
    meal6.setProvider(volunteer13);
    meal6.setMember(member4);

    Pickup pickup3 = new Pickup();

    pickup3.setId(3);
    pickup3.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup3.setStatus("Ended");
    pickup3.setRider(partner12);
    pickup3.setMeal(meal6);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup3);

    Feedback feedback2 = new Feedback();

    feedback2.setId(2);
    feedback2.setRate_meal(3);
    feedback2.setRemarks_meal("Meal Remarks #1");
    feedback2.setRate_delivery(3);
    feedback2.setRemarks_delivery("Delivery Remarks #1");
    feedback2.setStatus("Active");
    feedback2.setMember(member4);
    feedback2.setPickup(pickup3);

    when(feedbackService.findSubmissionById(Integer.toString(feedback2.getId()))).thenReturn(feedback2);

    when(pickupService.findDeliveries(feedback2.getMember().getEmail())).thenReturn(pickups1);

    mockMvc
      .perform(get("/service-feedback_submission")
	           .param("id", Integer.toString(feedback2.getId())))
      .andDo(print())
      .andExpect(model().attribute("submission",
        allOf(
          hasProperty("id", equalTo(feedback2.getId()))
        )
      ))
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(feedback2.getMember().getId()))
        )
      ))
      .andExpect(model().attribute("deliveries", hasSize(pickups1.size())))
      .andExpect(model().attribute("deliveries", hasItem(
        allOf(
          hasProperty("id", equalTo(pickup3.getId()))
        )
      )))
      .andExpect(view().name("service-feedback_submission"));


  }


  @Test
  @DisplayName("Test Unit #13: Volunteer-FSP user get(URL: /service-feedback) should get rendering of view(name: service-feedback)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowServiceFeedbackForVolunteerFsp() throws Exception {

    Profile member4 = new Profile();

    member4.setId(4);
    member4.setNric_uen("S1000004A");
    member4.setNames("Member #4");
    member4.setRole("Member");
    member4.setEmail("member4@gmail.com");
    member4.setPhone("10000004");
    member4.setAddress("Member Address #4");
    member4.setUnit("");
    member4.setPostal("100004");
    member4.setStatus("Active");
    member4.setNotes("Evaluated on 11/06/2025 00:00 am");
    member4.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member4.setGender("Female");
    member4.setSurname("Test #4");
    member4.setDob(LocalDate.of(1971, 1, 1));
    member4.setDisabilities("Disability #4");
    member4.setIncome(4000);
    member4.setHousehold(4);
    member4.setDiet("Halal");
    member4.setAllergies("");
    member4.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member4.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member4.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile volunteer13 = new Profile();

    volunteer13.setId(7);
    volunteer13.setNric_uen("400000003D");
    volunteer13.setNames("Volunteer #3");
    volunteer13.setRole("Volunteer - FSP");
    volunteer13.setEmail("volunteer3@gmail.com");
    volunteer13.setPhone("40000003");
    volunteer13.setAddress("Volunteer Address #3");
    volunteer13.setUnit("");
    volunteer13.setPostal("400003");
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner12 = new Profile();

    partner12.setId(12);
    partner12.setNric_uen("400000002D");
    partner12.setNames("Partner #2");
    partner12.setRole("Partner - FDR");
    partner12.setEmail("partner2@gmail.com");
    partner12.setPhone("30000002");
    partner12.setAddress("408 Pasir Ris Drive 6");
    partner12.setUnit("#08-423");
    partner12.setPostal("510408");
    partner12.setStatus("Active");
    partner12.setNotes("Evaluated on 11/06/2025 00:00 am");
    partner12.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner12.setProvider(volunteer13);
    partner12.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner12.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu6 = new Menu();

    menu6.setId(6);
    menu6.setSeq_day(1);
    menu6.setSeq_time(2);
    menu6.setName("Nasi Briyani");
    menu6.setDiet("Halal");
    menu6.setFrozen("No");
    menu6.setStatus("Active");
    menu6.setProvider(volunteer13);

    Meal meal6 = new Meal();

    meal6.setId(6);
    meal6.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setStatus("Ended");
    meal6.setMenu(menu6);
    meal6.setProvider(volunteer13);
    meal6.setMember(member4);

    Pickup pickup3 = new Pickup();

    pickup3.setId(3);
    pickup3.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup3.setStatus("Ended");
    pickup3.setRider(partner12);
    pickup3.setMeal(meal6);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup3);

    Feedback feedback2 = new Feedback();

    feedback2.setId(2);
    feedback2.setRate_meal(3);
    feedback2.setRemarks_meal("Meal Remarks #1");
    feedback2.setRate_delivery(3);
    feedback2.setRemarks_delivery("Delivery Remarks #1");
    feedback2.setStatus("Active");
    feedback2.setMember(member4);
    feedback2.setPickup(pickup3);

    List<Feedback> submissions1 = new ArrayList<>();

    submissions1.add(feedback2);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(feedbackService.findSubmissions(volunteer13.getEmail())).thenReturn(submissions1);

  ********************************************************************************/

    Page<Feedback> items = new PageImpl<>(submissions1);

    when(feedbackService.findSubmissions(volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
    .perform(get("/service-feedback"))
    .andDo(print())
    .andExpect(model().attribute("submissions", hasSize(submissions1.size())))
    .andExpect(model().attribute("submissions", hasItem(
      allOf(
        hasProperty("id", equalTo(feedback2.getId()))
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
    .andExpect(view().name("service-feedback"));

  }


  @Test
  @DisplayName("Test Unit #14: Volunteer-FSP user get(URL: /service-feedback_submission) should get rendering of view(name: service-feedback_submission)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowServiceFeedbackSubmissionforVolunteerFsp() throws Exception {

    Profile member4 = new Profile();

    member4.setId(4);
    member4.setNric_uen("S1000004A");
    member4.setNames("Member #4");
    member4.setRole("Member");
    member4.setEmail("member4@gmail.com");
    member4.setPhone("10000004");
    member4.setAddress("Member Address #4");
    member4.setUnit("");
    member4.setPostal("100004");
    member4.setStatus("Active");
    member4.setNotes("Evaluated on 11/06/2025 00:00 am");
    member4.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member4.setGender("Female");
    member4.setSurname("Test #4");
    member4.setDob(LocalDate.of(1971, 1, 1));
    member4.setDisabilities("Disability #4");
    member4.setIncome(4000);
    member4.setHousehold(4);
    member4.setDiet("Halal");
    member4.setAllergies("");
    member4.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member4.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member4.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile volunteer13 = new Profile();

    volunteer13.setId(7);
    volunteer13.setNric_uen("400000003D");
    volunteer13.setNames("Volunteer #3");
    volunteer13.setRole("Volunteer - FSP");
    volunteer13.setEmail("volunteer3@gmail.com");
    volunteer13.setPhone("40000003");
    volunteer13.setAddress("Volunteer Address #3");
    volunteer13.setUnit("");
    volunteer13.setPostal("400003");
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner12 = new Profile();

    partner12.setId(12);
    partner12.setNric_uen("400000002D");
    partner12.setNames("Partner #2");
    partner12.setRole("Partner - FDR");
    partner12.setEmail("partner2@gmail.com");
    partner12.setPhone("30000002");
    partner12.setAddress("408 Pasir Ris Drive 6");
    partner12.setUnit("#08-423");
    partner12.setPostal("510408");
    partner12.setStatus("Active");
    partner12.setNotes("Evaluated on 11/06/2025 00:00 am");
    partner12.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner12.setProvider(volunteer13);
    partner12.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner12.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu6 = new Menu();

    menu6.setId(6);
    menu6.setSeq_day(1);
    menu6.setSeq_time(2);
    menu6.setName("Nasi Briyani");
    menu6.setDiet("Halal");
    menu6.setFrozen("No");
    menu6.setStatus("Active");
    menu6.setProvider(volunteer13);

    Meal meal6 = new Meal();

    meal6.setId(6);
    meal6.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setStatus("Ended");
    meal6.setMenu(menu6);
    meal6.setProvider(volunteer13);
    meal6.setMember(member4);

    Pickup pickup3 = new Pickup();

    pickup3.setId(3);
    pickup3.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup3.setStatus("Ended");
    pickup3.setRider(partner12);
    pickup3.setMeal(meal6);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup3);

    Feedback feedback2 = new Feedback();

    feedback2.setId(2);
    feedback2.setRate_meal(3);
    feedback2.setRemarks_meal("Meal Remarks #1");
    feedback2.setRate_delivery(3);
    feedback2.setRemarks_delivery("Delivery Remarks #1");
    feedback2.setStatus("Active");
    feedback2.setMember(member4);
    feedback2.setPickup(pickup3);

    when(feedbackService.findSubmissionById(Integer.toString(feedback2.getId()))).thenReturn(feedback2);

    when(pickupService.findDeliveries(feedback2.getMember().getEmail())).thenReturn(pickups1);

    mockMvc
      .perform(get("/service-feedback_submission")
	           .param("id", Integer.toString(feedback2.getId())))
      .andDo(print())
      .andExpect(model().attribute("submission",
        allOf(
          hasProperty("id", equalTo(feedback2.getId()))
        )
      ))
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(feedback2.getMember().getId()))
        )
      ))
      .andExpect(model().attribute("deliveries", hasSize(pickups1.size())))
      .andExpect(model().attribute("deliveries", hasItem(
        allOf(
          hasProperty("id", equalTo(pickup3.getId()))
        )
      )))
      .andExpect(view().name("service-feedback_submission"));


  }


  @Test
  @DisplayName("Test Unit #15: Volunteer-FDR user get(URL: /service-feedback) should get rendering of view(name: service-feedback)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldShowServiceFeedbackForVolunteerFdr() throws Exception {

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
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8.setProvider(partner7);
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
    menu5.setProvider(partner7);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();

    submissions1.add(feedback1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(feedbackService.findSubmissions(volunteer8.getEmail())).thenReturn(submissions1);

  ********************************************************************************/

    Page<Feedback> items = new PageImpl<>(submissions1);

    when(feedbackService.findSubmissions(volunteer8.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
    .perform(get("/service-feedback"))
    .andDo(print())
    .andExpect(model().attribute("submissions", hasSize(submissions1.size())))
    .andExpect(model().attribute("submissions", hasItem(
      allOf(
        hasProperty("id", equalTo(feedback1.getId()))
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
    .andExpect(view().name("service-feedback"));

  }


  @Test
  @DisplayName("Test Unit #16: Volunteer-FDR user get(URL: /service-feedback_submission) should get rendering of view(name: service-feedback_submission)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldShowServiceFeedbackSubmissionforVolunteerFdr() throws Exception {

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
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    partner7.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer8.setProvider(partner7);
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
    menu5.setProvider(partner7);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    when(feedbackService.findSubmissionById(Integer.toString(feedback1.getId()))).thenReturn(feedback1);
    when(pickupService.findDeliveries(feedback1.getMember().getEmail())).thenReturn(pickups1);

    mockMvc
      .perform(get("/service-feedback_submission")
	           .param("id", Integer.toString(feedback1.getId())))
      .andDo(print())
      .andExpect(model().attribute("submission",
        allOf(
          hasProperty("id", equalTo(feedback1.getId()))
        )
      ))
      .andExpect(model().attribute("member",
        allOf(
          hasProperty("id", equalTo(feedback1.getMember().getId()))
        )
      ))
      .andExpect(model().attribute("deliveries", hasSize(pickups1.size())))
      .andExpect(model().attribute("deliveries", hasItem(
        allOf(
          hasProperty("id", equalTo(pickup1.getId()))
        )
      )))
      .andExpect(view().name("service-feedback_submission"));


  }



}
