/*
 * Class Name:  MenusControllerAllTests
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


@WebMvcTest(MenusController.class)
@AutoConfigureMockMvc(addFilters = true)

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class MenusControllerAllTests {



  @MockBean
  private ProfileService profileService;

  @MockBean
  private MenuService menuService;


  @Autowired
  private MockMvc mockMvc;



  final String PAGINATION_SIZE = "2";
  // Default and maximum should be 15 as measured.



  @Test
  @DisplayName("Test Unit #01: Partner-FSP user get(URL: /menu-planning) should get rendering of view(name: menu-planning)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMenuPlanningForPartnerFsp() throws Exception {

    mockMvc
      .perform(get("/menu-planning"))
      .andDo(print())
      .andExpect(view().name("menu-planning"));

  }


  @Test
  @DisplayName("Test Unit #02: Partner-FSP user get(URL: /menu-planning_halal) should get rendering of view(name: menu-planning_halal)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMenuPlanningHalalForPartnerFsp() throws Exception {

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

    List<Menu> menus = new ArrayList<>();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Halal", partner7.getEmail())).thenReturn(menus);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus);

    when(menuService.findMenus("Halal", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/menu-planning_halal"))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("menu-planning_halal"));

  }


  @Test
  @DisplayName("Test Unit #03: Partner-FSP user get(URL: /menu-planning_veg) should get rendering of view(name: menu-planning_veg)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMenuPlanningVegForPartnerFsp() throws Exception {

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

    List<Menu> menus = new ArrayList<>();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Vegetarian", partner7.getEmail())).thenReturn(menus);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus);

    when(menuService.findMenus("Vegetarian", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/menu-planning_veg"))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("menu-planning_veg"));

  }


  @Test
  @DisplayName("Test Unit #04: Partner-FSP user get(URL: /menu-planning_soft) should get rendering of view(name: menu-planning_soft)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMenuPlanningSoftForPartnerFsp() throws Exception {

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
    partner7.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Menu> menus = new ArrayList<>();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Soft", partner7.getEmail())).thenReturn(menus);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus);

    when(menuService.findMenus("Soft", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/menu-planning_soft"))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("menu-planning_soft"));

  }


  @Test
  @DisplayName("Test Unit #05: Partner-FSP user get(URL: /menu-planning_normal) should get rendering of view(name: menu-planning_normal)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMenuPlanningNormalForPartnerFsp() throws Exception {

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

    List<Menu> menus = new ArrayList<>();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Normal", partner7.getEmail())).thenReturn(menus);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus);

    when(menuService.findMenus("Normal", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/menu-planning_normal"))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("menu-planning_normal"));

  }


  @Test
  @DisplayName("Test Unit #06: Partner-FSP user post(URL: /menu-planning_halal_deletion) should get rendering of view(name: menu-planning_halal)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldDeleteMenuPlanningHalalForPartnerFsp() throws Exception {

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

    Menu menu5d = new Menu();

    menu5d.setId(5);
    menu5d.setSeq_day(1);
    menu5d.setSeq_time(1);
    menu5d.setName("Nasi Ayam");
    menu5d.setDiet("Halal");
    menu5d.setFrozen("No");
    menu5d.setStatus("Deleted");
    menu5d.setProvider(partner7);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu5d);

    when(menuService.deleteMenuById(Integer.toString(menu5.getId()))).thenReturn(menu5d);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Halal", partner7.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Halal", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_halal_deletion")
                .with(csrf())
   	            .param("id", Integer.toString(menu5.getId())))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id",     equalTo(menu5d.getId())),
          hasProperty("status", equalTo(menu5d.getStatus()))
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
      .andExpect(view().name("menu-planning_halal"));

  }


  @Test
  @DisplayName("Test Unit #07: Partner-FSP user post(URL: /menu-planning_veg_deletion) should get rendering of view(name: menu-planning_veg)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldDeleteMenuPlanningVegForPartnerFsp() throws Exception {

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

    Menu menu9 = new Menu();

    menu9.setId(9);
    menu9.setSeq_day(1);
    menu9.setSeq_time(1);
    menu9.setName("Bee Hoon");
    menu9.setDiet("Vegetarian");
    menu9.setFrozen("No");
    menu9.setStatus("Active");
    menu9.setProvider(partner7);

    Menu menu9d = new Menu();

    menu9d.setId(9);
    menu9d.setSeq_day(1);
    menu9d.setSeq_time(1);
    menu9d.setName("Bee Hoon");
    menu9d.setDiet("Vegetarian");
    menu9d.setFrozen("No");
    menu9d.setStatus("Deleted");
    menu9d.setProvider(partner7);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu9d);

    when(menuService.deleteMenuById(Integer.toString(menu9.getId()))).thenReturn(menu9d);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Vegetarian", partner7.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Vegetarian", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_veg_deletion")
                .with(csrf())
   	            .param("id", Integer.toString(menu9.getId())))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id",     equalTo(menu9d.getId())),
          hasProperty("status", equalTo(menu9d.getStatus()))
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
      .andExpect(view().name("menu-planning_veg"));

  }


  @Test
  @DisplayName("Test Unit #08: Partner-FSP user post(URL: /menu-planning_soft_deletion) should get rendering of view(name: menu-planning_soft)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldDeleteMenuPlanningSoftForPartnerFsp() throws Exception {

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

    Menu menu10 = new Menu();

    menu10.setId(10);
    menu10.setSeq_day(1);
    menu10.setSeq_time(1);
    menu10.setName("Masala Dosa");
    menu10.setDiet("Soft");
    menu10.setFrozen("Yes");
    menu10.setStatus("Active");
    menu10.setProvider(partner6);

    Menu menu10d = new Menu();

    menu10d.setId(10);
    menu10d.setSeq_day(1);
    menu10d.setSeq_time(1);
    menu10d.setName("Masala Dosa");
    menu10d.setDiet("Soft");
    menu10d.setFrozen("Yes");
    menu10d.setStatus("Deleted");
    menu10d.setProvider(partner6);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu10d);

    when(menuService.deleteMenuById(Integer.toString(menu10.getId()))).thenReturn(menu10d);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Soft", partner6.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Soft", partner6.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_soft_deletion")
                .with(csrf())
   	            .param("id", Integer.toString(menu10.getId())))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id",     equalTo(menu10d.getId())),
          hasProperty("status", equalTo(menu10d.getStatus()))
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
      .andExpect(view().name("menu-planning_soft"));

  }


  @Test
  @DisplayName("Test Unit #09: Partner-FSP user post(URL: /menu-planning_normal_deletion) should get rendering of view(name: menu-planning_normal)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldDeleteMenuPlanningNormalForPartnerFsp() throws Exception {

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

    Menu menu1d = new Menu();

    menu1d.setId(1);
    menu1d.setSeq_day(1);
    menu1d.setSeq_time(1);
    menu1d.setName("Chicken Chop");
    menu1d.setDiet("Normal");
    menu1d.setFrozen("Yes");
    menu1d.setStatus("Deleted");
    menu1d.setProvider(partner6);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu1d);

    when(menuService.deleteMenuById(Integer.toString(menu1.getId()))).thenReturn(menu1d);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Normal", partner6.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Normal", partner6.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_normal_deletion")
                .with(csrf())
   	            .param("id", Integer.toString(menu1.getId())))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id", equalTo(menu1d.getId())),
          hasProperty("status", equalTo(menu1d.getStatus()))
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
      .andExpect(view().name("menu-planning_normal"));

  }


  @Test
  @DisplayName("Test Unit #10: Partner-FSP user get(URL: /menu-planning_halal_menu) should get rendering of view(name: menu-planning_halal_menu)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMenuPlanningHalalMenuForPartnerFsp() throws Exception {

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

    when(profileService.findProfileByEmail(partner7.getEmail())).thenReturn(partner7);

    when(menuService.findMenuById(Integer.toString(menu5.getId()))).thenReturn(menu5);

    mockMvc
      .perform(get("/menu-planning_halal_menu")
   	            .param("id", Integer.toString(menu5.getId())))
      .andDo(print())
      .andExpect(model().attribute("menu",
        allOf(
          hasProperty("id", equalTo(menu5.getId()))
        )
      ))
      .andExpect(model().attribute("provider",
        allOf(
          hasProperty("id", equalTo(partner7.getId()))
        )
      ))

      .andExpect(view().name("menu-planning_halal_menu"));

  }


  @Test
  @DisplayName("Test Unit #11: Partner-FSP user get(URL: /menu-planning_veg_menu) should get rendering of view(name: menu-planning_veg_menu)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMenuPlanningVegMenuForPartnerFsp() throws Exception {

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

    Menu menu9 = new Menu();

    menu9.setId(9);
    menu9.setSeq_day(1);
    menu9.setSeq_time(1);
    menu9.setName("Bee Hoon");
    menu9.setDiet("Vegetarian");
    menu9.setFrozen("No");
    menu9.setStatus("Active");
    menu9.setProvider(partner7);

    when(profileService.findProfileByEmail(partner7.getEmail())).thenReturn(partner7);

    when(menuService.findMenuById(Integer.toString(menu9.getId()))).thenReturn(menu9);

    mockMvc
      .perform(get("/menu-planning_veg_menu")
   	            .param("id", Integer.toString(menu9.getId())))
      .andDo(print())
      .andExpect(model().attribute("menu",
        allOf(
          hasProperty("id", equalTo(menu9.getId()))
        )
      ))
      .andExpect(model().attribute("provider",
        allOf(
          hasProperty("id", equalTo(partner7.getId()))
        )
      ))
      .andExpect(view().name("menu-planning_veg_menu"));

  }


  @Test
  @DisplayName("Test Unit #12: Partner-FSP user get(URL: /menu-planning_soft_menu) should get rendering of view(name: menu-planning_soft_menu)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMenuPlanningSoftMenuForPartnerFsp() throws Exception {

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

    Menu menu10 = new Menu();

    menu10.setId(10);
    menu10.setSeq_day(1);
    menu10.setSeq_time(1);
    menu10.setName("Masala Dosa");
    menu10.setDiet("Soft");
    menu10.setFrozen("Yes");
    menu10.setStatus("Active");
    menu10.setProvider(partner6);

    when(profileService.findProfileByEmail(partner6.getEmail())).thenReturn(partner6);

    when(menuService.findMenuById(Integer.toString(menu10.getId()))).thenReturn(menu10);

    mockMvc
      .perform(get("/menu-planning_soft_menu")
   	            .param("id", Integer.toString(menu10.getId())))
      .andDo(print())
      .andExpect(model().attribute("menu",
        allOf(
          hasProperty("id", equalTo(menu10.getId()))
        )
      ))
      .andExpect(model().attribute("provider",
        allOf(
          hasProperty("id", equalTo(partner6.getId()))
        )
      ))
      .andExpect(view().name("menu-planning_soft_menu"));

  }


  @Test
  @DisplayName("Test Unit #13: Partner-FSP user get(URL: /menu-planning_normal_menu) should get rendering of view(name: menu-planning_normal_menu)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldShowMenuPlanningNormalMenuForPartnerFsp() throws Exception {

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

    when(profileService.findProfileByEmail(partner6.getEmail())).thenReturn(partner6);

    when(menuService.findMenuById(Integer.toString(menu1.getId()))).thenReturn(menu1);

    mockMvc
      .perform(get("/menu-planning_normal_menu")
   	            .param("id", Integer.toString(menu1.getId())))
      .andDo(print())
      .andExpect(model().attribute("menu",
        allOf(
          hasProperty("id", equalTo(menu1.getId()))
        )
      ))
      .andExpect(model().attribute("provider",
        allOf(
          hasProperty("id", equalTo(partner6.getId()))
        )
      ))
      .andExpect(view().name("menu-planning_normal_menu"));

  }


  @Test
  @DisplayName("Test Unit #14: Partner-FSP user post(URL: /menu-planning_halal_menu) should get rendering of view(name: menu-planning_halal)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldSaveMenuPlanningHalalMenuForPartnerFsp() throws Exception {

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

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu5);

    when(menuService.saveMenu(menu5, partner7.getEmail())).thenReturn(menu5);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Halal", partner7.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Halal", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_halal_menu")
                .with(csrf())
                .flashAttr("menu", menu5))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id", equalTo(menu5.getId()))
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
      .andExpect(view().name("menu-planning_halal"));

  }


  @Test
  @DisplayName("Test Unit #15: Partner-FSP user post(URL: /menu-planning_veg_menu) should get rendering of view(name: menu-planning_veg)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldSaveMenuPlanningVegMenuForPartnerFsp() throws Exception {

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

    Menu menu9 = new Menu();

    menu9.setId(9);
    menu9.setSeq_day(1);
    menu9.setSeq_time(1);
    menu9.setName("Bee Hoon");
    menu9.setDiet("Vegetarian");
    menu9.setFrozen("No");
    menu9.setStatus("Active");
    menu9.setProvider(partner7);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu9);

    when(menuService.saveMenu(menu9, partner7.getEmail())).thenReturn(menu9);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Vegetarian", partner7.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Vegetarian", partner7.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_veg_menu")
                .with(csrf())
                .flashAttr("menu", menu9))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id", equalTo(menu9.getId()))
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
      .andExpect(view().name("menu-planning_veg"));

  }


  @Test
  @DisplayName("Test Unit #16: Partner-FSP user post(URL: /menu-planning_soft_menu) should get rendering of view(name: menu-planning_soft)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldSaveMenuPlanningSoftMenuForPartnerFsp() throws Exception {

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

    Menu menu10 = new Menu();

    menu10.setId(10);
    menu10.setSeq_day(1);
    menu10.setSeq_time(1);
    menu10.setName("Masala Dosa");
    menu10.setDiet("Soft");
    menu10.setFrozen("Yes");
    menu10.setStatus("Active");
    menu10.setProvider(partner6);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu10);

    when(menuService.saveMenu(menu10, partner6.getEmail())).thenReturn(menu10);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Soft", partner6.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Soft", partner6.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_soft_menu")
                .with(csrf())
                .flashAttr("menu", menu10))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id", equalTo(menu10.getId()))
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
      .andExpect(view().name("menu-planning_soft"));

  }


  @Test
  @DisplayName("Test Unit #17: Partner-FSP user post(URL: /menu-planning_normal_menu) should get rendering of view(name: menu-planning_normal)")

  @WithMockUser(username = "kitchen.merrymeal@gmail.com", roles = "PARTNER - FSP")

  void shouldSaveMenuPlanningNormalMenuForPartnerFsp() throws Exception {

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

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu1);

    when(menuService.saveMenu(menu1, partner6.getEmail())).thenReturn(menu1);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Normal", partner6.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Normal", partner6.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_normal_menu")
                .with(csrf())
                .flashAttr("menu", menu1))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id", equalTo(menu1.getId()))
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
      .andExpect(view().name("menu-planning_normal"));

  }


  @Test
  @DisplayName("Test Unit #18: Volunteer-FSP user get(URL: /menu-planning) should get rendering of view(name: menu-planning)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMenuPlanningForVolunteerFsp() throws Exception {

    mockMvc
      .perform(get("/menu-planning"))
      .andDo(print())
      .andExpect(view().name("menu-planning"));

  }


  @Test
  @DisplayName("Test Unit #19: Volunteer-FSP user get(URL: /menu-planning_halal) should get rendering of view(name: menu-planning_halal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMenuPlanningHalalForVolunteerFsp() throws Exception {

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

    List<Menu> menus = new ArrayList<>();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Halal", volunteer13.getEmail())).thenReturn(menus);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus);

    when(menuService.findMenus("Halal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/menu-planning_halal"))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("menu-planning_halal"));

  }


  @Test
  @DisplayName("Test Unit #20: Volunteer-FSP user get(URL: /menu-planning_veg) should get rendering of view(name: menu-planning_veg)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMenuPlanningVegForVolunteerFsp() throws Exception {

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

    List<Menu> menus = new ArrayList<>();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Vegetarian", volunteer13.getEmail())).thenReturn(menus);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus);

    when(menuService.findMenus("Vegetarian", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/menu-planning_veg"))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("menu-planning_veg"));

  }


  @Test
  @DisplayName("Test Unit #21: Volunteer-FSP user get(URL: /menu-planning_soft) should get rendering of view(name: menu-planning_soft)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMenuPlanningSoftForVolunteerFsp() throws Exception {

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

    List<Menu> menus = new ArrayList<>();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Soft", volunteer13.getEmail())).thenReturn(menus);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus);

    when(menuService.findMenus("Soft", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/menu-planning_soft"))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("menu-planning_soft"));

  }


  @Test
  @DisplayName("Test Unit #22: Volunteer-FSP user get(URL: /menu-planning_normal) should get rendering of view(name: menu-planning_normal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMenuPlanningNormalForVolunteerFsp() throws Exception {

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

    List<Menu> menus = new ArrayList<>();

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Normal", volunteer13.getEmail())).thenReturn(menus);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus);

    when(menuService.findMenus("Normal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(get("/menu-planning_normal"))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus.size())))
 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************/
      .andExpect(model().attribute("pages",   equalTo(1)))
      .andExpect(model().attribute("current", equalTo(0)))
      .andExpect(model().attribute("size",    equalTo(Integer.parseInt(PAGINATION_SIZE))))
 /********************************************************************************/
      .andExpect(view().name("menu-planning_normal"));

  }


  @Test
  @DisplayName("Test Unit #23: Volunteer-FSP user post(URL: /menu-planning_halal_deletion) should get rendering of view(name: menu-planning_halal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldDeleteMenuPlanningHalalForVolunteerFsp() throws Exception {

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
    menu11.setProvider(volunteer13);

    Menu menu11d = new Menu();

    menu11d.setId(11);
    menu11d.setSeq_day(1);
    menu11d.setSeq_time(1);
    menu11d.setName("Nasi Ayam");
    menu11d.setDiet("Halal");
    menu11d.setFrozen("No");
    menu11d.setStatus("Deleted");
    menu11d.setProvider(volunteer13);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu11d);

    when(menuService.deleteMenuById(Integer.toString(menu11.getId()))).thenReturn(menu11d);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Halal", volunteer13.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Halal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

    mockMvc
      .perform(post("/menu-planning_halal_deletion")
                .with(csrf())
   	            .param("id", Integer.toString(menu11.getId())))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id",     equalTo(menu11d.getId())),
          hasProperty("status", equalTo(menu11d.getStatus()))
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
      .andExpect(view().name("menu-planning_halal"));

  }


  @Test
  @DisplayName("Test Unit #24: Volunteer-FSP user post(URL: /menu-planning_veg_deletion) should get rendering of view(name: menu-planning_veg)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldDeleteMenuPlanningVegForVolunteerFsp() throws Exception {

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

    menu12.setId(12);
    menu12.setSeq_day(1);
    menu12.setSeq_time(1);
    menu12.setName("Bee Hoon");
    menu12.setDiet("Vegetarian");
    menu12.setFrozen("No");
    menu12.setStatus("Active");
    menu12.setProvider(volunteer13);

    Menu menu12d = new Menu();

    menu12d.setId(12);
    menu12d.setSeq_day(1);
    menu12d.setSeq_time(1);
    menu12d.setName("Bee Hoon");
    menu12d.setDiet("Vegetarian");
    menu12d.setFrozen("No");
    menu12d.setStatus("Deleted");
    menu12d.setProvider(volunteer13);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu12d);

    when(menuService.deleteMenuById(Integer.toString(menu12.getId()))).thenReturn(menu12d);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Vegetarian", volunteer13.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Vegetarian", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_veg_deletion")
                .with(csrf())
   	            .param("id", Integer.toString(menu12.getId())))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id",     equalTo(menu12d.getId())),
          hasProperty("status", equalTo(menu12d.getStatus()))
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
      .andExpect(view().name("menu-planning_veg"));

  }


  @Test
  @DisplayName("Test Unit #25: Volunteer-FSP user post(URL: /menu-planning_soft_deletion) should get rendering of view(name: menu-planning_soft)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldDeleteMenuPlanningSoftForVolunteerFsp() throws Exception {

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

    Menu menu13 = new Menu();

    menu13.setId(13);
    menu13.setSeq_day(1);
    menu13.setSeq_time(1);
    menu13.setName("Masala Dosa");
    menu13.setDiet("Soft");
    menu13.setFrozen("No");
    menu13.setStatus("Active");
    menu13.setProvider(volunteer13);

    Menu menu13d = new Menu();

    menu13d.setId(13);
    menu13d.setSeq_day(1);
    menu13d.setSeq_time(1);
    menu13d.setName("Masala Dosa");
    menu13d.setDiet("Soft");
    menu13d.setFrozen("No");
    menu13d.setStatus("Deleted");
    menu13d.setProvider(volunteer13);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu13d);

    when(menuService.deleteMenuById(Integer.toString(menu13.getId()))).thenReturn(menu13d);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Soft", volunteer13.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Soft", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_soft_deletion")
                .with(csrf())
   	            .param("id", Integer.toString(menu13.getId())))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id",     equalTo(menu13d.getId())),
          hasProperty("status", equalTo(menu13d.getStatus()))
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
      .andExpect(view().name("menu-planning_soft"));

  }


  @Test
  @DisplayName("Test Unit #26: Volunteer-FSP user post(URL: /menu-planning_normal_deletion) should get rendering of view(name: menu-planning_normal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldDeleteMenuPlanningNormalForVolunteerFsp() throws Exception {

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

    Menu menu14 = new Menu();

    menu14.setId(14);
    menu14.setSeq_day(1);
    menu14.setSeq_time(1);
    menu14.setName("Chicken Chop");
    menu14.setDiet("Normal");
    menu14.setFrozen("No");
    menu14.setStatus("Active");
    menu14.setProvider(volunteer13);

    Menu menu14d = new Menu();

    menu14d.setId(14);
    menu14d.setSeq_day(1);
    menu14d.setSeq_time(1);
    menu14d.setName("Chicken Chop");
    menu14d.setDiet("Normal");
    menu14d.setFrozen("No");
    menu14d.setStatus("Deleted");
    menu14d.setProvider(volunteer13);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu14d);

    when(menuService.deleteMenuById(Integer.toString(menu14.getId()))).thenReturn(menu14d);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Normal", volunteer13.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Normal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_normal_deletion")
                .with(csrf())
   	            .param("id", Integer.toString(menu14.getId())))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id",     equalTo(menu14d.getId())),
          hasProperty("status", equalTo(menu14d.getStatus()))
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
      .andExpect(view().name("menu-planning_normal"));

  }


  @Test
  @DisplayName("Test Unit #27: Volunteer-FSP user get(URL: /menu-planning_halal_menu) should get rendering of view(name: menu-planning_halal_menu)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMenuPlanningHalalMenuForVolunteerFsp() throws Exception {

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
    menu11.setProvider(volunteer13);

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    when(menuService.findMenuById(Integer.toString(menu11.getId()))).thenReturn(menu11);

    mockMvc
      .perform(get("/menu-planning_halal_menu")
   	            .param("id", Integer.toString(menu11.getId())))
      .andDo(print())
      .andExpect(model().attribute("menu",
        allOf(
          hasProperty("id", equalTo(menu11.getId()))
        )
      ))
      .andExpect(model().attribute("provider",
        allOf(
          hasProperty("id", equalTo(volunteer13.getId()))
        )
      ))

      .andExpect(view().name("menu-planning_halal_menu"));

  }


  @Test
  @DisplayName("Test Unit #28: Volunteer-FSP user get(URL: /menu-planning_veg_menu) should get rendering of view(name: menu-planning_veg_menu)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMenuPlanningVegMenuForVolunteerFsp() throws Exception {

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

    menu12.setId(12);
    menu12.setSeq_day(1);
    menu12.setSeq_time(1);
    menu12.setName("Bee Hoon");
    menu12.setDiet("Vegetarian");
    menu12.setFrozen("No");
    menu12.setStatus("Active");
    menu12.setProvider(volunteer13);

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    when(menuService.findMenuById(Integer.toString(menu12.getId()))).thenReturn(menu12);

    mockMvc
      .perform(get("/menu-planning_veg_menu")
   	            .param("id", Integer.toString(menu12.getId())))
      .andDo(print())
      .andExpect(model().attribute("menu",
        allOf(
          hasProperty("id", equalTo(menu12.getId()))
        )
      ))
      .andExpect(model().attribute("provider",
        allOf(
          hasProperty("id", equalTo(volunteer13.getId()))
        )
      ))
      .andExpect(view().name("menu-planning_veg_menu"));

  }


  @Test
  @DisplayName("Test Unit #29: Volunteer-FSP user get(URL: /menu-planning_soft_menu) should get rendering of view(name: menu-planning_soft_menu)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMenuPlanningSoftMenuForVolunteerFsp() throws Exception {

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

    Menu menu13 = new Menu();

    menu13.setId(13);
    menu13.setSeq_day(1);
    menu13.setSeq_time(1);
    menu13.setName("Masala Dosa");
    menu13.setDiet("Soft");
    menu13.setFrozen("Yes");
    menu13.setStatus("Active");
    menu13.setProvider(volunteer13);

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    when(menuService.findMenuById(Integer.toString(menu13.getId()))).thenReturn(menu13);

    mockMvc
      .perform(get("/menu-planning_soft_menu")
   	            .param("id", Integer.toString(menu13.getId())))
      .andDo(print())
      .andExpect(model().attribute("menu",
        allOf(
          hasProperty("id", equalTo(menu13.getId()))
        )
      ))
      .andExpect(model().attribute("provider",
        allOf(
          hasProperty("id", equalTo(volunteer13.getId()))
        )
      ))
      .andExpect(view().name("menu-planning_soft_menu"));

  }


  @Test
  @DisplayName("Test Unit #30: Volunteer-FSP user get(URL: /menu-planning_normal_menu) should get rendering of view(name: menu-planning_normal_menu)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowMenuPlanningNormalMenuForVolunteerFsp() throws Exception {

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

    Menu menu14 = new Menu();

    menu14.setId(14);
    menu14.setSeq_day(1);
    menu14.setSeq_time(1);
    menu14.setName("Chicken Chop");
    menu14.setDiet("Normal");
    menu14.setFrozen("Yes");
    menu14.setStatus("Active");
    menu14.setProvider(volunteer13);

    when(profileService.findProfileByEmail(volunteer13.getEmail())).thenReturn(volunteer13);

    when(menuService.findMenuById(Integer.toString(menu14.getId()))).thenReturn(menu14);

    mockMvc
      .perform(get("/menu-planning_normal_menu")
   	            .param("id", Integer.toString(menu14.getId())))
      .andDo(print())
      .andExpect(model().attribute("menu",
        allOf(
          hasProperty("id", equalTo(menu14.getId()))
        )
      ))
      .andExpect(model().attribute("provider",
        allOf(
          hasProperty("id", equalTo(volunteer13.getId()))
        )
      ))
      .andExpect(view().name("menu-planning_normal_menu"));

  }


  @Test
  @DisplayName("Test Unit #31: Volunteer-FSP user post(URL: /menu-planning_halal_menu) should get rendering of view(name: menu-planning_halal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldSaveMenuPlanningHalalMenuForVolunteerFsp() throws Exception {

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
    menu11.setProvider(volunteer13);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu11);

    when(menuService.saveMenu(menu11, volunteer13.getEmail())).thenReturn(menu11);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Halal", volunteer13.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Halal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_halal_menu")
                .with(csrf())
                .flashAttr("menu", menu11))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id", equalTo(menu11.getId()))
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
      .andExpect(view().name("menu-planning_halal"));

  }


  @Test
  @DisplayName("Test Unit #32: Volunteerr-FSP user post(URL: /menu-planning_veg_menu) should get rendering of view(name: menu-planning_veg)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldSaveMenuPlanningVegMenuForVolunteerFsp() throws Exception {

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

    menu12.setId(12);
    menu12.setSeq_day(1);
    menu12.setSeq_time(1);
    menu12.setName("Bee Hoon");
    menu12.setDiet("Vegetarian");
    menu12.setFrozen("No");
    menu12.setStatus("Active");
    menu12.setProvider(volunteer13);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu12);

    when(menuService.saveMenu(menu12, volunteer13.getEmail())).thenReturn(menu12);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Vegetarian", volunteer13.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Vegetarian", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_veg_menu")
                .with(csrf())
                .flashAttr("menu", menu12))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id", equalTo(menu12.getId()))
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
      .andExpect(view().name("menu-planning_veg"));

  }


  @Test
  @DisplayName("Test Unit #33: Volunteer-FSP user post(URL: /menu-planning_soft_menu) should get rendering of view(name: menu-planning_soft)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldSaveMenuPlanningSoftMenuForVolunteerFsp() throws Exception {

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

    Menu menu13 = new Menu();

    menu13.setId(13);
    menu13.setSeq_day(1);
    menu13.setSeq_time(1);
    menu13.setName("Masala Dosa");
    menu13.setDiet("Soft");
    menu13.setFrozen("Yes");
    menu13.setStatus("Active");
    menu13.setProvider(volunteer13);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu13);

    when(menuService.saveMenu(menu13, volunteer13.getEmail())).thenReturn(menu13);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Soft", volunteer13.getEmail())).thenReturn(menus1);

  ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Soft", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_soft_menu")
                .with(csrf())
                .flashAttr("menu", menu13))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id", equalTo(menu13.getId()))
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
      .andExpect(view().name("menu-planning_soft"));

  }


  @Test
  @DisplayName("Test Unit #34: Volunteer-FSP user post(URL: /menu-planning_normal_menu) should get rendering of view(name: menu-planning_normal)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldSaveMenuPlanningNormalMenuForVolunteerFsp() throws Exception {

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

    Menu menu14 = new Menu();

    menu14.setId(14);
    menu14.setSeq_day(1);
    menu14.setSeq_time(1);
    menu14.setName("Chicken Chop");
    menu14.setDiet("Normal");
    menu14.setFrozen("Yes");
    menu14.setStatus("Active");
    menu14.setProvider(volunteer13);

    List<Menu> menus1 = new ArrayList<>();

    menus1.add(menu14);

    when(menuService.saveMenu(menu14, volunteer13.getEmail())).thenReturn(menu14);

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

    when(menuService.findMenus("Normal", volunteer13.getEmail())).thenReturn(menus1);

 ********************************************************************************/

    Page<Menu> items = new PageImpl<>(menus1);

    when(menuService.findMenus("Normal", volunteer13.getEmail(), 0, Integer.parseInt(PAGINATION_SIZE))).thenReturn(items);

 /********************************************************************************/

    mockMvc
      .perform(post("/menu-planning_normal_menu")
                .with(csrf())
                .flashAttr("menu", menu14))
      .andDo(print())
      .andExpect(model().attribute("menus", hasSize(menus1.size())))
      .andExpect(model().attribute("menus", hasItem(
        allOf(
          hasProperty("id", equalTo(menu14.getId()))
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
      .andExpect(view().name("menu-planning_normal"));

  }



}
