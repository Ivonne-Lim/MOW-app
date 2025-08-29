/*
 * Class Name:  ContactsControllerOtherTests
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


@WebMvcTest(ContactsController.class)
@AutoConfigureMockMvc(addFilters = true)

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class ContactsControllerOtherTests {



  @Autowired
  private MockMvc mockMvc;



  @Test
  @DisplayName("Test Unit #01: Administrator user get(URL: /contacts) should get rendering of view(name: contacts)")

  @WithMockUser(username = "management.merrymeal@gmail.com", roles = "ADMINISTRATOR")

	  void shouldShowContactsForAdministrator() throws Exception {

	    mockMvc
	      .perform(get("/contacts"))
	      .andDo(print())
	      .andExpect(view().name("contacts"));

	  }


  @Test
  @DisplayName("Test Unit #02: Member user get(URL: /contacts) should get rendering of view(name: contacts)")

  @WithMockUser(username = "member1@gmail.com", roles = "MEMBER")

	  void shouldShowContactsForMember() throws Exception {

	    mockMvc
	      .perform(get("/contacts"))
	      .andDo(print())
	      .andExpect(view().name("contacts"));

	  }


  @Test
  @DisplayName("Test Unit #03: Partner-FSP user get(URL: /contacts) should get rendering of view(name: contacts)")

  @WithMockUser(username = "partner1@gmail.com", roles = "PARTNER - FSP")

	  void shouldShowContactsForPartnerFsp() throws Exception {

	    mockMvc
	      .perform(get("/contacts"))
	      .andDo(print())
	      .andExpect(view().name("contacts"));

	  }


  @Test
  @DisplayName("Test Unit #04: Partner-FDR user get(URL: /contacts) should get rendering of view(name: contacts)")

  @WithMockUser(username = "partner2@gmail.com", roles = "PARTNER - FDR")

	  void shouldShowContactsForPartnerFdr() throws Exception {

	    mockMvc
	      .perform(get("/contacts"))
	      .andDo(print())
	      .andExpect(view().name("contacts"));

	  }


  @Test
  @DisplayName("Test Unit #05: Vounteer-FDR user get(URL: /contacts) should get rendering of view(name: contacts)")

  @WithMockUser(username = "volunteer1@gmail.com", roles = "VOLUNTEER - FDR")

	  void shouldShowContactsForVolunteerFdr() throws Exception {

	    mockMvc
	      .perform(get("/contacts"))
	      .andDo(print())
	      .andExpect(view().name("contacts"));

	  }


  @Test
  @DisplayName("Test Unit #06: Volunteer-FSP user get(URL: /contacts) should get rendering of view(name: contacts)")

  @WithMockUser(username = "volunteer3@gmail.com", roles = "VOLUNTEER - FSP")

	  void shouldShowContactsForVolunteerFsp() throws Exception {

	    mockMvc
	      .perform(get("/contacts"))
	      .andDo(print())
	      .andExpect(view().name("contacts"));

	  }



}
