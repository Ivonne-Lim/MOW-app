/*
 * Class Name:  PickupsControllerAllTests
 * Description: WebMvcTest
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.controller;


import org.merrymeal.mow.model.SelectWrapper;

import org.merrymeal.mow.entity.Profile;
import org.merrymeal.mow.entity.Menu;
import org.merrymeal.mow.entity.Feedback;
import org.merrymeal.mow.entity.Meal;
import org.merrymeal.mow.entity.Pickup;

import org.merrymeal.mow.service.ProfileService;
import org.merrymeal.mow.service.MenuService;
import org.merrymeal.mow.service.MealService;
import org.merrymeal.mow.service.PickupService;

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


@WebMvcTest(PickupsController.class)
@AutoConfigureMockMvc(addFilters = true)

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class PickupsControllerAllTests {



  @MockBean
  private ProfileService profileService;

  @MockBean
  private MenuService menuService;

  @MockBean
  private MealService mealService;

  @MockBean
  private PickupService pickupService;


  @Autowired
  private MockMvc mockMvc;



  final String PAGINATION_SIZE = "2";
    // Default and maximum should be 15 as measured.



  @Test
  @DisplayName("Test Unit #01: Administrator user get(URL: /meal-delivery) should get rendering of view(name: meal-delivery)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowMealDeliveryForAdministrator() throws Exception {

    mockMvc
      .perform(get("/meal-delivery"))
      .andDo(print())
      .andExpect(view().name("meal-delivery"));

  }


  @Test
  @DisplayName("Test Unit #02: Administrator user get(URL: /meal-delivery_assignment) should get rendering of view(name: meal-delivery_assignment)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowMealDeliveryAssignmentForAdministrator() throws Exception {

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

    List<Profile> riders1 = new ArrayList<>();

    riders1.add(volunteer8);

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

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal3);

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(pickupService.findMealsAvailable()).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(pickupService.findMealsAvailable(0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findRiders()).thenReturn(riders1);

    mockMvc
      .perform(get("/meal-delivery_assignment"))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id", equalTo(meal3.getId()))
        )
      )))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
        .andExpect(model().attribute("pages",    equalTo(1)))
        .andExpect(model().attribute("elements", equalTo(1L)))
        .andExpect(model().attribute("current",  equalTo(0)))
        .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(model().attribute("riders", hasSize(riders1.size())))
      .andExpect(model().attribute("riders", hasItem(
        allOf(
          hasProperty("id", equalTo(volunteer8.getId()))
        )
      )))
      .andExpect(view().name("meal-delivery_assignment"));

  }


  @Test
  @DisplayName("Test Unit #03: Administrator user post(URL: /meal-delivery_assignment) should get rendering of view(name: meal-delivery_assignment)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldGenerateMealDeliveryAssignmentForAdministrator() throws Exception {

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

    List<Profile> riders1 = new ArrayList<>();

    riders1.add(volunteer8);

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

    List<Meal> meals0 = new ArrayList<>();

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Accepted");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1);

    List<String> ids = new ArrayList<>();

    ids.add(Integer.toString(meal3.getId()));

    SelectWrapper selectWrapper0 = new SelectWrapper();
    SelectWrapper selectWrapper1 = new SelectWrapper();

    selectWrapper1.setIds(ids);

    when(profileService.findProfileById(Integer.toString(volunteer8.getId()))).thenReturn(volunteer8);

    when(pickupService.generatePickups(selectWrapper1, volunteer8.getEmail())).thenReturn(pickups1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(pickupService.findMealsAvailable()).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(pickupService.findMealsAvailable(0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findRiders()).thenReturn(riders1);

    mockMvc
      .perform(post("/meal-delivery_assignment")
                .with(csrf())
   	            .param("rider_id", Integer.toString(volunteer8.getId())))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals0.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",    equalTo(1)))
      .andExpect(model().attribute("elements", equalTo(0L)))
      .andExpect(model().attribute("current",  equalTo(0)))
      .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(model().attribute("riders", hasSize(riders1.size())))
      .andExpect(model().attribute("riders", hasItem(
        allOf(
          hasProperty("id", equalTo(volunteer8.getId()))
        )
      )))
      .andExpect(view().name("meal-delivery_assignment"));

  }


  @Test
  @DisplayName("Test Unit #04: Member user get(URL: /meal-delivery) should get rendering of view(name: meal-delivery)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowMealDeliveryForMember() throws Exception {

    mockMvc
      .perform(get("/meal-delivery"))
      .andDo(print())
      .andExpect(view().name("meal-delivery"));

  }


  @Test
  @DisplayName("Test Unit #05: Member user get(URL: /meal-delivery_current) should get rendering of view(name: meal-delivery_current)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowMealDeliveryCurrentForMember() throws Exception {

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
    pickup1.setStatus("Accepted");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1);

    when(profileService.findProfileByEmail(member2.getEmail())).thenReturn(member2);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(pickupService.findPickupsCurrentByMember(member2.getEmail())).thenReturn(pickups1);

  ********************************************************************************/

    Page<Pickup> items = new PageImpl<>(pickups1);

    when(pickupService.findPickupsCurrentByMember(member2.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
    .perform(get("/meal-delivery_current"))
    .andDo(print())
    .andExpect(model().attribute("pickups", hasSize(pickups1.size())))
    .andExpect(model().attribute("pickups", hasItem(
      allOf(
        hasProperty("id", equalTo(pickup1.getId()))
      )
    )))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
    .andExpect(model().attribute("pages",    equalTo(1)))
    .andExpect(model().attribute("elements", equalTo(1L)))
    .andExpect(model().attribute("current",  equalTo(0)))
    .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
    .andExpect(view().name("meal-delivery_current"));

  }


  @Test
  @DisplayName("Test Unit #06: Member user get(URL: /meal-delivery_current_pickup_caregivers) should get rendering of view(name: meal-delivery_current_pickup_caregivers)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowMealDeliveryCurrentPickupCaregiversForMember() throws Exception {

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
    pickup1.setStatus("Accepted");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    when(pickupService.findPickupById(Integer.toString(pickup1.getId()))).thenReturn(pickup1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findCaregivers(Integer.toString(pickup1.getMeal().getMember().getId()))).thenReturn(caregivers1);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(caregivers1);

    when(profileService.findCaregivers(Integer.toString(pickup1.getMeal().getMember().getId()), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

    mockMvc
    .perform(get("/meal-delivery_current_pickup_caregivers")
	           .param("id", Integer.toString(pickup1.getId())))
    .andDo(print())
    .andExpect(model().attribute("member",
      allOf(
        hasProperty("id", equalTo(member2.getId()))
      )
    ))
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
      .andExpect(model().attribute("id",       equalTo(Integer.toString(pickup1.getId()))))
      .andExpect(model().attribute("pages",    equalTo(1)))
      .andExpect(model().attribute("current",  equalTo(0)))
      .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
    .andExpect(view().name("meal-delivery_current_pickup_caregivers"));

  }


  @Test
  @DisplayName("Test Unit #07: Partner-FDR user get(URL: /meal-delivery) should get rendering of view(name: meal-delivery)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldShowMealDeliveryForPartnerFdr() throws Exception {

    mockMvc
      .perform(get("/meal-delivery"))
      .andDo(print())
      .andExpect(view().name("meal-delivery"));

  }


  @Test
  @DisplayName("Test Unit #08: Partner-FDR user get(URL: /meal-delivery_acceptance) should get rendering of view(name: meal-delivery_acceptance)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldShowMealDeliveryAcceptanceForPartnerFdr() throws Exception {

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
    partner12.setProvider(partner7);
    partner12.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner12.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal3);

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(pickupService.findMealsAvailable(partner12.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(pickupService.findMealsAvailable(partner12.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/meal-delivery_acceptance"))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id", equalTo(meal3.getId()))
        )
      )))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",    equalTo(1)))
      .andExpect(model().attribute("elements", equalTo(1L)))
      .andExpect(model().attribute("current",  equalTo(0)))
      .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-delivery_acceptance"));

  }


  @Test
  @DisplayName("Test Unit #09: Partner-FDR user get(URL: /meal-delivery_current) should get rendering of view(name: meal-delivery_current)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldShowMealDeliveryCurrentForPartnerFdr() throws Exception {

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
    partner12.setProvider(partner7);
    partner12.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner12.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    pickup1.setStatus("Accepted");
    pickup1.setRider(partner12);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1);

    when(profileService.findProfileByEmail(partner12.getEmail())).thenReturn(partner12);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(pickupService.findPickupsCurrentByRider(partner12.getEmail())).thenReturn(pickups1);

  ********************************************************************************/

    Page<Pickup> items = new PageImpl<>(pickups1);

    when(pickupService.findPickupsCurrentByRider(partner12.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/meal-delivery_current"))
      .andDo(print())
      .andExpect(model().attribute("pickups", hasSize(pickups1.size())))
      .andExpect(model().attribute("pickups", hasItem(
        allOf(
          hasProperty("id", equalTo(pickup1.getId()))
        )
      )))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",    equalTo(1)))
      .andExpect(model().attribute("elements", equalTo(1L)))
      .andExpect(model().attribute("current",  equalTo(0)))
      .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("meal-delivery_current"));

  }


  @Test
  @DisplayName("Test Unit #10: Partner-FDR user post(URL: /meal-delivery_acceptance) should get rendering of view(name: meal-delivery_acceptance)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldGenerateMealDeliveryAcceptanceForPartnerFdr() throws Exception {

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
    partner12.setProvider(partner7);
    partner12.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner12.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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

    List<Meal> meals0 = new ArrayList<>();

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Accepted");
    pickup1.setRider(partner12);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1);

    List<String> ids = new ArrayList<>();

    ids.add(Integer.toString(meal3.getId()));

    SelectWrapper selectWrapper0 = new SelectWrapper();
    SelectWrapper selectWrapper1 = new SelectWrapper();

    selectWrapper1.setIds(ids);

    when(pickupService.generatePickups(selectWrapper1, partner12.getEmail())).thenReturn(pickups1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(pickupService.findMealsAvailable(partner12.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(pickupService.findMealsAvailable(partner12.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/meal-delivery_acceptance")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper1))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals0.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",    equalTo(1)))
      .andExpect(model().attribute("elements", equalTo(0L)))
      .andExpect(model().attribute("current",  equalTo(0)))
      .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-delivery_acceptance"));

  }


  @Test
  @DisplayName("Test Unit #11: Partner-FDR user get(URL: /meal-delivery_current_pickup) should get rendering of view(name: meal-delivery_current_pickup)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldShowMealDeliveryCurrentPickupForPartnerFdr() throws Exception {

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
    partner12.setProvider(partner7);
    partner12.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner12.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    pickup1.setStatus("Accepted");
    pickup1.setRider(partner12);
    pickup1.setMeal(meal3);

    when(pickupService.findPickupById(Integer.toString(pickup1.getId()))).thenReturn(pickup1);

    mockMvc
    .perform(get("/meal-delivery_current_pickup")
	           .param("id", Integer.toString(pickup1.getId())))
    .andDo(print())
    .andExpect(model().attribute("pickup",
      allOf(
        hasProperty("id", equalTo(pickup1.getId()))
      )
    ))
    .andExpect(view().name("meal-delivery_current_pickup"));

  }


  @Test
  @DisplayName("Test Unit #12: Partner-FDR user post(URL: /meal-delivery_current_pickup) should get rendering of view(name: meal-delivery_current_pickup)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldSaveMealDeliveryCurrentPickupForPartnerFdr() throws Exception {

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
    partner12.setProvider(partner7);
    partner12.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner12.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    pickup1.setStatus("Started");

    Pickup pickup1u = new Pickup();

    pickup1u.setId(1);
    pickup1u.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1u.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1u.setStatus("Started");
    pickup1u.setRider(partner12);
    pickup1u.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1u);

    when(pickupService.savePickup(pickup1, Integer.toString(partner12.getId()), Integer.toString(meal3.getId()))).thenReturn(pickup1u);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(pickupService.findPickupsCurrentByRider(partner12.getEmail())).thenReturn(pickups1);

 ********************************************************************************/

    Page<Pickup> items = new PageImpl<>(pickups1);

    when(pickupService.findPickupsCurrentByRider(partner12.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
    .perform(post("/meal-delivery_current_pickup")
               .with(csrf())
               .flashAttr("pickup", pickup1)
	           .param("rider_id", Integer.toString(partner12.getId()))
	           .param("meal_id",  Integer.toString(meal3.getId())))
    .andDo(print())
    .andExpect(model().attribute("pickups", hasSize(pickups1.size())))
    .andExpect(model().attribute("pickups", hasItem(
       allOf(
 	     hasProperty("id", equalTo(pickup1u.getId())),
 	     hasProperty("time_start", equalTo(pickup1u.getTime_start())),
 	     hasProperty("rider", equalTo(pickup1u.getRider())),
 	     hasProperty("meal", equalTo(pickup1u.getMeal()))
       )
    )))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
    .andExpect(model().attribute("pages",    equalTo(1)))
    .andExpect(model().attribute("elements", equalTo(1L)))
    .andExpect(model().attribute("current",  equalTo(0)))
    .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
    .andExpect(view().name("meal-delivery_current"));

  }


  @Test
  @DisplayName("Test Unit #13: Partner-FDR user get(URL: /meal-delivery_current_pickup_caregivers) should get rendering of view(name: meal-delivery_current_pickup_caregivers)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldShowMealDeliveryCurrentPickupCaregiversForPartnerFdr() throws Exception {

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
    partner12.setProvider(partner7);
    partner12.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner12.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    pickup1.setTime_accept(LocalDateTime.of(2026, 6, 11, 0, 0));
    pickup1.setStatus("Accepted");
    pickup1.setRider(partner12);
    pickup1.setMeal(meal3);

    when(pickupService.findPickupById(Integer.toString(pickup1.getId()))).thenReturn(pickup1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(profileService.findCaregivers(Integer.toString(pickup1.getMeal().getMember().getId()))).thenReturn(caregivers1);

 ********************************************************************************/

    Page<Profile> items = new PageImpl<>(caregivers1);

    when(profileService.findCaregivers(Integer.toString(pickup1.getMeal().getMember().getId()), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

    mockMvc
    .perform(get("/meal-delivery_current_pickup_caregivers")
	           .param("id", Integer.toString(pickup1.getId())))
    .andDo(print())
    .andExpect(model().attribute("member",
      allOf(
        hasProperty("id", equalTo(member2.getId()))
      )
    ))
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
      .andExpect(model().attribute("id",       equalTo(Integer.toString(pickup1.getId()))))
      .andExpect(model().attribute("pages",    equalTo(1)))
      .andExpect(model().attribute("current",  equalTo(0)))
      .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
    .andExpect(view().name("meal-delivery_current_pickup_caregivers"));

  }


  @Test
  @DisplayName("Test Unit #14: Volunteer-FDR user get(URL: /meal-delivery) should get rendering of view(name: meal-delivery)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldShowMealDeliveryForVolunteerFdr() throws Exception {

    mockMvc
      .perform(get("/meal-delivery"))
      .andDo(print())
      .andExpect(view().name("meal-delivery"));

  }


  @Test
  @DisplayName("Test Unit #15: Volunteer-FDR user get(URL: /meal-delivery_acceptance) should get rendering of view(name: meal-delivery_acceptance)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldShowMealDeliveryAcceptanceForVolunteerFdr() throws Exception {

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

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal3);

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(pickupService.findMealsAvailable(volunteer8.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(pickupService.findMealsAvailable(volunteer8.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/meal-delivery_acceptance"))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id", equalTo(meal3.getId()))
        )
      )))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",    equalTo(1)))
      .andExpect(model().attribute("elements", equalTo(1L)))
      .andExpect(model().attribute("current",  equalTo(0)))
      .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-delivery_acceptance"));

  }


  @Test
  @DisplayName("Test Unit #16: Volunteer-FDR user get(URL: /meal-delivery_current) should get rendering of view(name: meal-delivery_current)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldShowMealDeliveryCurrentForVolunteerFdr() throws Exception {

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
    pickup1.setStatus("Accepted");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1);

    when(profileService.findProfileByEmail(volunteer8.getEmail())).thenReturn(volunteer8);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(pickupService.findPickupsCurrentByRider(volunteer8.getEmail())).thenReturn(pickups1);

  ********************************************************************************/

    Page<Pickup> items = new PageImpl<>(pickups1);

    when(pickupService.findPickupsCurrentByRider(volunteer8.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/meal-delivery_current"))
      .andDo(print())
      .andExpect(model().attribute("pickups", hasSize(pickups1.size())))
      .andExpect(model().attribute("pickups", hasItem(
        allOf(
          hasProperty("id", equalTo(pickup1.getId()))
        )
      )))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",    equalTo(1)))
      .andExpect(model().attribute("elements", equalTo(1L)))
      .andExpect(model().attribute("current",  equalTo(0)))
      .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("meal-delivery_current"));

  }


  @Test
  @DisplayName("Test Unit #17: Volunteer-FDR user post(URL: /meal-delivery_acceptance) should get rendering of view(name: meal-delivery_acceptance)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldGenerateMealDeliveryAcceptanceForVolunteerFdr() throws Exception {

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

    List<Meal> meals0 = new ArrayList<>();

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Accepted");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1);

    List<String> ids = new ArrayList<>();

    ids.add(Integer.toString(meal3.getId()));

    SelectWrapper selectWrapper0 = new SelectWrapper();
    SelectWrapper selectWrapper1 = new SelectWrapper();

    selectWrapper1.setIds(ids);

    when(pickupService.generatePickups(selectWrapper1, volunteer8.getEmail())).thenReturn(pickups1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(pickupService.findMealsAvailable(volunteer8.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(pickupService.findMealsAvailable(volunteer8.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/meal-delivery_acceptance")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper1))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals0.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",    equalTo(1)))
      .andExpect(model().attribute("elements", equalTo(0L)))
      .andExpect(model().attribute("current",  equalTo(0)))
      .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-delivery_acceptance"));

  }


  @Test
  @DisplayName("Test Unit #18: Volunteer-FDR user get(URL: /meal-delivery_current_pickup) should get rendering of view(name: meal-delivery_current_pickup)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldShowMealDeliveryCurrentPickupForVolunteerFdr() throws Exception {

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
    pickup1.setStatus("Accepted");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    when(pickupService.findPickupById(Integer.toString(pickup1.getId()))).thenReturn(pickup1);

    mockMvc
    .perform(get("/meal-delivery_current_pickup")
	           .param("id", Integer.toString(pickup1.getId())))
    .andDo(print())
    .andExpect(model().attribute("pickup",
      allOf(
        hasProperty("id", equalTo(pickup1.getId()))
      )
    ))
    .andExpect(view().name("meal-delivery_current_pickup"));

  }


  @Test
  @DisplayName("Test Unit #19: Volunteer-FDR user post(URL: /meal-delivery_current_pickup) should get rendering of view(name: meal-delivery_current_pickup)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldSaveMealDeliveryCurrentPickupForVolunteerFdr() throws Exception {

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
    pickup1.setStatus("Started");

    Pickup pickup1u = new Pickup();

    pickup1u.setId(1);
    pickup1u.setTime_accept(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1u.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1u.setStatus("Started");
    pickup1u.setRider(volunteer8);
    pickup1u.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();

    pickups1.add(pickup1u);

    when(pickupService.savePickup(pickup1, Integer.toString(volunteer8.getId()), Integer.toString(meal3.getId()))).thenReturn(pickup1u);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(pickupService.findPickupsCurrentByRider(volunteer8.getEmail())).thenReturn(pickups1);

  ********************************************************************************/

    Page<Pickup> items = new PageImpl<>(pickups1);

    when(pickupService.findPickupsCurrentByRider(volunteer8.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
    .perform(post("/meal-delivery_current_pickup")
               .with(csrf())
               .flashAttr("pickup", pickup1)
	           .param("rider_id", Integer.toString(volunteer8.getId()))
	           .param("meal_id",  Integer.toString(meal3.getId())))
    .andDo(print())
    .andExpect(model().attribute("pickups", hasSize(pickups1.size())))
    .andExpect(model().attribute("pickups", hasItem(
       allOf(
 	     hasProperty("id", equalTo(pickup1u.getId())),
 	     hasProperty("time_start", equalTo(pickup1u.getTime_start())),
 	     hasProperty("rider", equalTo(pickup1u.getRider())),
 	     hasProperty("meal", equalTo(pickup1u.getMeal()))
       )
    )))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
    .andExpect(model().attribute("pages",    equalTo(1)))
    .andExpect(model().attribute("elements", equalTo(1L)))
    .andExpect(model().attribute("current",  equalTo(0)))
    .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
    .andExpect(view().name("meal-delivery_current"));

  }


  @Test
  @DisplayName("Test Unit #20: Volunteer-FDR user get(URL: /meal-delivery_current_pickup_caregivers) should get rendering of view(name: meal-delivery_current_pickup_caregivers)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldShowMealDeliveryCurrentPickupCaregiversForVolunteerFdr() throws Exception {

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
    pickup1.setTime_accept(LocalDateTime.of(2026, 6, 11, 0, 0));
    pickup1.setStatus("Accepted");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    when(pickupService.findPickupById(Integer.toString(pickup1.getId()))).thenReturn(pickup1);

    /********************************************************************************
    Note: Switched to simple pagination.
          See above PAGINATION_SIZE constant.
    ********************************************************************************

    when(profileService.findCaregivers(Integer.toString(pickup1.getMeal().getMember().getId()))).thenReturn(caregivers1);

  ********************************************************************************/

    Page<Profile> items = new PageImpl<>(caregivers1);

    when(profileService.findCaregivers(Integer.toString(pickup1.getMeal().getMember().getId()), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
    .perform(get("/meal-delivery_current_pickup_caregivers")
	           .param("id", Integer.toString(pickup1.getId())))
    .andDo(print())
    .andExpect(model().attribute("member",
      allOf(
        hasProperty("id", equalTo(member2.getId()))
      )
    ))
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
      .andExpect(model().attribute("id",       equalTo(Integer.toString(pickup1.getId()))))
      .andExpect(model().attribute("pages",    equalTo(1)))
      .andExpect(model().attribute("current",  equalTo(0)))
      .andExpect(model().attribute("size",     equalTo(Integer.parseInt(PAGINATION_SIZE))))
   /********************************************************************************/
    .andExpect(view().name("meal-delivery_current_pickup_caregivers"));

  }



}
