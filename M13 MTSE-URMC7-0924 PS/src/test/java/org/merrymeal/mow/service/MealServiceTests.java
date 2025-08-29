/*
 * Class Name:  MealServiceTests
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

import org.merrymeal.mow.repository.ProfileRepository;
import org.merrymeal.mow.repository.MenuRepository;
import org.merrymeal.mow.repository.MealRepository;

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


public class MealServiceTests {



  @Mock
  private ProfileRepository profileRepository;

  @Mock
  private MenuRepository menuRepository;

  @Mock
  private MealRepository mealRepository;

  
  @InjectMocks
  private MealService mealService;



  final int PAGINATION_SIZE = 2;
    // Default and maximum should be 15 as measured.



  @Test
  @DisplayName("Test Unit #01: findMeals(diet, username) for administrator should return meals")

  void shouldFindMealsForAdministrator() throws Exception {

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

    given(profileRepository.findByEmail(administrator1.getEmail())).willReturn(administrator1);
    given(mealRepository.findByDiet(menu5.getDiet())).willReturn(meals1);

    List<Meal> meals = mealService.findMeals(menu5.getDiet(), administrator1.getEmail());

    assertThat(meals).isNotNull();
    assertThat(meals.size()).isEqualTo(meals1.size());

  }

  @Test
  @DisplayName("Test Unit #01a: findMeals(diet, username, page, size) for administrator should return page of meals")

  void shouldFindMealsPageForAdministrator() throws Exception {

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

    Page<Meal> items = new PageImpl<>(meals1);

    given(profileRepository.findByEmail(administrator1.getEmail())).willReturn(administrator1);
    given(mealRepository.findByDiet(menu5.getDiet(), PageRequest.of(0, PAGINATION_SIZE))).willReturn(items);

    Page<Meal> meals = mealService.findMeals(menu5.getDiet(), administrator1.getEmail(), 0, PAGINATION_SIZE);

    assertThat(meals).isNotNull();
    assertThat(meals.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #02: findMeals(diet, username) for member should return meals")

  void shouldFindMealsForMember() throws Exception {

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

    given(profileRepository.findByEmail(member2.getEmail())).willReturn(member2);
    given(mealRepository.findByDietAndMember_Id(menu5.getDiet(), member2.getId())).willReturn(meals1);

    List<Meal> meals = mealService.findMeals(menu5.getDiet(), member2.getEmail());

    assertThat(meals).isNotNull();
    assertThat(meals.size()).isEqualTo(meals1.size());

  }

  @Test
  @DisplayName("Test Unit #02a: findMeals(diet, username, page, size) for member should return page of meals")

  void shouldFindMealsPageForMember() throws Exception {

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

    Page<Meal> items = new PageImpl<>(meals1);

    given(profileRepository.findByEmail(member2.getEmail())).willReturn(member2);
    given(mealRepository.findByDietAndMember_Id(menu5.getDiet(), member2.getId(), PageRequest.of(0, PAGINATION_SIZE))).willReturn(items);

    Page<Meal> meals = mealService.findMeals(menu5.getDiet(), member2.getEmail(), 0, PAGINATION_SIZE);

    assertThat(meals).isNotNull();
    assertThat(meals.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #03: findMeals(diet, username) for provider should return meals")

  void shouldFindMealsForProvider() throws Exception {

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

    given(profileRepository.findByEmail(partner7.getEmail())).willReturn(partner7);
    given(mealRepository.findByDietAndProvider_Id(menu5.getDiet(), partner7.getId())).willReturn(meals1);

    List<Meal> meals = mealService.findMeals(menu5.getDiet(), partner7.getEmail());

    assertThat(meals).isNotNull();
    assertThat(meals.size()).isEqualTo(meals1.size());

  }

  @Test
  @DisplayName("Test Unit #03a: findMeals(diet, username, page, size) for provider should return page of meals")

  void shouldFindMealsPageForProvider() throws Exception {

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

    Page<Meal> items = new PageImpl<>(meals1);

    given(profileRepository.findByEmail(partner7.getEmail())).willReturn(partner7);
    given(mealRepository.findByDietAndProvider_Id(menu5.getDiet(), partner7.getId(), PageRequest.of(0, PAGINATION_SIZE))).willReturn(items);

    Page<Meal> meals = mealService.findMeals(menu5.getDiet(), partner7.getEmail(), 0, PAGINATION_SIZE);

    assertThat(meals).isNotNull();
    assertThat(meals.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #04: generateMeals(diet, username) should return meals")

  void shouldGenerateMeals() throws Exception {

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

    List<Profile> members1 = new ArrayList<>();
    members1.add(member5);

    List<String> roles = new ArrayList<>();
    roles.add("Member");

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

    List<Profile> otherProviders = new ArrayList<>();
    otherProviders.add(partner6);
    otherProviders.add(partner7);

    List<String> otherRoles = new ArrayList<>();
    otherRoles.add("Partner - FSP");
    otherRoles.add("Volunteer - FSP");

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Chicken Chop");
    menu1.setDiet("Normal");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");
    menu1.setProvider(partner6);

    Meal meal0 = new Meal();

    meal0.setId(0);
    meal0.setStatus("Generated");
    meal0.setMenu(menu1);
    meal0.setProvider(partner6);
    meal0.setMember(member5);

    Meal meal1 = new Meal();

    meal1.setId(1);
    meal1.setStatus("Generated");
    meal1.setMenu(menu1);
    meal1.setProvider(partner6);
    meal1.setMember(member5);

    List<Meal> meals0 = new ArrayList<>();
    List<Meal> meals1 = new ArrayList<>();
    meals1.add(meal1);

    partner6.setMenu_normal(menu1);

    given(profileRepository.findByEmail(partner6.getEmail())).willReturn(partner6);
    given(mealRepository.findByDietAndProvider_Id(menu1.getDiet(), partner6.getId())).willReturn(meals0);
      // Note: Non-ended and non-deleted meals do not exist for partner6.
    given(profileRepository.findByDietAndStatusAndRoleIn(member5.getDiet(), member5.getStatus(), roles, Sort.by(Sort.Direction.ASC, "dob"))).willReturn(members1);
    given(profileRepository.findByStatusAndRoleIn(partner7.getStatus(), otherRoles, Sort.by(Sort.Direction.ASC, "names"))).willReturn(otherProviders);
      // Note: member5 and partner7 are > 10 km apart so meal will not be provided by partner7 regardless today is weekday or weekend.
    given(mealRepository.findByDietAndMember_Id(member5.getDiet(), member5.getId())).willReturn(meals0);
      // Note: Non-ended and non-deleted meals do not exist for member5.
    given(mealRepository.saveAndFlush(meal0)).willReturn(meal1);

    List<Meal> meals = mealService.generateMeals(menu1.getDiet(), partner6.getEmail());

    assertThat(meals).isNotNull();
    assertThat(meals.size()).isEqualTo(meals1.size());

  }


  @Test
  @DisplayName("Test Unit #05: updateMeals(selectWrapper, status) should return meals")

  void shouldUpdateMeals() throws Exception {

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

    Meal meal3u = new Meal();

    meal3u.setId(3);
    meal3u.setTime_avail(localDateTime);
    meal3u.setStatus("Available");
    meal3u.setMenu(menu5);
    meal3u.setProvider(partner7);
    meal3u.setMember(member2);

    List<Meal> meals1 = new ArrayList<>();
    meals1.add(meal3u);

    List<String> ids = new ArrayList<>();
    ids.add(Integer.toString(meal3.getId()));

    SelectWrapper selectWrapper = new SelectWrapper();
    selectWrapper.setIds(ids);

    given(mealRepository.findById(meal3.getId())).willReturn(meal3);
    given(mealRepository.saveAndFlush(meal3u)).willReturn(meal3u);

    List<Meal> meals = mealService.updateMeals(selectWrapper, meal3u.getStatus());

    assertThat(meals).isNotNull();
    assertThat(meals.size()).isEqualTo(meals1.size());

  }


 /********************************************************************************
  Note:
  getLatitudeLongitude(address) and getDistance(lat1, long1, lat2, long2)
    private methods are tested via generateMeals(diet, username) public method.
  ********************************************************************************/



}
