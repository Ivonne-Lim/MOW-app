/*
 * Class Name:  MealsControllerAllTests
 * Description: WebMvcTest
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.controller;


import org.merrymeal.mow.model.SelectWrapper;

import org.merrymeal.mow.entity.Profile;
import org.merrymeal.mow.entity.Menu;
import org.merrymeal.mow.entity.Meal;

import org.merrymeal.mow.service.ProfileService;
import org.merrymeal.mow.service.MenuService;
import org.merrymeal.mow.service.MealService;

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


@WebMvcTest(MealsController.class)
@AutoConfigureMockMvc(addFilters = true)

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class MealsControllerAllTests {



  @MockBean
  private ProfileService profileService;

  @MockBean
  private MenuService menuService;

  @MockBean
  private MealService mealService;


  @Autowired
  private MockMvc mockMvc;



  final String PAGINATION_SIZE = "2";
    // Default and maximum should be 15 as measured.



  @Test
  @DisplayName("Test Unit #01: Administrator user get(URL: /meal-preparation) should get rendering of view(name: meal-preparation)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowMealPreparationForAdministrator() throws Exception {

    mockMvc
      .perform(get("/meal-preparation"))
      .andDo(print())
      .andExpect(view().name("meal-preparation"));

  }


  @Test
  @DisplayName("Test Unit #02: Administrator user get(URL: /meal-preparation_halal) should get rendering of view(name: meal-preparation_halal)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowMealPreparationHalalForAdministrator() throws Exception {

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
    meal3.setStatus("Generated");
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

    when(mealService.findMeals("Halal", administrator1.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Halal", administrator1.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(administrator1.getEmail())).thenReturn(administrator1);

    mockMvc
      .perform(get("/meal-preparation_halal"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_halal"));

  }


  @Test
  @DisplayName("Test Unit #03: Administrator user get(URL: /meal-preparation_veg) should get rendering of view(name: meal-preparation_veg)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowMealPreparationVegForAdministrator() throws Exception {

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

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Vegetarian", administrator1.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Vegetarian", administrator1.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(administrator1.getEmail())).thenReturn(administrator1);

    mockMvc
      .perform(get("/meal-preparation_veg"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_veg"));

  }


  @Test
  @DisplayName("Test Unit #04: Administrator user get(URL: /meal-preparation_soft) should get rendering of view(name: meal-preparation_soft)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowMealPreparationSoftForAdministrator() throws Exception {

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

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Soft", administrator1.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Soft", administrator1.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(administrator1.getEmail())).thenReturn(administrator1);

    mockMvc
      .perform(get("/meal-preparation_soft"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_soft"));

  }


  @Test
  @DisplayName("Test Unit #05: Administrator user get(URL: /meal-preparation_normal) should get rendering of view(name: meal-preparation_normal)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowMealPreparationNormalForAdministrator() throws Exception {

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
    member5.setStatus("Active");
    member5.setNotes("Evaluated on 11/06/2025 00:00 am");
    member5.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member5.setGender("Female");
    member5.setSurname("Test #2");
    member5.setDob(LocalDate.of(1971, 2, 1));
    member5.setDisabilities("Disability #2");
    member5.setIncome(2000);
    member5.setHousehold(2);
    member5.setDiet("Normal");
    member5.setAllergies("");
    member5.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member5.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member5.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner6 = new Profile();

    partner6.setId(6);
    partner6.setNric_uen("200202841R");
    partner6.setNames("MerryMeal Ltd");
    partner6.setRole("Partner - FSP");
    partner6.setEmail("kitchen.merrymeal@gmail.com");
    partner6.setPhone("63249730");
    partner6.setAddress("11 Eunos Road 8");
    partner6.setUnit("#07-02");
    partner6.setPostal("408601");
    partner6.setStatus("Active");
    partner6.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner6.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner6.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Chicken Chop");
    menu1.setDiet("Normal");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");
    menu1.setProvider(partner6);

    Meal meal1 = new Meal();

    meal1.setId(1);
    meal1.setStatus("Generated");
    meal1.setMenu(menu1);
    meal1.setProvider(partner6);
    meal1.setMember(member5);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal1);

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Normal", administrator1.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Normal", administrator1.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(administrator1.getEmail())).thenReturn(administrator1);

    mockMvc
      .perform(get("/meal-preparation_normal"))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id", equalTo(meal1.getId()))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_normal"));

  }


  @Test
  @DisplayName("Test Unit #06: Member user get(URL: /meal-preparation) should get rendering of view(name: meal-preparation)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowMealPreparationForMember() throws Exception {

    mockMvc
      .perform(get("/meal-preparation"))
      .andDo(print())
      .andExpect(view().name("meal-preparation"));

  }


  @Test
  @DisplayName("Test Unit #07: Member user get(URL: /meal-preparation_halal) should get rendering of view(name: meal-preparation_halal)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowMealPreparationHalalForMember() throws Exception {

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
    meal3.setStatus("Generated");
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

    when(mealService.findMeals("Halal", member2.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Halal", member2.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(member2.getEmail())).thenReturn(member2);

    mockMvc
      .perform(get("/meal-preparation_halal"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_halal"));

  }


  @Test
  @DisplayName("Test Unit #08: Member user get(URL: /meal-preparation_veg) should get rendering of view(name: meal-preparation_veg)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowMealPreparationVegForMember() throws Exception {

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
    
    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Vegetarian", member2.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Vegetarian", member2.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(member2.getEmail())).thenReturn(member2);

    mockMvc
      .perform(get("/meal-preparation_veg"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_veg"));

  }


  @Test
  @DisplayName("Test Unit #09: Member user get(URL: /meal-preparation_soft) should get rendering of view(name: meal-preparation_soft)")

  @WithMockUser(username = "member2@gmail.com", roles = "MEMBER")

  void shouldShowMealPreparationSoftForMember() throws Exception {

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
    member5.setStatus("Active");
    member5.setNotes("Evaluated on 11/06/2025 00:00 am");
    member5.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member5.setGender("Female");
    member5.setSurname("Test #2");
    member5.setDob(LocalDate.of(1971, 2, 1));
    member5.setDisabilities("Disability #2");
    member5.setIncome(2000);
    member5.setHousehold(2);
    member5.setDiet("Normal");
    member5.setAllergies("");
    member5.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member5.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member5.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Soft", member5.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Soft", member5.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(member5.getEmail())).thenReturn(member5);

    mockMvc
      .perform(get("/meal-preparation_soft"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_soft"));

  }


  @Test
  @DisplayName("Test Unit #10: Member user get(URL: /meal-preparation_normal) should get rendering of view(name: meal-preparation_normal)")

  @WithMockUser(username = "member2@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowMealPreparationNormalForMember() throws Exception {

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
    member5.setStatus("Active");
    member5.setNotes("Evaluated on 11/06/2025 00:00 am");
    member5.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member5.setGender("Female");
    member5.setSurname("Test #2");
    member5.setDob(LocalDate.of(1971, 1, 2));
    member5.setDisabilities("Disability #2");
    member5.setIncome(2000);
    member5.setHousehold(2);
    member5.setDiet("Normal");
    member5.setAllergies("");
    member5.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member5.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member5.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner6 = new Profile();

    partner6.setId(6);
    partner6.setNric_uen("200202841R");
    partner6.setNames("MerryMeal Ltd");
    partner6.setRole("Partner - FSP");
    partner6.setEmail("kitchen.merrymeal@gmail.com");
    partner6.setPhone("63249730");
    partner6.setAddress("11 Eunos Road 8");
    partner6.setUnit("#07-02");
    partner6.setPostal("408601");
    partner6.setStatus("Active");
    partner6.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner6.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner6.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Chicken Chop");
    menu1.setDiet("Normal");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");
    menu1.setProvider(partner6);

    Meal meal1 = new Meal();

    meal1.setId(1);
    meal1.setStatus("Generated");
    meal1.setMenu(menu1);
    meal1.setProvider(partner6);
    meal1.setMember(member5);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal1);

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Normal", member5.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Normal", member5.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(member5.getEmail())).thenReturn(member5);

    mockMvc
      .perform(get("/meal-preparation_normal"))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id", equalTo(meal1.getId()))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_normal"));

  }


  @Test
  @DisplayName("Test Unit #11: Partner-FSP user get(URL: /meal-preparation) should get rendering of view(name: meal-preparation)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMealPreparationForPartnerFsp() throws Exception {

    mockMvc
      .perform(get("/meal-preparation"))
      .andDo(print())
      .andExpect(view().name("meal-preparation"));

  }


  @Test
  @DisplayName("Test Unit #12: Partner-FSP user get(URL: /meal-preparation_halal) should get rendering of view(name: meal-preparation_halal)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMealPreparationHalalForPartnerFsp() throws Exception {

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

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
//  menu5.setProvider(partner7);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    partner7.setMenu_halal(menu5);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setStatus("Generated");
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

    when(mealService.findMeals("Halal", partner7.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Halal", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner7.getEmail())).thenReturn(partner7);

    mockMvc
      .perform(get("/meal-preparation_halal"))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_halal"));

  }


  @Test
  @DisplayName("Test Unit #13: Partner-FSP user get(URL: /meal-preparation_veg) should get rendering of view(name: meal-preparation_veg)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMealPreparationVegForPartnerFsp() throws Exception {

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

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Vegetarian", partner7.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Vegetarian", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner7.getEmail())).thenReturn(partner7);

    mockMvc
      .perform(get("/meal-preparation_veg"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_veg"));

  }


  @Test
  @DisplayName("Test Unit #14: Partner-FSP user get(URL: /meal-preparation_soft) should get rendering of view(name: meal-preparation_soft)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMealPreparationSoftForPartnerFsp() throws Exception {

    Profile partner6 = new Profile();

    partner6.setId(6);
    partner6.setNric_uen("200202841R");
    partner6.setNames("MerryMeal Ltd");
    partner6.setRole("Partner - FSP");
    partner6.setEmail("kitchen.merrymeal@gmail.com");
    partner6.setPhone("63249730");
    partner6.setAddress("11 Eunos Road 8");
    partner6.setUnit("#07-02");
    partner6.setPostal("408601");
    partner6.setStatus("Active");
    partner6.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner6.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner6.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Soft", partner6.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Soft", partner6.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner6.getEmail())).thenReturn(partner6);

    mockMvc
      .perform(get("/meal-preparation_soft"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_soft"));

  }


  @Test
  @DisplayName("Test Unit #15: Partner-FSP user get(URL: /meal-preparation_normal) should get rendering of view(name: meal-preparation_normal)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMealPreparationNormalForPartnerFsp() throws Exception {

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
    member5.setStatus("Active");
    member5.setNotes("Evaluated on 11/06/2025 00:00 am");
    member5.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member5.setGender("Female");
    member5.setSurname("Test #2");
    member5.setDob(LocalDate.of(1971, 2, 1));
    member5.setDisabilities("Disability #2");
    member5.setIncome(2000);
    member5.setHousehold(2);
    member5.setDiet("Normal");
    member5.setAllergies("");
    member5.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member5.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member5.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner6 = new Profile();

    partner6.setId(6);
    partner6.setNric_uen("200202841R");
    partner6.setNames("MerryMeal Ltd");
    partner6.setRole("Partner - FSP");
    partner6.setEmail("kitchen.merrymeal@gmail.com");
    partner6.setPhone("63249730");
    partner6.setAddress("11 Eunos Road 8");
    partner6.setUnit("#07-02");
    partner6.setPostal("408601");
    partner6.setStatus("Active");
    partner6.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner6.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner6.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Chicken Chop");
    menu1.setDiet("Normal");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");
 // menu1.setProvider(partner6);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    partner6.setMenu_normal(menu1);

    Meal meal1 = new Meal();

    meal1.setId(1);
    meal1.setStatus("Generated");
    meal1.setMenu(menu1);
    meal1.setProvider(partner6);
    meal1.setMember(member5);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal1);

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Normal", partner6.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Normal", partner6.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner6.getEmail())).thenReturn(partner6);

    mockMvc
      .perform(get("/meal-preparation_normal"))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id", equalTo(meal1.getId()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_normal"));

  }


  @Test
  @DisplayName("Test Unit #16: Partner-FSP user post(URL: /meal-preparation_halal_generation) should get rendering of view(name: meal-preparation_halal)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldGenerateMealPreparationHalalForPartnerFsp() throws Exception {

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

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
 // menu5.setProvider(partner7);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    partner7.setMenu_halal(menu5);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setStatus("Generated");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal3);

    SelectWrapper selectWrapper = new SelectWrapper();

    when(mealService.generateMeals("Halal", partner7.getEmail())).thenReturn(meals1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Halal", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner7.getEmail())).thenReturn(partner7);

    mockMvc
      .perform(post("/meal-preparation_halal_generation")
                .with(csrf()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_halal"));

  }


  @Test
  @DisplayName("Test Unit #17: Partner-FSP user post(URL: /meal-preparation_veg_generation) should get rendering of view(name: meal-preparation_veg)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldGenerateMealPreparationVegForPartnerFsp() throws Exception {

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

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

    when(mealService.generateMeals("Vegetarian", partner7.getEmail())).thenReturn(meals0);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Vegetarian", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner7.getEmail())).thenReturn(partner7);

    mockMvc
      .perform(post("/meal-preparation_veg_generation")
                .with(csrf()))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_veg"));

  }


  @Test
  @DisplayName("Test Unit #18: Partner-FSP user post(URL: /meal-preparation_soft_generation) should get rendering of view(name: meal-preparation_soft)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldGenerateMealPreparationSoftForPartnerFsp() throws Exception {

    Profile partner6 = new Profile();

    partner6.setId(6);
    partner6.setNric_uen("200202841R");
    partner6.setNames("MerryMeal Ltd");
    partner6.setRole("Partner - FSP");
    partner6.setEmail("kitchen.merrymeal@gmail.com");
    partner6.setPhone("63249730");
    partner6.setAddress("11 Eunos Road 8");
    partner6.setUnit("#07-02");
    partner6.setPostal("408601");
    partner6.setStatus("Active");
    partner6.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner6.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner6.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

    when(mealService.generateMeals("Soft", partner6.getEmail())).thenReturn(meals0);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Soft", partner6.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner6.getEmail())).thenReturn(partner6);

    mockMvc
      .perform(post("/meal-preparation_soft_generation")
                .with(csrf()))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_soft"));

  }


  @Test
  @DisplayName("Test Unit #19: Partner-FSP user post(URL: /meal-preparation_normal_generation) should get rendering of view(name: meal-preparation_normal)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldGenerateMealPreparationNormalForPartnerFsp() throws Exception {

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
    member5.setStatus("Active");
    member5.setNotes("Evaluated on 11/06/2025 00:00 am");
    member5.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member5.setGender("Female");
    member5.setSurname("Test #2");
    member5.setDob(LocalDate.of(1971, 1, 2));
    member5.setDisabilities("Disability #2");
    member5.setIncome(2000);
    member5.setHousehold(2);
    member5.setDiet("Normal");
    member5.setAllergies("");
    member5.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member5.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member5.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner6 = new Profile();

    partner6.setId(6);
    partner6.setNric_uen("200202841R");
    partner6.setNames("MerryMeal Ltd");
    partner6.setRole("Partner - FSP");
    partner6.setEmail("kitchen.merrymeal@gmail.com");
    partner6.setPhone("63249730");
    partner6.setAddress("11 Eunos Road 8");
    partner6.setUnit("#07-02");
    partner6.setPostal("408601");
    partner6.setStatus("Active");
    partner6.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner6.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner6.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Chicken Chop");
    menu1.setDiet("Normal");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");
 // menu1.setProvider(partner6);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    partner6.setMenu_normal(menu1);

    Meal meal1 = new Meal();

    meal1.setId(1);
    meal1.setStatus("Generated");
    meal1.setMenu(menu1);
    meal1.setProvider(partner6);
    meal1.setMember(member5);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal1);

    SelectWrapper selectWrapper = new SelectWrapper();

    when(mealService.generateMeals("Normal", partner6.getEmail())).thenReturn(meals1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Normal", partner6.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner6.getEmail())).thenReturn(partner6);

    mockMvc
      .perform(post("/meal-preparation_normal_generation")
                .with(csrf()))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id", equalTo(meal1.getId()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_normal"));

  }


  @Test
  @DisplayName("Test Unit #20: Partner-FSP user post(URL: /meal-preparation_halal_updation) should get rendering of view(name: meal-preparation_halal)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldUpdateMealPreparationHalalForPartnerFsp() throws Exception {

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

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
 // menu5.setProvider(partner7);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    partner7.setMenu_halal(menu5);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Started");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal3);

    List<String> ids = new ArrayList<>();

    ids.add(Integer.toString(meal3.getId()));

    SelectWrapper selectWrapper0 = new SelectWrapper();
    SelectWrapper selectWrapper1 = new SelectWrapper();

    selectWrapper1.setIds(ids);

    when(mealService.updateMeals(selectWrapper1, "Started")).thenReturn(meals1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Halal", partner7.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Halal", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner7.getEmail())).thenReturn(partner7);

    mockMvc
      .perform(post("/meal-preparation_halal_updation")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper1)
   	            .param("status", "Started"))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id",     equalTo(meal3.getId())),
          hasProperty("status", equalTo(meal3.getStatus()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_halal"));

  }


  @Test
  @DisplayName("Test Unit #21: Partner-FSP user post(URL: /meal-preparation_veg_updation) should get rendering of view(name: meal-preparation_veg)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldUpdateMealPreparationVegForPartnerFsp() throws Exception {

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

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper0 = new SelectWrapper();

    when(mealService.updateMeals(selectWrapper0, "Started")).thenReturn(meals0);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Vegetarian", partner7.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Vegetarian", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner7.getEmail())).thenReturn(partner7);

    mockMvc
      .perform(post("/meal-preparation_veg_updation")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper0)
   	            .param("status", "Started"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_veg"));

  }


  @Test
  @DisplayName("Test Unit #22: Partner-FSP user post(URL: /meal-preparation_soft_updation) should get rendering of view(name: meal-preparation_soft)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldUpdateMealPreparationSoftForPartnerFsp() throws Exception {

    Profile partner6 = new Profile();

    partner6.setId(6);
    partner6.setNric_uen("200202841R");
    partner6.setNames("MerryMeal Ltd");
    partner6.setRole("Partner - FSP");
    partner6.setEmail("kitchen.merrymeal@gmail.com");
    partner6.setPhone("63249730");
    partner6.setAddress("11 Eunos Road 8");
    partner6.setUnit("#07-02");
    partner6.setPostal("408601");
    partner6.setStatus("Active");
    partner6.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner6.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner6.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper0 = new SelectWrapper();

    when(mealService.updateMeals(selectWrapper0, "Started")).thenReturn(meals0);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Soft", partner6.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Soft", partner6.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner6.getEmail())).thenReturn(partner6);

    mockMvc
      .perform(post("/meal-preparation_soft_updation")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper0)
   	            .param("status", "Started"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_soft"));

  }
  

  @Test
  @DisplayName("Test Unit #23: Partner-FSP user post(URL: /meal-preparation_normal_updation) should get rendering of view(name: meal-preparation_normal)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldUpdateMealPreparationNormalForPartnerFsp() throws Exception {

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
    member5.setStatus("Active");
    member5.setNotes("Evaluated on 11/06/2025 00:00 am");
    member5.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member5.setGender("Female");
    member5.setSurname("Test #2");
    member5.setDob(LocalDate.of(1971, 2, 1));
    member5.setDisabilities("Disability #2");
    member5.setIncome(2000);
    member5.setHousehold(2);
    member5.setDiet("Normal");
    member5.setAllergies("");
    member5.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member5.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member5.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner6 = new Profile();

    partner6.setId(6);
    partner6.setNric_uen("200202841R");
    partner6.setNames("MerryMeal Ltd");
    partner6.setRole("Partner - FSP");
    partner6.setEmail("kitchen.merrymeal@gmail.com");
    partner6.setPhone("63249730");
    partner6.setAddress("11 Eunos Road 8");
    partner6.setUnit("#07-02");
    partner6.setPostal("408601");
    partner6.setStatus("Active");
    partner6.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner6.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner6.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Chicken Chop");
    menu1.setDiet("Normal");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");
 // menu1.setProvider(partner6);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    partner6.setMenu_normal(menu1);

    Meal meal1 = new Meal();

    meal1.setId(1);
    meal1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal1.setStatus("Started");
    meal1.setMenu(menu1);
    meal1.setProvider(partner6);
    meal1.setMember(member5);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal1);

    List<String> ids = new ArrayList<>();

    ids.add(Integer.toString(meal1.getId()));

    SelectWrapper selectWrapper0 = new SelectWrapper();
    SelectWrapper selectWrapper1 = new SelectWrapper();

    selectWrapper1.setIds(ids);

    when(mealService.updateMeals(selectWrapper1, "Started")).thenReturn(meals1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Normal", partner6.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Normal", partner6.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner6.getEmail())).thenReturn(partner6);

    mockMvc
      .perform(post("/meal-preparation_normal_updation")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper1)
   	            .param("status", "Started"))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
   	    allOf(
          hasProperty("id",     equalTo(meal1.getId())),
          hasProperty("status", equalTo(meal1.getStatus()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_normal"));

  }


  @Test
  @DisplayName("Test Unit #24: Partner-FSP user post(URL: /meal-preparation_halal_deletion) should get rendering of view(name: meal-preparation_halal)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldDeleteMealPreparationHalalForPartnerFsp() throws Exception {

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

    Menu menu5 = new Menu();

    menu5.setId(5);
    menu5.setSeq_day(1);
    menu5.setSeq_time(1);
    menu5.setName("Nasi Ayam");
    menu5.setDiet("Halal");
    menu5.setFrozen("No");
    menu5.setStatus("Active");
 // menu5.setProvider(partner7);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    partner7.setMenu_halal(menu5);

    Meal meal3 = new Meal();

    meal3.setId(3);
    meal3.setStatus("Deleted");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal3);

    List<String> ids = new ArrayList<>();

    ids.add(Integer.toString(meal3.getId()));

    SelectWrapper selectWrapper0 = new SelectWrapper();
    SelectWrapper selectWrapper1 = new SelectWrapper();

    selectWrapper1.setIds(ids);

    when(mealService.updateMeals(selectWrapper1, "Deleted")).thenReturn(meals1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Halal", partner7.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Halal", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner7.getEmail())).thenReturn(partner7);

    mockMvc
      .perform(post("/meal-preparation_halal_deletion")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper1))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id",     equalTo(meal3.getId())),
          hasProperty("status", equalTo(meal3.getStatus()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_halal"));

  }


  @Test
  @DisplayName("Test Unit #25: Partner-FSP user post(URL: /meal-preparation_veg_deletion) should get rendering of view(name: meal-preparation_veg)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldDeleteMealPreparationVegForPartnerFsp() throws Exception {

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

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper0 = new SelectWrapper();

    when(mealService.updateMeals(selectWrapper0, "Deleted")).thenReturn(meals0);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Vegetarian", partner7.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Vegetarian", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner7.getEmail())).thenReturn(partner7);

    mockMvc
      .perform(post("/meal-preparation_veg_deletion")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper0))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_veg"));

  }


  @Test
  @DisplayName("Test Unit #26: Partner-FSP user post(URL: /meal-preparation_soft_deletion) should get rendering of view(name: meal-preparation_soft)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldDeleteMealPreparationSoftForPartnerFsp() throws Exception {

    Profile partner6 = new Profile();

    partner6.setId(6);
    partner6.setNric_uen("200202841R");
    partner6.setNames("MerryMeal Ltd");
    partner6.setRole("Partner - FSP");
    partner6.setEmail("kitchen.merrymeal@gmail.com");
    partner6.setPhone("63249730");
    partner6.setAddress("11 Eunos Road 8");
    partner6.setUnit("#07-02");
    partner6.setPostal("408601");
    partner6.setStatus("Active");
    partner6.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner6.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner6.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper0 = new SelectWrapper();

    when(mealService.updateMeals(selectWrapper0, "Deleted")).thenReturn(meals0);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Soft", partner6.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Soft", partner6.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner6.getEmail())).thenReturn(partner6);

    mockMvc
      .perform(post("/meal-preparation_soft_deletion")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper0))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_soft"));

  }
  

  @Test
  @DisplayName("Test Unit #27: Partner-FSP user post(URL: /meal-preparation_normal_deletion) should get rendering of view(name: meal-preparation_normal)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldDeleteMealPreparationNormalForPartnerFsp() throws Exception {

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
    member5.setStatus("Active");
    member5.setNotes("Evaluated on 11/06/2025 00:00 am");
    member5.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member5.setGender("Female");
    member5.setSurname("Test #2");
    member5.setDob(LocalDate.of(1971, 2, 1));
    member5.setDisabilities("Disability #2");
    member5.setIncome(2000);
    member5.setHousehold(2);
    member5.setDiet("Normal");
    member5.setAllergies("");
    member5.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member5.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member5.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile partner6 = new Profile();

    partner6.setId(6);
    partner6.setNric_uen("200202841R");
    partner6.setNames("MerryMeal Ltd");
    partner6.setRole("Partner - FSP");
    partner6.setEmail("kitchen.merrymeal@gmail.com");
    partner6.setPhone("63249730");
    partner6.setAddress("11 Eunos Road 8");
    partner6.setUnit("#07-02");
    partner6.setPostal("408601");
    partner6.setStatus("Active");
    partner6.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner6.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner6.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Chicken Chop");
    menu1.setDiet("Normal");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");
 // menu1.setProvider(partner6);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    partner6.setMenu_normal(menu1);

    Meal meal1 = new Meal();

    meal1.setId(1);
    meal1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal1.setStatus("Started");
    meal1.setMenu(menu1);
    meal1.setProvider(partner6);
    meal1.setMember(member5);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal1);

    List<String> ids = new ArrayList<>();

    ids.add(Integer.toString(meal1.getId()));

    SelectWrapper selectWrapper0 = new SelectWrapper();
    SelectWrapper selectWrapper1 = new SelectWrapper();

    selectWrapper1.setIds(ids);

    when(mealService.updateMeals(selectWrapper1, "Deleted")).thenReturn(meals1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Normal", partner6.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Normal", partner6.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(partner6.getEmail())).thenReturn(partner6);

    mockMvc
      .perform(post("/meal-preparation_normal_deletion")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper1))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
   	    allOf(
          hasProperty("id",     equalTo(meal1.getId())),
          hasProperty("status", equalTo(meal1.getStatus()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_normal"));

  }


  @Test
  @DisplayName("Test Unit #28: Volunteer-FSP user get(URL: /meal-preparation) should get rendering of view(name: meal-preparation)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMealPreparationForVolunteerFsp() throws Exception {

    mockMvc
      .perform(get("/meal-preparation"))
      .andDo(print())
      .andExpect(view().name("meal-preparation"));

  }


  @Test
  @DisplayName("Test Unit #29: Volunteer-FSP user get(URL: /meal-preparation_halal) should get rendering of view(name: meal-preparation_halal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMealPreparationHalalForVolunteerFsp() throws Exception {

    Profile member14 = new Profile();

    member14.setId(14);
    member14.setNric_uen("S1000004A");
    member14.setNames("Member #4");
    member14.setRole("Member");
    member14.setEmail("member4@gmail.com");
    member14.setPhone("10000004");
    member14.setAddress("Member Address #4");
    member14.setUnit("");
    member14.setPostal("100004");
    member14.setStatus("Active");
    member14.setNotes("Evaluated on 11/06/2025 00:00 am");
    member14.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member14.setGender("Female");
    member14.setSurname("Test #4");
    member14.setDob(LocalDate.of(1971, 4, 1));
    member14.setDisabilities("Disability #4");
    member14.setIncome(4000);
    member14.setHousehold(4);
    member14.setDiet("Halal");
    member14.setAllergies("Allergy #4");
    member14.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member14.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member14.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu11 = new Menu();

    menu11.setId(11);
    menu11.setSeq_day(1);
    menu11.setSeq_time(1);
    menu11.setName("Nasi Ayam");
    menu11.setDiet("Halal");
    menu11.setFrozen("No");
    menu11.setStatus("Active");
 // menu11.setProvider(volunteer13);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    volunteer13.setMenu_halal(menu11);

    Meal meal6 = new Meal();

    meal6.setId(6);
    meal6.setStatus("Generated");
    meal6.setMenu(menu11);
    meal6.setProvider(volunteer13);
    meal6.setMember(member14);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal6);

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Halal", volunteer13.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Halal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(get("/meal-preparation_halal"))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id", equalTo(meal6.getId()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_halal"));

  }


  @Test
  @DisplayName("Test Unit #30: Volunteer-FSP user get(URL: /meal-preparation_veg) should get rendering of view(name: meal-preparation_veg)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMealPreparationVegForVolunteerFsp() throws Exception {

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Vegetarian", volunteer13.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Vegetarian", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(get("/meal-preparation_veg"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_veg"));

  }


  @Test
  @DisplayName("Test Unit #31: Volunteer-FSP user get(URL: /meal-preparation_soft) should get rendering of view(name: meal-preparation_soft)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMealPreparationSoftForVolunteerFsp() throws Exception {

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Soft", volunteer13.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Soft", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(get("/meal-preparation_soft"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_soft"));

  }


  @Test
  @DisplayName("Test Unit #32: Volunteer-FSP user get(URL: /meal-preparation_normal) should get rendering of view(name: meal-preparation_normal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMealPreparationNormalForVolunteerFsp() throws Exception {

    Profile member15 = new Profile();

    member15.setId(15);
    member15.setNric_uen("S1000005A");
    member15.setNames("Member #5");
    member15.setRole("Member");
    member15.setEmail("member5@gmail.com");
    member15.setPhone("10000005");
    member15.setAddress("Member Address #5");
    member15.setUnit("");
    member15.setPostal("100005");
    member15.setStatus("Active");
    member15.setNotes("Evaluated on 11/06/2025 00:00 am");
    member15.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member15.setGender("Female");
    member15.setSurname("Test #5");
    member15.setDob(LocalDate.of(1971, 5, 1));
    member15.setDisabilities("Disability #5");
    member15.setIncome(5000);
    member15.setHousehold(5);
    member15.setDiet("Normal");
    member15.setAllergies("");
    member15.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member15.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member15.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu12 = new Menu();

    menu12.setId(1);
    menu12.setSeq_day(1);
    menu12.setSeq_time(1);
    menu12.setName("Chicken Chop");
    menu12.setDiet("Normal");
    menu12.setFrozen("Yes");
    menu12.setStatus("Active");
 // menu12.setProvider(volunteer13);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    volunteer13.setMenu_normal(menu12);

    Meal meal17 = new Meal();

    meal17.setId(1);
    meal17.setStatus("Generated");
    meal17.setMenu(menu12);
    meal17.setProvider(volunteer13);
    meal17.setMember(member15);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal17);

    SelectWrapper selectWrapper = new SelectWrapper();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Normal", volunteer13.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Normal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(get("/meal-preparation_normal"))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id", equalTo(meal17.getId()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_normal"));

  }


  @Test
  @DisplayName("Test Unit #33: Volunteer-FSP user post(URL: /meal-preparation_halal_generation) should get rendering of view(name: meal-preparation_halal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldGenerateMealPreparationHalalForVolunteerFsp() throws Exception {

    Profile member14 = new Profile();

    member14.setId(14);
    member14.setNric_uen("S1000004A");
    member14.setNames("Member #4");
    member14.setRole("Member");
    member14.setEmail("member4@gmail.com");
    member14.setPhone("10000004");
    member14.setAddress("Member Address #4");
    member14.setUnit("");
    member14.setPostal("100004");
    member14.setStatus("Active");
    member14.setNotes("Evaluated on 11/06/2025 00:00 am");
    member14.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member14.setGender("Female");
    member14.setSurname("Test #4");
    member14.setDob(LocalDate.of(1971, 4, 1));
    member14.setDisabilities("Disability #4");
    member14.setIncome(4000);
    member14.setHousehold(4);
    member14.setDiet("Halal");
    member14.setAllergies("Allergy #4");
    member14.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member14.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member14.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu11 = new Menu();

    menu11.setId(11);
    menu11.setSeq_day(1);
    menu11.setSeq_time(1);
    menu11.setName("Nasi Ayam");
    menu11.setDiet("Halal");
    menu11.setFrozen("No");
    menu11.setStatus("Active");
 // menu11.setProvider(volunteer13);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    volunteer13.setMenu_halal(menu11);

    Meal meal6 = new Meal();

    meal6.setId(6);
    meal6.setStatus("Generated");
    meal6.setMenu(menu11);
    meal6.setProvider(volunteer13);
    meal6.setMember(member14);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal6);

    SelectWrapper selectWrapper = new SelectWrapper();

    when(mealService.generateMeals("Halal", volunteer13.getEmail())).thenReturn(meals1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Halal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(post("/meal-preparation_halal_generation")
                .with(csrf()))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id", equalTo(meal6.getId()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_halal"));

  }


  @Test
  @DisplayName("Test Unit #34: Volunteer-FSP user post(URL: /meal-preparation_veg_generation) should get rendering of view(name: meal-preparation_veg)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldGenerateMealPreparationVegForVolunteerFsp() throws Exception {

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

    when(mealService.generateMeals("Vegetarian", volunteer13.getEmail())).thenReturn(meals0);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Vegetarian", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(post("/meal-preparation_veg_generation")
                .with(csrf()))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_veg"));

  }


  @Test
  @DisplayName("Test Unit #35: Volunteer-FSP user post(URL: /meal-preparation_soft_generation) should get rendering of view(name: meal-preparation_soft)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldGenerateMealPreparationSoftForVolunteerFsp() throws Exception {

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper = new SelectWrapper();

    when(mealService.generateMeals("Soft", volunteer13.getEmail())).thenReturn(meals0);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/

      Page<Meal> items = new PageImpl<>(meals0);

      when(mealService.findMeals("Soft", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(post("/meal-preparation_soft_generation")
                .with(csrf()))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_soft"));

  }


  @Test
  @DisplayName("Test Unit #36: Volunteer-FSP user post(URL: /meal-preparation_normal_generation) should get rendering of view(name: meal-preparation_normal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldGenerateMealPreparationNormalForVolunteerFsp() throws Exception {

    Profile member15 = new Profile();

    member15.setId(15);
    member15.setNric_uen("S1000005A");
    member15.setNames("Member #5");
    member15.setRole("Member");
    member15.setEmail("member5@gmail.com");
    member15.setPhone("10000005");
    member15.setAddress("Member Address #5");
    member15.setUnit("");
    member15.setPostal("100005");
    member15.setStatus("Active");
    member15.setNotes("Evaluated on 11/06/2025 00:00 am");
    member15.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member15.setGender("Female");
    member15.setSurname("Test #5");
    member15.setDob(LocalDate.of(1971, 5, 1));
    member15.setDisabilities("Disability #5");
    member15.setIncome(5000);
    member15.setHousehold(5);
    member15.setDiet("Normal");
    member15.setAllergies("");
    member15.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member15.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member15.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu12 = new Menu();

    menu12.setId(1);
    menu12.setSeq_day(1);
    menu12.setSeq_time(1);
    menu12.setName("Chicken Chop");
    menu12.setDiet("Normal");
    menu12.setFrozen("Yes");
    menu12.setStatus("Active");
 // menu12.setProvider(volunteer13);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    volunteer13.setMenu_normal(menu12);

    Meal meal17 = new Meal();

    meal17.setId(1);
    meal17.setStatus("Generated");
    meal17.setMenu(menu12);
    meal17.setProvider(volunteer13);
    meal17.setMember(member15);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal17);

    SelectWrapper selectWrapper = new SelectWrapper();

    when(mealService.generateMeals("Normal", volunteer13.getEmail())).thenReturn(meals1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Normal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

   /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(post("/meal-preparation_normal_generation")
                .with(csrf()))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id", equalTo(meal17.getId()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_normal"));

  }


  @Test
  @DisplayName("Test Unit #37: Volunteer-FSP user post(URL: /meal-preparation_halal_updation) should get rendering of view(name: meal-preparation_halal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldUpdateMealPreparationHalalForVolunteerFsp() throws Exception {

    Profile member14 = new Profile();

    member14.setId(14);
    member14.setNric_uen("S1000004A");
    member14.setNames("Member #4");
    member14.setRole("Member");
    member14.setEmail("member4@gmail.com");
    member14.setPhone("10000004");
    member14.setAddress("Member Address #4");
    member14.setUnit("");
    member14.setPostal("100004");
    member14.setStatus("Active");
    member14.setNotes("Evaluated on 11/06/2025 00:00 am");
    member14.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member14.setGender("Female");
    member14.setSurname("Test #4");
    member14.setDob(LocalDate.of(1971, 4, 1));
    member14.setDisabilities("Disability #4");
    member14.setIncome(4000);
    member14.setHousehold(4);
    member14.setDiet("Halal");
    member14.setAllergies("Allergy #4");
    member14.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member14.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member14.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu11 = new Menu();

    menu11.setId(11);
    menu11.setSeq_day(1);
    menu11.setSeq_time(1);
    menu11.setName("Nasi Ayam");
    menu11.setDiet("Halal");
    menu11.setFrozen("No");
    menu11.setStatus("Active");
 // menu11.setProvider(volunteer13);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    volunteer13.setMenu_halal(menu11);

    Meal meal6 = new Meal();

    meal6.setId(6);
    meal6.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal6.setStatus("Started");
    meal6.setMenu(menu11);
    meal6.setProvider(volunteer13);
    meal6.setMember(member14);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal6);

    List<String> ids = new ArrayList<>();

    ids.add(Integer.toString(meal6.getId()));

    SelectWrapper selectWrapper0 = new SelectWrapper();
    SelectWrapper selectWrapper1 = new SelectWrapper();

    selectWrapper1.setIds(ids);

    when(mealService.updateMeals(selectWrapper1, "Started")).thenReturn(meals1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Halal", volunteer13.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Halal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(post("/meal-preparation_halal_updation")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper1)
   	            .param("status", "Started"))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id",     equalTo(meal6.getId())),
          hasProperty("status", equalTo(meal6.getStatus()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_halal"));

  }


  @Test
  @DisplayName("Test Unit #38: Volunteer-FSP user post(URL: /meal-preparation_veg_updation) should get rendering of view(name: meal-preparation_veg)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldUpdateMealPreparationVegForVolunteerFsp() throws Exception {

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper0 = new SelectWrapper();

    when(mealService.updateMeals(selectWrapper0, "Started")).thenReturn(meals0);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Vegetarian", volunteer13.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Vegetarian", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(post("/meal-preparation_veg_updation")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper0)
   	            .param("status", "Started"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_veg"));

  }


  @Test
  @DisplayName("Test Unit #39: Volunteer-FSP user post(URL: /meal-preparation_soft_updation) should get rendering of view(name: meal-preparation_soft)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldUpdateMealPreparationSoftForVolunteerFsp() throws Exception {

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper0 = new SelectWrapper();

    when(mealService.updateMeals(selectWrapper0, "Started")).thenReturn(meals0);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Soft", volunteer13.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Soft", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(post("/meal-preparation_soft_updation")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper0)
   	            .param("status", "Started"))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_soft"));

  }
  

  @Test
  @DisplayName("Test Unit #40: Volunteer-FSP user post(URL: /meal-preparation_normal_updation) should get rendering of view(name: meal-preparation_normal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldUpdateMealPreparationNormalForVolunteerFsp() throws Exception {

    Profile member15 = new Profile();

    member15.setId(15);
    member15.setNric_uen("S1000005A");
    member15.setNames("Member #5");
    member15.setRole("Member");
    member15.setEmail("member5@gmail.com");
    member15.setPhone("10000005");
    member15.setAddress("Member Address #5");
    member15.setUnit("");
    member15.setPostal("100005");
    member15.setStatus("Active");
    member15.setNotes("Evaluated on 11/06/2025 00:00 am");
    member15.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member15.setGender("Female");
    member15.setSurname("Test #5");
    member15.setDob(LocalDate.of(1971, 5, 1));
    member15.setDisabilities("Disability #5");
    member15.setIncome(5000);
    member15.setHousehold(5);
    member15.setDiet("Normal");
    member15.setAllergies("");
    member15.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member15.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member15.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu12 = new Menu();

    menu12.setId(1);
    menu12.setSeq_day(1);
    menu12.setSeq_time(1);
    menu12.setName("Chicken Chop");
    menu12.setDiet("Normal");
    menu12.setFrozen("Yes");
    menu12.setStatus("Active");
 // menu12.setProvider(volunteer13);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    volunteer13.setMenu_normal(menu12);

    Meal meal17 = new Meal();

    meal17.setId(1);
    meal17.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal17.setStatus("Started");
    meal17.setMenu(menu12);
    meal17.setProvider(volunteer13);
    meal17.setMember(member15);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal17);

    List<String> ids = new ArrayList<>();

    ids.add(Integer.toString(meal17.getId()));

    SelectWrapper selectWrapper0 = new SelectWrapper();
    SelectWrapper selectWrapper1 = new SelectWrapper();

    selectWrapper1.setIds(ids);

    when(mealService.updateMeals(selectWrapper1, "Started")).thenReturn(meals1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Normal", volunteer13.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Normal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(post("/meal-preparation_normal_updation")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper1)
   	            .param("status", "Started"))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
   	    allOf(
          hasProperty("id",     equalTo(meal17.getId())),
          hasProperty("status", equalTo(meal17.getStatus()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_normal"));

  }


  @Test
  @DisplayName("Test Unit #41: Volunteer-FSP user post(URL: /meal-preparation_halal_deletion) should get rendering of view(name: meal-preparation_halal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldDeleteMealPreparationHalalForVolunteerFsp() throws Exception {

    Profile member14 = new Profile();

    member14.setId(14);
    member14.setNric_uen("S1000004A");
    member14.setNames("Member #4");
    member14.setRole("Member");
    member14.setEmail("member4@gmail.com");
    member14.setPhone("10000004");
    member14.setAddress("Member Address #4");
    member14.setUnit("");
    member14.setPostal("100004");
    member14.setStatus("Active");
    member14.setNotes("Evaluated on 11/06/2025 00:00 am");
    member14.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member14.setGender("Female");
    member14.setSurname("Test #4");
    member14.setDob(LocalDate.of(1971, 4, 1));
    member14.setDisabilities("Disability #4");
    member14.setIncome(4000);
    member14.setHousehold(4);
    member14.setDiet("Halal");
    member14.setAllergies("Allergy #4");
    member14.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member14.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member14.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu11 = new Menu();

    menu11.setId(11);
    menu11.setSeq_day(1);
    menu11.setSeq_time(1);
    menu11.setName("Nasi Ayam");
    menu11.setDiet("Halal");
    menu11.setFrozen("No");
    menu11.setStatus("Active");
 // menu11.setProvider(volunteer13);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    volunteer13.setMenu_halal(menu11);

    Meal meal6 = new Meal();

    meal6.setId(6);
    meal6.setStatus("Deleted");
    meal6.setMenu(menu11);
    meal6.setProvider(volunteer13);
    meal6.setMember(member14);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal6);

    List<String> ids = new ArrayList<>();

    ids.add(Integer.toString(meal6.getId()));

    SelectWrapper selectWrapper0 = new SelectWrapper();
    SelectWrapper selectWrapper1 = new SelectWrapper();

    selectWrapper1.setIds(ids);

   when(mealService.updateMeals(selectWrapper1, "Deleted")).thenReturn(meals1);

 /********************************************************************************
   Note: Switched to simple pagination.
         See above PAGINATION_SIZE constant.
   ********************************************************************************

   when(mealService.findMeals("Halal", volunteer13.getEmail())).thenReturn(meals1);

  ********************************************************************************/

   Page<Meal> items = new PageImpl<>(meals1);

   when(mealService.findMeals("Halal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

   when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(post("/meal-preparation_halal_deletion")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper1))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
        allOf(
          hasProperty("id", equalTo(meal6.getId())),
          hasProperty("status", equalTo(meal6.getStatus()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_halal"));

  }


  @Test
  @DisplayName("Test Unit #42: Volunteer-FSP user post(URL: /meal-preparation_veg_deletion) should get rendering of view(name: meal-preparation_veg)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldDeleteMealPreparationVegForVolunteerFsp() throws Exception {

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper0 = new SelectWrapper();

    when(mealService.updateMeals(selectWrapper0, "Deleted")).thenReturn(meals0);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Vegetarian", volunteer13.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Vegetarian", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(post("/meal-preparation_veg_deletion")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper0))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_veg"));

  }


  @Test
  @DisplayName("Test Unit #43: Volunteer-FSP user post(URL: /meal-preparation_soft_deletion) should get rendering of view(name: meal-preparation_soft)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldDeleteMealPreparationSoftForVolunteerFsp() throws Exception {

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Meal> meals0 = new ArrayList<>();

    SelectWrapper selectWrapper0 = new SelectWrapper();

    when(mealService.updateMeals(selectWrapper0, "Deleted")).thenReturn(meals0);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Soft", volunteer13.getEmail())).thenReturn(meals0);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals0);

    when(mealService.findMeals("Soft", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(post("/meal-preparation_soft_deletion")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper0))
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
      .andExpect(model().attributeDoesNotExist("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_soft"));

  }
  

  @Test
  @DisplayName("Test Unit #44: Volunteer-FSP user post(URL: /meal-preparation_normal_deletion) should get rendering of view(name: meal-preparation_normal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldDeleteMealPreparationNormalForVolunteerFsp() throws Exception {

    Profile member15 = new Profile();

    member15.setId(15);
    member15.setNric_uen("S1000005A");
    member15.setNames("Member #5");
    member15.setRole("Member");
    member15.setEmail("member5@gmail.com");
    member15.setPhone("10000005");
    member15.setAddress("Member Address #5");
    member15.setUnit("");
    member15.setPostal("100005");
    member15.setStatus("Active");
    member15.setNotes("Evaluated on 11/06/2025 00:00 am");
    member15.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member15.setGender("Female");
    member15.setSurname("Test #5");
    member15.setDob(LocalDate.of(1971, 5, 1));
    member15.setDisabilities("Disability #5");
    member15.setIncome(5000);
    member15.setHousehold(5);
    member15.setDiet("Normal");
    member15.setAllergies("");
    member15.setScheduled(LocalDateTime.of(2026, 6, 11, 0, 0));
    member15.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member15.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

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
    volunteer13.setStatus("Active");
    volunteer13.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer13.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer13.setGender("Male");
    volunteer13.setSurname("Test #3");
    volunteer13.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer13.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Menu menu12 = new Menu();

    menu12.setId(1);
    menu12.setSeq_day(1);
    menu12.setSeq_time(1);
    menu12.setName("Chicken Chop");
    menu12.setDiet("Normal");
    menu12.setFrozen("Yes");
    menu12.setStatus("Active");
 // menu12.setProvider(volunteer13);
    // Note: Commented out because possibly a bug in Lombok will cause stack overflow and setting this property is not crucial for the test.

    volunteer13.setMenu_normal(menu12);

    Meal meal17 = new Meal();

    meal17.setId(1);
    meal17.setStatus("Deleted");
    meal17.setMenu(menu12);
    meal17.setProvider(volunteer13);
    meal17.setMember(member15);

    List<Meal> meals1 = new ArrayList<>();

    meals1.add(meal17);

    List<String> ids = new ArrayList<>();

    ids.add(Integer.toString(meal17.getId()));

    SelectWrapper selectWrapper0 = new SelectWrapper();
    SelectWrapper selectWrapper1 = new SelectWrapper();

    selectWrapper1.setIds(ids);

    when(mealService.updateMeals(selectWrapper1, "Deleted")).thenReturn(meals1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(mealService.findMeals("Normal", volunteer13.getEmail())).thenReturn(meals1);

  ********************************************************************************/

    Page<Meal> items = new PageImpl<>(meals1);

    when(mealService.findMeals("Normal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    mockMvc
      .perform(post("/meal-preparation_normal_deletion")
                .with(csrf())
                .flashAttr("selectWrapper", selectWrapper1))
      .andDo(print())
      .andExpect(model().attribute("meals", hasSize(meals1.size())))
      .andExpect(model().attribute("meals", hasItem(
   	    allOf(
          hasProperty("id", equalTo(meal17.getId())),
          hasProperty("status", equalTo(meal17.getStatus()))
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
      .andExpect(model().attributeExists("menu"))
      .andExpect(model().attribute("selectWrapper",
        allOf(
          hasProperty("ids", equalTo(selectWrapper0.getIds()))
        )
      ))
      .andExpect(view().name("meal-preparation_normal"));

  }



}
