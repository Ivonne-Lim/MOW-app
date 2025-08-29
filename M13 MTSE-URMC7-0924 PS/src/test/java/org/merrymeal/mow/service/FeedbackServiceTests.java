/*
 * Class Name:  FeedbackServiceTests
 * Description: InjectMocks
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.service;


import org.merrymeal.mow.entity.Profile;
import org.merrymeal.mow.entity.Menu;
import org.merrymeal.mow.entity.Meal;
import org.merrymeal.mow.entity.Pickup;
import org.merrymeal.mow.entity.Feedback;

import org.merrymeal.mow.repository.ProfileRepository;
import org.merrymeal.mow.repository.MenuRepository;
import org.merrymeal.mow.repository.MealRepository;
import org.merrymeal.mow.repository.PickupRepository;
import org.merrymeal.mow.repository.FeedbackRepository;

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


@ExtendWith(MockitoExtension.class)

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class FeedbackServiceTests {



  @Mock
  private ProfileRepository profileRepository;

  @Mock
  private MenuRepository menuRepository;

  @Mock
  private MealRepository mealRepository;

  @Mock
  private PickupRepository pickupRepository;

  @Mock
  private FeedbackRepository feedbackRepository;


  @InjectMocks
  private FeedbackService feedbackService;



  final int PAGINATION_SIZE = 2;
    // Default and maximum should be 15 as measured.



  @Test
  @DisplayName("Test Unit #01: findSubmissions(username) for administrator should return submissions")

  void shouldFindSubmissionsForAdministrator() throws Exception {

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
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();
    submissions1.add(feedback1);

    given(profileRepository.findByEmail(administrator1.getEmail())).willReturn(administrator1);
    given(feedbackRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))).willReturn(submissions1);

    List<Feedback> submissions = feedbackService.findSubmissions(administrator1.getEmail());

    assertThat(submissions).isNotNull();
    assertThat(submissions.size()).isEqualTo(submissions1.size());

  }

  @Test
  @DisplayName("Test Unit #01a: findSubmissions(username, page, size) for administrator should return page of submissions")

  void shouldFindSubmissionsPageForAdministrator() throws Exception {

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
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();
    submissions1.add(feedback1);

    Page<Feedback> items = new PageImpl<>(submissions1);

    given(profileRepository.findByEmail(administrator1.getEmail())).willReturn(administrator1);
    given(feedbackRepository.findAll(PageRequest.of(0, PAGINATION_SIZE, Sort.by(Sort.Direction.DESC, "id")))).willReturn(items);

    Page<Feedback> submissions = feedbackService.findSubmissions(administrator1.getEmail(), 0, PAGINATION_SIZE);

    assertThat(submissions).isNotNull();
    assertThat(submissions.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #02: findSubmissions(username) for member should return submissions")

  void shouldFindSubmissionsForMember() throws Exception {

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
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();
    submissions1.add(feedback1);

    given(profileRepository.findByEmail(member2.getEmail())).willReturn(member2);
    given(feedbackRepository.findByMember_Id(member2.getId(), Sort.by(Sort.Direction.DESC, "id"))).willReturn(submissions1);

    List<Feedback> submissions = feedbackService.findSubmissions(member2.getEmail());

    assertThat(submissions).isNotNull();
    assertThat(submissions.size()).isEqualTo(submissions1.size());

  }

  @Test
  @DisplayName("Test Unit #02a: findSubmissions(username, page, size) for member should return page of submissions")

  void shouldFindSubmissionsPageForMember() throws Exception {

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
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();
    submissions1.add(feedback1);

    Page<Feedback> items = new PageImpl<>(submissions1);

    given(profileRepository.findByEmail(member2.getEmail())).willReturn(member2);
    given(feedbackRepository.findByMember_Id(member2.getId(), PageRequest.of(0, PAGINATION_SIZE, Sort.by(Sort.Direction.DESC, "id")))).willReturn(items);

    Page<Feedback> submissions = feedbackService.findSubmissions(member2.getEmail(), 0, PAGINATION_SIZE);

    assertThat(submissions).isNotNull();
    assertThat(submissions.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #03: findSubmissions(username) for provider should return submissions")

  void shouldFindSubmissionsForProvider() throws Exception {

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
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();
    submissions1.add(feedback1);

    given(profileRepository.findByEmail(partner7.getEmail())).willReturn(partner7);
    given(feedbackRepository.findByProvider_Id(partner7.getId())).willReturn(submissions1);

    List<Feedback> submissions = feedbackService.findSubmissions(partner7.getEmail());

    assertThat(submissions).isNotNull();
    assertThat(submissions.size()).isEqualTo(submissions1.size());

  }

  @Test
  @DisplayName("Test Unit #03a: findSubmissions(username, page, size) for provider should return page of submissions")

  void shouldFindSubmissionsPageForProvider() throws Exception {

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
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();
    submissions1.add(feedback1);

    Page<Feedback> items = new PageImpl<>(submissions1);

    given(profileRepository.findByEmail(partner7.getEmail())).willReturn(partner7);
    given(feedbackRepository.findByProvider_Id(partner7.getId(), PageRequest.of(0, PAGINATION_SIZE))).willReturn(items);

    Page<Feedback> submissions = feedbackService.findSubmissions(partner7.getEmail(), 0, PAGINATION_SIZE);

    assertThat(submissions).isNotNull();
    assertThat(submissions.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #04: findSubmissions(username) for rider should return submissions")

  void shouldFindSubmissionsForRider() throws Exception {

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
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();
    submissions1.add(feedback1);

    given(profileRepository.findByEmail(volunteer8.getEmail())).willReturn(volunteer8);
    given(feedbackRepository.findByRider_Id(volunteer8.getId())).willReturn(submissions1);

    List<Feedback> submissions = feedbackService.findSubmissions(volunteer8.getEmail());

    assertThat(submissions).isNotNull();
    assertThat(submissions.size()).isEqualTo(submissions1.size());

  }

  @Test
  @DisplayName("Test Unit #04a: findSubmissions(username, page, size) for rider should return page of submissions")

  void shouldFindSubmissionsPageForRider() throws Exception {

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
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    List<Feedback> submissions1 = new ArrayList<>();
    submissions1.add(feedback1);

    Page<Feedback> items = new PageImpl<>(submissions1);

    given(profileRepository.findByEmail(volunteer8.getEmail())).willReturn(volunteer8);
    given(feedbackRepository.findByRider_Id(volunteer8.getId(), PageRequest.of(0, PAGINATION_SIZE))).willReturn(items);

    Page<Feedback> submissions = feedbackService.findSubmissions(volunteer8.getEmail(), 0, PAGINATION_SIZE);

    assertThat(submissions).isNotNull();
    assertThat(submissions.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #04: deleteSubmissionById(id) should return submission")

  void shouldDeleteSubmissionById() throws Exception {

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
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    Feedback feedback1d = new Feedback();

    feedback1d.setId(1);
    feedback1d.setRate_meal(3);
    feedback1d.setRemarks_meal("Meal Remarks #1");
    feedback1d.setRate_delivery(3);
    feedback1d.setRemarks_delivery("Delivery Remarks #1");
    feedback1d.setStatus("Deleted");
    feedback1d.setMember(member2);
    feedback1d.setPickup(pickup1);

    given(feedbackRepository.findById(feedback1.getId())).willReturn(feedback1);
    given(feedbackRepository.saveAndFlush(feedback1d)).willReturn(feedback1d);

    Feedback submission = feedbackService.deleteSubmissionById(Integer.toString(feedback1.getId()));

    assertThat(submission).isNotNull();
    assertThat(submission.getId()).isEqualTo(feedback1.getId());

  }


  @Test
  @DisplayName("Test Unit #05: findSubmissionById(id)) should return submission")

  void shouldFindSubmissionById() throws Exception {

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
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    given(feedbackRepository.findById(feedback1.getId())).willReturn(feedback1);

    Feedback submission = feedbackService.findSubmissionById(Integer.toString(feedback1.getId()));

    assertThat(submission).isNotNull();
    assertThat(submission.getId()).isEqualTo(feedback1.getId());

  }


  @Test
  @DisplayName("Test Unit #06: saveSubmission(submission, member_id, pickup_id)) should return submission")

  void shouldSaveSubmission() throws Exception {

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
    pickup1.setTime_start(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setTime_end(LocalDateTime.of(2025, 6, 11, 0, 0));
    pickup1.setStatus("Ended");
    pickup1.setRider(volunteer8);
    pickup1.setMeal(meal3);

    Feedback feedback1 = new Feedback();

    feedback1.setId(1);
    feedback1.setRate_meal(3);
    feedback1.setRemarks_meal("Meal Remarks #1");
    feedback1.setRate_delivery(3);
    feedback1.setRemarks_delivery("Delivery Remarks #1");
    feedback1.setStatus("Active");
    feedback1.setMember(member2);
    feedback1.setPickup(pickup1);

    given(profileRepository.findById(member2.getId())).willReturn(member2);
    given(pickupRepository.findById(pickup1.getId())).willReturn(pickup1);
    given(feedbackRepository.saveAndFlush(feedback1)).willReturn(feedback1);

    Feedback submission = feedbackService.saveSubmission(feedback1, Integer.toString(member2.getId()), Integer.toString(pickup1.getId()));

    assertThat(submission).isNotNull();
    assertThat(submission.getId()).isEqualTo(feedback1.getId());

  }



}
