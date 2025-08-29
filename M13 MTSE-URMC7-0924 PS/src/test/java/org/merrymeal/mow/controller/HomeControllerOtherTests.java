/*
 * Class Name:  HomeControllerOtherTests
 * Description: WebMvcTest
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.controller;


import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest(HomeController.class)
@AutoConfigureMockMvc(addFilters = true)

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class HomeControllerOtherTests {



  @Autowired
  private MockMvc mockMvc;



  @Test
  @DisplayName("Test Unit #01: Administrator user get(URL: /) should get redirected to get(URL: /home)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowDefaultForAdministrator() throws Exception {

    mockMvc
      .perform(get("/"))
      .andDo(print())
      .andExpect(view().name("redirect:/home"));

  }


  @Test
  @DisplayName("Test Unit #02: Administrator user get(URL: /home) should get rendering of view(name: index)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

  void shouldShowHomeForAdministrator() throws Exception {

    mockMvc
      .perform(get("/home"))
      .andDo(print())
      .andExpect(view().name("redirect:/dashboard"));

  }


  @Test
  @DisplayName("Test Unit #03: Member user get(URL: /) should get redirected to get(URL: /home)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowDefaultForMember() throws Exception {

    mockMvc
      .perform(get("/"))
      .andDo(print())
      .andExpect(view().name("redirect:/home"));

  }


  @Test
  @DisplayName("Test Unit #04: Member user get(URL: /home) should get rendering of view(name: index)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

  void shouldShowHomeForMember() throws Exception {

    mockMvc
      .perform(get("/home"))
      .andDo(print())
      .andExpect(view().name("index"));

  }


  @Test
  @DisplayName("Test Unit #05: Partner-FSP user get(URL: /) should get redirected to get(URL: /home)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowDefaultForPartnerFsp() throws Exception {

    mockMvc
      .perform(get("/"))
      .andDo(print())
      .andExpect(view().name("redirect:/home"));

  }


  @Test
  @DisplayName("Test Unit #06: Partner-FSP user get(URL: /home) should get rendering of view(name: index)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

  void shouldShowHomeForPartnerFsp() throws Exception {

    mockMvc
      .perform(get("/home"))
      .andDo(print())
      .andExpect(view().name("index"));

  }


  @Test
  @DisplayName("Test Unit #07: Partner-FDR user get(URL: /) should get redirected to get(URL: /home)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldShowDefaultForPartnerFdr() throws Exception {

    mockMvc
      .perform(get("/"))
      .andDo(print())
      .andExpect(view().name("redirect:/home"));

  }


  @Test
  @DisplayName("Test Unit #08: Partner-FDR user get(URL: /home) should get rendering of view(name: index)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

  void shouldShowHomeForPartnerFdr() throws Exception {

    mockMvc
      .perform(get("/home"))
      .andDo(print())
      .andExpect(view().name("index"));

  }


  @Test
  @DisplayName("Test Unit #09: Volunteer-FDR user get(URL: /) should get redirected to get(URL: /home)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldShowDefaultForVolunteerFdr() throws Exception {

    mockMvc
      .perform(get("/"))
      .andDo(print())
      .andExpect(view().name("redirect:/home"));

  }


  @Test
  @DisplayName("Test Unit #10: Volunteer-FDR user get(URL: /home) should get rendering of view(name: index)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

  void shouldShowHomeForVolunteerFdr() throws Exception {

    mockMvc
      .perform(get("/home"))
      .andDo(print())
      .andExpect(view().name("index"));

  }


  @Test
  @DisplayName("Test Unit #11: Volunteer-FSP user get(URL: /) should get redirected to get(URL: /home)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowDefaultForVolunteerFsp() throws Exception {

    mockMvc
      .perform(get("/"))
      .andDo(print())
      .andExpect(view().name("redirect:/home"));

  }


  @Test
  @DisplayName("Test Unit #12: Volunteer-FSP user get(URL: /home) should get rendering of view(name: index)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

  void shouldShowHomeForVolunteerFsp() throws Exception {

    mockMvc
      .perform(get("/home"))
      .andDo(print())
      .andExpect(view().name("index"));

  }



}
