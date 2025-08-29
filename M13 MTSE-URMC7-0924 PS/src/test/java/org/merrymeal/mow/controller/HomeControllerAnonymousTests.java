/*
 * Class Name:  HomeControllerAnonymousTests
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

import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest(HomeController.class)
@AutoConfigureMockMvc(addFilters = false)
  // ISSUE: Security filters not working for anonymous

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class HomeControllerAnonymousTests {



  @Autowired
  private MockMvc mockMvc;



  @Test
  @DisplayName("Test Unit #01: Anonymous user get(URL: /) should get redirected to get(URL: /home)")

  @WithAnonymousUser

  void shouldShowDefaultForAnonymous() throws Exception {

    mockMvc
      .perform(get("/"))
      .andDo(print())
      .andExpect(view().name("redirect:/home"));

  }


  @Test
  @DisplayName("Test Unit #02: Anonymous user get(URL: /home) should get rendering of view(name: index)")

  @WithAnonymousUser

  void shouldShowHomeForAnonymous() throws Exception {

    mockMvc
      .perform(get("/home"))
      .andDo(print())
      .andExpect(view().name("index"));

  }



}
