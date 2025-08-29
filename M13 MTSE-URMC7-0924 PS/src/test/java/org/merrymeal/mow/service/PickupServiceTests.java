/*
 * Class Name:  PickupServiceTests
 * Description: InjectMocks
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.service;


import org.merrymeal.mow.model.SelectWrapper;

import org.merrymeal.mow.entity.Profile;
import org.merrymeal.mow.entity.Menu;
import org.merrymeal.mow.entity.Meal;
import org.merrymeal.mow.entity.Pickup;

import org.merrymeal.mow.repository.ProfileRepository;
import org.merrymeal.mow.repository.MenuRepository;
import org.merrymeal.mow.repository.MealRepository;
import org.merrymeal.mow.repository.PickupRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.InjectMocks;

import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@ExtendWith(MockitoExtension.class)

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class PickupServiceTests {



  @Mock
  private ProfileRepository profileRepository;

  @Mock
  private MenuRepository menuRepository;

  @Mock
  private MealRepository mealRepository;

  @Mock
  private PickupRepository pickupRepository;

	  
  @InjectMocks
  private PickupService pickupService;



  final int PAGINATION_SIZE = 2;
    // Default and maximum should be 15 as measured.



  @Test
  @DisplayName("Test Unit #01: findMealsAvailable(username) should return meals")

  void shouldFindMealsAvailable1() throws Exception {

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

    given(profileRepository.findByEmail(volunteer8.getEmail())).willReturn(volunteer8);
    given(mealRepository.findByStatusAndPickupAndProvider_Id(meal3.getStatus(), volunteer8.getProvider().getId())).willReturn(meals1);

    List<Meal> meals = pickupService.findMealsAvailable(volunteer8.getEmail());

    assertThat(meals).isNotNull();
    assertThat(meals.size()).isEqualTo(meals1.size());

  }

  @Test
  @DisplayName("Test Unit #01a: findMealsAvailable(username, page, size) should return page of meals")

  void shouldFindMealsAvailablePage1() throws Exception {

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

    Page<Meal> items = new PageImpl<>(meals1);

    given(profileRepository.findByEmail(volunteer8.getEmail())).willReturn(volunteer8);
    given(mealRepository.findByStatusAndPickupAndProvider_Id(meal3.getStatus(), volunteer8.getProvider().getId(), PageRequest.of(0, PAGINATION_SIZE))).willReturn(items);

    Page<Meal> meals = pickupService.findMealsAvailable(volunteer8.getEmail(), 0, PAGINATION_SIZE);

    assertThat(meals).isNotNull();
    assertThat(meals.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #02: findMealsAvailable() should return meals")

  void shouldFindMealsAvailable2() throws Exception {

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
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    List<Meal> meals1 = new ArrayList<>();
    meals1.add(meal3);

    given(mealRepository.findByStatusAndPickup(meal3.getStatus())).willReturn(meals1);

    List<Meal> meals = pickupService.findMealsAvailable();

    assertThat(meals).isNotNull();
    assertThat(meals.size()).isEqualTo(meals1.size());

  }

  @Test
  @DisplayName("Test Unit #02a: findMealsAvailable(page, size) should return page of meals")

  void shouldFindMealsAvailablePage2() throws Exception {

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
    meal3.setTime_avail(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    meal3.setStatus("Ended");
    meal3.setMenu(menu5);
    meal3.setProvider(partner7);
    meal3.setMember(member2);

    List<Meal> meals1 = new ArrayList<>();
    meals1.add(meal3);

    Page<Meal> items = new PageImpl<>(meals1);

    given(mealRepository.findByStatusAndPickup(meal3.getStatus(), PageRequest.of(0, PAGINATION_SIZE))).willReturn(items);

    Page<Meal> meals = pickupService.findMealsAvailable(0, PAGINATION_SIZE);

    assertThat(meals).isNotNull();
    assertThat(meals.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #03: findPickupsCurrentByRider(username) should return pickups")

  void shouldFindMealsCurrentByRider() throws Exception {

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

    List<String> statuses = new ArrayList<>();
    statuses.add("Accepted");
    statuses.add("Started");

    given(profileRepository.findByEmail(volunteer8.getEmail())).willReturn(volunteer8);
    given(pickupRepository.findByRider_IdAndStatusIn(volunteer8.getId(), statuses)).willReturn(pickups1);

    List<Pickup> pickups = pickupService.findPickupsCurrentByRider(volunteer8.getEmail());

    assertThat(pickups).isNotNull();
    assertThat(pickups.size()).isEqualTo(pickups1.size());

  }

  @Test
  @DisplayName("Test Unit #03a: findPickupsCurrentByRider(username, page, size) should return page of pickups")

  void shouldFindMealsCurrentPageByRider() throws Exception {

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

    Page<Pickup> items = new PageImpl<>(pickups1);

    List<String> statuses = new ArrayList<>();
    statuses.add("Accepted");
    statuses.add("Started");

    given(profileRepository.findByEmail(volunteer8.getEmail())).willReturn(volunteer8);
    given(pickupRepository.findByRider_IdAndStatusIn(volunteer8.getId(), statuses, PageRequest.of(0, PAGINATION_SIZE))).willReturn(items);

    Page<Pickup> pickups = pickupService.findPickupsCurrentByRider(volunteer8.getEmail(), 0, PAGINATION_SIZE);

    assertThat(pickups).isNotNull();
    assertThat(pickups.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #04: findPickupsCurrentByMember(username) should return pickups")

  void shouldFindMealsCurrentByMember() throws Exception {

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

    List<String> statuses = new ArrayList<>();
    statuses.add("Accepted");
    statuses.add("Started");

    given(profileRepository.findByEmail(member2.getEmail())).willReturn(member2);
    given(pickupRepository.findByMember_IdAndStatusIn(member2.getId(), statuses)).willReturn(pickups1);

    List<Pickup> pickups = pickupService.findPickupsCurrentByMember(member2.getEmail());

    assertThat(pickups).isNotNull();
    assertThat(pickups.size()).isEqualTo(pickups1.size());

  }

  @Test
  @DisplayName("Test Unit #04a: findPickupsCurrentByMember(username, page, size) should return page of pickups")

  void shouldFindMealsCurrentPageByMember() throws Exception {

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

    Page<Pickup> items = new PageImpl<>(pickups1);

    List<String> statuses = new ArrayList<>();
    statuses.add("Accepted");
    statuses.add("Started");

    given(profileRepository.findByEmail(member2.getEmail())).willReturn(member2);
    given(pickupRepository.findByMember_IdAndStatusIn(member2.getId(), statuses, PageRequest.of(0, PAGINATION_SIZE))).willReturn(items);

    Page<Pickup> pickups = pickupService.findPickupsCurrentByMember(member2.getEmail(), 0, PAGINATION_SIZE);

    assertThat(pickups).isNotNull();
    assertThat(pickups.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #05: generatePickups(selectWrapper, username) should return pickups")

  void shouldGeneratePickups() throws Exception {

	LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

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

    Pickup pickup0 = new Pickup();

    pickup0.setId(0);
    pickup0.setTime_accept(localDateTime);
    pickup0.setStatus("Accepted");
    pickup0.setRider(volunteer8);
    pickup0.setMeal(meal3);

    Pickup pickup1 = new Pickup();

    pickup1.setId(1);
    pickup1.setTime_accept(localDateTime);
    pickup1.setStatus("Accepted");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    List<Pickup> pickups1 = new ArrayList<>();
    pickups1.add(pickup1);

    List<String> ids = new ArrayList<>();
    ids.add(Integer.toString(meal3.getId()));

    SelectWrapper selectWrapper = new SelectWrapper();
    selectWrapper.setIds(ids);

    given(profileRepository.findByEmail(volunteer8.getEmail())).willReturn(volunteer8);
    given(mealRepository.findById(meal3.getId())).willReturn(meal3);
    given(pickupRepository.saveAndFlush(pickup0)).willReturn(pickup1);

    List<Pickup> pickups = pickupService.generatePickups(selectWrapper, volunteer8.getEmail());
    
    assertThat(pickups).isNotNull();
    assertThat(pickups.size()).isEqualTo(pickups1.size());

  }


  @Test
  @DisplayName("Test Unit #06: findPickupById(id) should return pickup")

  void shouldFindPickupById() throws Exception {

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

    given(pickupRepository.findById(pickup1.getId())).willReturn(pickup1);

    Pickup pickup = pickupService.findPickupById(Integer.toString(pickup1.getId()));

    assertThat(pickup).isNotNull();
    assertThat(pickup.getId()).isEqualTo(pickup1.getId());

  }


  @Test
  @DisplayName("Test Unit #07: savePickup(pickup, rider_id, meal_id) should return pickup")

  void shouldSavePickup() throws Exception {

	LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

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
    pickup1.setTime_start(localDateTime);
    pickup1.setStatus("Started");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    given(profileRepository.findById(volunteer8.getId())).willReturn(volunteer8);
    given(mealRepository.findById(meal3.getId())).willReturn(meal3);
    given(pickupRepository.saveAndFlush(pickup1)).willReturn(pickup1);

    Pickup pickup = pickupService.savePickup(pickup1, Integer.toString(volunteer8.getId()),Integer.toString(meal3.getId()));

    assertThat(pickup).isNotNull();
    assertThat(pickup.getId()).isEqualTo(pickup1.getId());

  }


  @Test
  @DisplayName("Test Unit #08: findDeliveries(username) should return pickups")

  void shouldFindDeliveries() throws Exception {

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

    List<String> statuses = new ArrayList<>();
    statuses.add("Ended");

    given(profileRepository.findByEmail(member2.getEmail())).willReturn(member2);
    given(pickupRepository.findByMember_IdAndStatusIn(member2.getId(), statuses)).willReturn(pickups1);

    List<Pickup> deliveries = pickupService.findDeliveries(member2.getEmail());

    assertThat(deliveries).isNotNull();
    assertThat(deliveries.size()).isEqualTo(pickups1.size());

  }



}
