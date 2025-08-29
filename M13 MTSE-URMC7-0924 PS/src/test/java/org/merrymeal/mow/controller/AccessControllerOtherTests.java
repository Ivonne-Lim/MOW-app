/*
 * Class Name:  AccessControllerOtherTests
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

import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

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


@WebMvcTest(AccessController.class)
@AutoConfigureMockMvc(addFilters = false)
  // ISSUE: Security filters not working for other

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class AccessControllerOtherTests {



  @MockBean
  private ProfileService profileService;


  @Autowired
  private MockMvc mockMvc;



  @Test
  @DisplayName("Test Unit #01: Administrator user post(URL: /logout) should get redirected to get(URL: /logout_success)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldLogoutAccessSuccessForAdministrator() throws Exception {

    mockMvc
      .perform(post("/logout")
                .with(csrf()))
      .andDo(print())
      .andExpect(view().name("redirect:/logout_success"));

  }


  @Test
  @DisplayName("Test Unit #02: Administrator user get(URL: /login_success) should get rendering of view(name: access)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowLoginSuccessForAdministrator() throws Exception {

      mockMvc
        .perform(get("/login_success"))
        .andDo(print())
        .andExpect(model().attribute("login_success", equalTo("Login successful.")))
        .andExpect(view().name("access"));

  }


  @Test
  @DisplayName("Test Unit #03: Member user post(URL: /logout) should get redirected to get(URL: /logout_success)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldLogoutAccessSuccessForMember() throws Exception {

    mockMvc
      .perform(post("/logout")
                .with(csrf()))
      .andDo(print())
      .andExpect(view().name("redirect:/logout_success"));

  }


  @Test
  @DisplayName("Test Unit #04: Member user get(URL: /login_success) should get rendering of view(name: access)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowLoginSuccessForMember() throws Exception {

      mockMvc
        .perform(get("/login_success"))
        .andDo(print())
        .andExpect(model().attribute("login_success", equalTo("Login successful.")))
        .andExpect(view().name("access"));

  }



  @Test
  @DisplayName("Test Unit #05: Partner-FSP user post(URL: /logout) should get redirected to get(URL: /logout_success)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldLogoutAccessSuccessForPartnerFsp() throws Exception {

    mockMvc
      .perform(post("/logout")
                .with(csrf()))
      .andDo(print())
      .andExpect(view().name("redirect:/logout_success"));

  }


  @Test
  @DisplayName("Test Unit #06: Partner-FSP user get(URL: /login_success) should get rendering of view(name: access)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowLoginSuccessForPartnerFsp() throws Exception {

      mockMvc
        .perform(get("/login_success"))
        .andDo(print())
        .andExpect(model().attribute("login_success", equalTo("Login successful.")))
        .andExpect(view().name("access"));

  }


  @Test
  @DisplayName("Test Unit #07: Partner-FDR user post(URL: /logout) should get redirected to get(URL: /logout_success)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldLogoutAccessSuccessForPartnerFdr() throws Exception {

    mockMvc
      .perform(post("/logout")
                .with(csrf()))
      .andDo(print())
      .andExpect(view().name("redirect:/logout_success"));

  }


  @Test
  @DisplayName("Test Unit #08: Partner-FDR user get(URL: /login_success) should get rendering of view(name: access)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldShowLoginSuccessForPartnerFdr() throws Exception {

      mockMvc
        .perform(get("/login_success"))
        .andDo(print())
        .andExpect(model().attribute("login_success", equalTo("Login successful.")))
        .andExpect(view().name("access"));

  }


  @Test
  @DisplayName("Test Unit #09: Volunteer-FDR user post(URL: /logout) should get redirected to get(URL: /logout_success)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldLogoutAccessSuccessForVolunteerFdr() throws Exception {

    mockMvc
      .perform(post("/logout")
                .with(csrf()))
      .andDo(print())
      .andExpect(view().name("redirect:/logout_success"));

  }


  @Test
  @DisplayName("Test Unit #10: Volunteer-FDR user get(URL: /login_success) should get rendering of view(name: access)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldShowLoginSuccessForVolunteerFdr() throws Exception {

      mockMvc
        .perform(get("/login_success"))
        .andDo(print())
        .andExpect(model().attribute("login_success", equalTo("Login successful.")))
        .andExpect(view().name("access"));

  }


  @Test
  @DisplayName("Test Unit #11: Volunteer-FSP user post(URL: /logout) should get redirected to get(URL: /logout_success)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldLogoutAccessSuccessForVolunteerFsp() throws Exception {

    mockMvc
      .perform(post("/logout")
                .with(csrf()))
      .andDo(print())
      .andExpect(view().name("redirect:/logout_success"));

  }


  @Test
  @DisplayName("Test Unit #12: Volunteer-FSP user get(URL: /login_success) should get rendering of view(name: access)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowLoginSuccessForVolunteerFsp() throws Exception {

      mockMvc
        .perform(get("/login_success"))
        .andDo(print())
        .andExpect(model().attribute("login_success", equalTo("Login successful.")))
        .andExpect(view().name("access"));

  }



}
