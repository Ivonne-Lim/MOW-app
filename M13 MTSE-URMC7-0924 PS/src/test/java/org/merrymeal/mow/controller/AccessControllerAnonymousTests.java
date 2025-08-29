/*
 * Class Name:  AccessControllerAnonymousTests
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


@WebMvcTest(AccessController.class)
@AutoConfigureMockMvc(addFilters = false)
  // ISSUE: Security filters not working for anonymous

@TestMethodOrder(MethodOrderer.DisplayName.class)


public class AccessControllerAnonymousTests {



  @MockBean
  private ProfileService profileService;


  @Autowired
  private MockMvc mockMvc;



  @Test
  @DisplayName("Test Unit #01: Anonymous user get(URL: /access) should get rendering of view(name: access)")

  @WithAnonymousUser

  void shouldShowAccessForAnonymous() throws Exception {

    mockMvc
      .perform(get("/access"))
      .andDo(print())
      .andExpect(view().name("access"));

  }


  @Test
  @DisplayName("Test Unit #02: Anonymous user post(URL: /login) should get redirected to get(URL: /login_failure)")

  @WithAnonymousUser

  void shouldLoginAccessFailureForAnonymous() throws Exception {

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
    member2.setPassword("password");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    when(profileService.findProfileByEmailAndPassword(member2.getEmail(), "invalid")).thenReturn(null);

    mockMvc
      .perform(post("/login")
                .with(csrf())
                .param("email",    member2.getEmail())
                .param("password", "invalid"))
      .andDo(print())
      .andExpect(view().name("redirect:/login_failure"));

  }


  @Test
  @DisplayName("Test Unit #03: Anonymous user post(URL: /login) should get redirected to get(URL: /login_success)")

  @WithAnonymousUser

  void shouldLoginAccessSuccessForAnonymous() throws Exception {

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
    member2.setPassword("password");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    when(profileService.findProfileByEmailAndPassword(member2.getEmail(), member2.getPassword())).thenReturn(member2);

    mockMvc
      .perform(post("/login")
                .with(csrf())
                .param("email",    member2.getEmail())
                .param("password", member2.getPassword()))
      .andDo(print())
      .andExpect(view().name("redirect:/login_success"));

  }


  @Test
  @DisplayName("Test Unit #04: Anonymous user get(URL: /login_failure) should get rendering of view(name: access)")

  @WithAnonymousUser

  void shouldShowLoginFailureForAnonymous() throws Exception {

      mockMvc
        .perform(get("/login_failure"))
        .andDo(print())
        .andExpect(model().attribute("login_failure", equalTo("Login failed due to incorrect Email Address or Password.")))
        .andExpect(view().name("access"));

  }


  @Test
  @DisplayName("Test Unit #05: Anonymous user get(URL: /logout_success) should get rendering of view(name: access)")

  @WithAnonymousUser

  void shouldShowLogoutSuccessForAnonymous() throws Exception {

      mockMvc
        .perform(get("/logout_success"))
        .andDo(print())
        .andExpect(model().attribute("logout_success", equalTo("Logout successful.")))
        .andExpect(view().name("access"));

  }



}
