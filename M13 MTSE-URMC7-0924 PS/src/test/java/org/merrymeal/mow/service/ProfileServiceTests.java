/*
 * Class Name:  ProfileServiceTests
 * Description: InjectMocks
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.service;


import org.merrymeal.mow.entity.Profile;
import org.merrymeal.mow.entity.Menu;

import org.merrymeal.mow.repository.ProfileRepository;
import org.merrymeal.mow.repository.MenuRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
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

public class ProfileServiceTests {



  @Mock
  private ProfileRepository profileRepository;

  @Mock
  private MenuRepository menuRepository;

  @Mock
  private PasswordEncoder passwordEncoder;


  @InjectMocks
  private ProfileService profileService;



  final int PAGINATION_SIZE = 2;
    // Default and maximum should be 15 as measured.



  @Test
  @DisplayName("Test Unit #01: saveProfile(member, '', '', '', '', '', null) should return member")

  public void shouldSaveProfileForMember() throws Exception {

	LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

    Profile member0 = new Profile();
	    
    member0.setId(0);
    member0.setNric_uen("S1000001A");
    member0.setNames("Member #1");
    member0.setRole("Member");
    member0.setEmail("member1@gmail.com");
    member0.setPhone("10000001");
    member0.setAddress("55 Pipit Road");
    member0.setUnit("#11-08");
    member0.setPostal("370055");
    member0.setStatus("Pending");
    member0.setPassword("password");
    member0.setGender("Female");
    member0.setSurname("Test #1");
    member0.setDob(LocalDate.of(1971, 1, 1));
    member0.setDisabilities("Disability #1");
    member0.setIncome(1000);
    member0.setHousehold(1);
    member0.setDiet("Halal");
    member0.setAllergies("Allergy #1");

    Profile member0u = new Profile();
    
    member0u.setId(0);
    member0u.setNric_uen("S1000001A");
    member0u.setNames("Member #1");
    member0u.setRole("Member");
    member0u.setEmail("member1@gmail.com");
    member0u.setPhone("10000001");
    member0u.setAddress("55 Pipit Road");
    member0u.setUnit("#11-08");
    member0u.setPostal("370055");
    member0u.setStatus("Pending");
    member0u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member0u.setGender("Female");
    member0u.setSurname("Test #1");
    member0u.setDob(LocalDate.of(1971, 1, 1));
    member0u.setDisabilities("Disability #1");
    member0u.setIncome(1000);
    member0u.setHousehold(1);
    member0u.setDiet("Halal");
    member0u.setAllergies("Allergy #1");
    member0u.setCreated(localDateTime);

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
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(localDateTime);

    given(passwordEncoder.encode(member0.getPassword())).willReturn(member0u.getPassword());
    given(profileRepository.saveAndFlush(member0u)).willReturn(member2);

    Profile member = profileService.saveProfile(member0, "", "", "", "", "", null);

    assertThat(member).isNotNull();
    assertThat(member.getId()).isEqualTo(member2.getId());
    assertThat(member.getCreated()).isNotNull();
      // Note: profileService.saveProfile can return member profile with slightly different value of created property.

  }


  @Test
  @DisplayName("Test Unit #02: saveProfile(caregiver, '', '', '', '', '', member_id) should return member")

  public void shouldSaveProfileForCaregiver() throws Exception {

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
    member2.setStatus("Pending");
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile member2u = new Profile();

    member2u.setId(2);
    member2u.setNric_uen("S1000001A");
    member2u.setNames("Member #1");
    member2u.setRole("Member");
    member2u.setEmail("member1@gmail.com");
    member2u.setPhone("10000001");
    member2u.setAddress("55 Pipit Road");
    member2u.setUnit("#11-08");
    member2u.setPostal("370055");
    member2u.setStatus("Pending");
    member2u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2u.setGender("Female");
    member2u.setSurname("Test #1");
    member2u.setDob(LocalDate.of(1971, 1, 1));
    member2u.setDisabilities("Disability #1");
    member2u.setIncome(1000);
    member2u.setHousehold(1);
    member2u.setDiet("Halal");
    member2u.setAllergies("Allergy #1");
    member2u.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2u.setUpdated(localDateTime);

    Profile caregiver0 = new Profile();

    caregiver0.setId(0);
    caregiver0.setNric_uen("S2000001B");
    caregiver0.setNames("Caregiver #1");
    caregiver0.setRole("Caregiver");
    caregiver0.setEmail("caregiver1@gmail.com");
    caregiver0.setPhone("20000001");
    caregiver0.setAddress("Caregiver Address #1");
    caregiver0.setUnit("");
    caregiver0.setPostal("200001");
    caregiver0.setStatus("Pending");
    caregiver0.setGender("Male");
    caregiver0.setSurname("Test #1");

    Profile caregiver0u = new Profile();

    caregiver0u.setId(0);
    caregiver0u.setNric_uen("S2000001B");
    caregiver0u.setNames("Caregiver #1");
    caregiver0u.setRole("Caregiver");
    caregiver0u.setEmail("caregiver1@gmail.com");
    caregiver0u.setPhone("20000001");
    caregiver0u.setAddress("Caregiver Address #1");
    caregiver0u.setUnit("");
    caregiver0u.setPostal("200001");
    caregiver0u.setStatus("Pending");
    caregiver0u.setGender("Male");
    caregiver0u.setSurname("Test #1");
    caregiver0u.setCreated(localDateTime);

    Profile caregiver3 = new Profile();

    caregiver3.setId(3);
    caregiver3.setNric_uen("S2000001B");
    caregiver3.setNames("Caregiver #1");
    caregiver3.setRole("Caregiver");
    caregiver3.setEmail("caregiver1@gmail.com");
    caregiver3.setPhone("20000001");
    caregiver3.setAddress("Caregiver Address #1");
    caregiver3.setUnit("");
    caregiver3.setPostal("200001");
    caregiver3.setStatus("Pending");
    caregiver3.setGender("Male");
    caregiver3.setSurname("Test #1");
    caregiver3.setCreated(localDateTime);

    List<Profile> members0 = new ArrayList<>();
    List<Profile> members1 = new ArrayList<>();
    members1.add(member2);

    List<Profile> caregivers0 = new ArrayList<>();
    List<Profile> caregivers1 = new ArrayList<>();
    caregivers1.add(caregiver3);

    member2.setCaregivers(caregivers0);
    member2u.setCaregivers(caregivers1);

    caregiver0.setMembers(members0);
    caregiver0u.setMembers(members1);
    caregiver3.setMembers(members1);

    given(profileRepository.findById(member2.getId())).willReturn(member2);
    given(profileRepository.saveAndFlush(caregiver0u)).willReturn(caregiver3);
    given(profileRepository.saveAndFlush(member2)).willReturn(member2u);
      // Note: Workaround with member2 parameter instead of member2u parameter
      //       which bug in Lombok will cause stack overflow.

    Profile caregiver = profileService.saveProfile(caregiver0, "", "", "", "", "", Integer.toString(member2.getId()));

    assertThat(caregiver).isNotNull();
    assertThat(caregiver.getId()).isEqualTo(caregiver3.getId());
    assertThat(caregiver.getCreated()).isNotNull();
      // Note: profileService.saveProfile can return caregiver profile with slightly different value of created property.
    assertThat(caregiver.getMembers().size()).isEqualTo(caregiver3.getMembers().size());

  }


  @Test
  @DisplayName("Test Unit #03: saveProfile(provider, menu_halal_id, menu_veg_id, menu_soft_id, menu_normal_id, '', null) should return provider")

  public void shouldSaveProfileForProvider() throws Exception {

	LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

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

    Profile partner7u = new Profile();

    partner7u.setId(7);
    partner7u.setNric_uen("300000001C");
    partner7u.setNames("Partner #1");
    partner7u.setRole("Partner - FSP");
    partner7u.setEmail("partner1@gmail.com");
    partner7u.setPhone("30000001");
    partner7u.setAddress("408 Pasir Ris Drive 6");
    partner7u.setUnit("#08-423");
    partner7u.setPostal("510408");
    partner7u.setStatus("Active");
    partner7u.setNotes("Evaluated on 11/06/2025 00:00 am");
    partner7u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner7u.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    partner7u.setUpdated(localDateTime);

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Name");
    menu1.setDiet("Diet");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");
    menu1.setProvider(partner7);

    partner7u.setMenu_normal(menu1);
    partner7u.setMenu_halal(menu1);
    partner7u.setMenu_veg(menu1);
    partner7u.setMenu_soft(menu1);

    given(menuRepository.findById(menu1.getId())).willReturn(menu1);
    given(menuRepository.findById(menu1.getId())).willReturn(menu1);
    given(menuRepository.findById(menu1.getId())).willReturn(menu1);
    given(menuRepository.findById(menu1.getId())).willReturn(menu1);
      // Note: Only menu1 used since Mockito does not allow multiple menu mocks.
    given(profileRepository.findById(partner7.getId())).willReturn(partner7);
    given(profileRepository.saveAndFlush(partner7)).willReturn(partner7u);
      // Note: Workaround with partner7 parameter instead of partner7u parameter
      //       which bug in Lombok will cause stack overflow.


    Profile provider = profileService.saveProfile(partner7, Integer.toString(menu1.getId()), Integer.toString(menu1.getId()),
    		                                                Integer.toString(menu1.getId()), Integer.toString(menu1.getId()), "", null);

    assertThat(provider).isNotNull();
    assertThat(provider.getId()).isEqualTo(partner7u.getId());
    assertThat(provider.getUpdated()).isNotNull();
      // Note: profileService.saveProfile can return provider profile with slightly different value of updated property.

    assertThat(provider.getMenu_normal()).isNotNull();
    assertThat(provider.getMenu_normal().getId()).isEqualTo(menu1.getId());

    assertThat(provider.getMenu_halal()).isNotNull();
    assertThat(provider.getMenu_halal().getId()).isEqualTo(menu1.getId());

    assertThat(provider.getMenu_veg()).isNotNull();
    assertThat(provider.getMenu_veg().getId()).isEqualTo(menu1.getId());

    assertThat(provider.getMenu_soft()).isNotNull();
    assertThat(provider.getMenu_soft().getId()).isEqualTo(menu1.getId());
    
  }


  @Test
  @DisplayName("Test Unit #04: saveProfile(rider, '', '', '', '', provider_id, null) should return rider")

  public void shouldSaveProfileForRider() throws Exception {

	LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

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
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile volunteer8u = new Profile();

    volunteer8u.setId(8);
    volunteer8u.setNric_uen("400000001D");
    volunteer8u.setNames("Volunteer #1");
    volunteer8u.setRole("Volunteer - FDR");
    volunteer8u.setEmail("volunteer1@gmail.com");
    volunteer8u.setPhone("40000001");
    volunteer8u.setAddress("Volunteer Address #1");
    volunteer8u.setUnit("");
    volunteer8u.setPostal("400001");
    volunteer8u.setStatus("Active");
    volunteer8u.setNotes("Evaluated on 11/06/2025 00:00 am");
    volunteer8u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer8u.setGender("Male");
    volunteer8u.setSurname("Test #1");
    volunteer8u.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8u.setUpdated(localDateTime);

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

    volunteer8u.setProvider(partner7);

    given(profileRepository.findById(partner7.getId())).willReturn(partner7);
    given(profileRepository.findById(volunteer8.getId())).willReturn(volunteer8);
    given(profileRepository.saveAndFlush(volunteer8u)).willReturn(volunteer8u);

    Profile rider = profileService.saveProfile(volunteer8, "", "", "", "", Integer.toString(partner7.getId()), null);

    assertThat(rider).isNotNull();
    assertThat(rider.getId()).isEqualTo(volunteer8u.getId());
    assertThat(rider.getUpdated()).isNotNull();
      // Note: profileService.saveProfile can return rider profile with slightly different value of updated property.

    assertThat(rider.getProvider()).isNotNull();
    assertThat(rider.getProvider().getId()).isEqualTo(partner7.getId());

  }


  @Test
  @DisplayName("Test Unit #05: deleteCaregiverById(id, member_id) should return caregiver")

  void shouldDeleteCaregiverById() throws Exception {

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
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile member2u = new Profile();

    member2u.setId(2);
    member2u.setNric_uen("S1000001A");
    member2u.setNames("Member #1");
    member2u.setRole("Member");
    member2u.setEmail("member1@gmail.com");
    member2u.setPhone("10000001");
    member2u.setAddress("55 Pipit Road");
    member2u.setUnit("#11-08");
    member2u.setPostal("370055");
    member2u.setStatus("Pending");
    member2u.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2u.setGender("Female");
    member2u.setSurname("Test #1");
    member2u.setDob(LocalDate.of(1971, 1, 1));
    member2u.setDisabilities("Disability #1");
    member2u.setIncome(1000);
    member2u.setHousehold(1);
    member2u.setDiet("Halal");
    member2u.setAllergies("Allergy #1");
    member2u.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2u.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile caregiver3 = new Profile();

    caregiver3.setId(3);
    caregiver3.setNric_uen("S2000001B");
    caregiver3.setNames("Caregiver #1");
    caregiver3.setRole("Caregiver");
    caregiver3.setEmail("caregiver1@gmail.com");
    caregiver3.setPhone("20000001");
    caregiver3.setAddress("Caregiver Address #1");
    caregiver3.setUnit("");
    caregiver3.setPostal("200001");
    caregiver3.setStatus("Pending");
    caregiver3.setGender("Male");
    caregiver3.setSurname("Test #1");
    caregiver3.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    caregiver3.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile caregiver3d = new Profile();

    caregiver3d.setId(3);
    caregiver3d.setNric_uen("S2000001B");
    caregiver3d.setNames("Caregiver #1");
    caregiver3d.setRole("Caregiver");
    caregiver3d.setEmail("caregiver1@gmail.com");
    caregiver3d.setPhone("20000001");
    caregiver3d.setAddress("Caregiver Address #1");
    caregiver3d.setUnit("");
    caregiver3d.setPostal("200001");
    caregiver3d.setStatus("Pending");
    caregiver3d.setGender("Male");
    caregiver3d.setSurname("Test #1");
    caregiver3d.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    caregiver3d.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> members0 = new ArrayList<>();
    List<Profile> members1 = new ArrayList<>();
    members1.add(member2);

    List<Profile> caregivers0 = new ArrayList<>();
    List<Profile> caregivers1 = new ArrayList<>();
    caregivers1.add(caregiver3);

    member2.setCaregivers(caregivers1);
    member2u.setCaregivers(caregivers0);

    caregiver3.setMembers(members1);
    caregiver3d.setMembers(members0);

    given(profileRepository.findById(caregiver3.getId())).willReturn(caregiver3);
    given(profileRepository.findById(member2.getId())).willReturn(member2);
    given(profileRepository.saveAndFlush(caregiver3d)).willReturn(caregiver3d);
    given(profileRepository.saveAndFlush(member2u)).willReturn(member2u);

    Profile caregiver = profileService.deleteCaregiverById(Integer.toString(caregiver3.getId()), Integer.toString(member2.getId()));

    assertThat(caregiver).isNotNull();
    assertThat(caregiver.getId()).isEqualTo(caregiver3d.getId());

  }


  @Test
  @DisplayName("Test Unit #06: findCaregiverById(id) should return caregiver")

  void shouldFindCaregiverById() throws Exception {

    Profile caregiver3 = new Profile();

    caregiver3.setId(3);
    caregiver3.setNric_uen("S2000001B");
    caregiver3.setNames("Caregiver #1");
    caregiver3.setRole("Caregiver");
    caregiver3.setEmail("caregiver1@gmail.com");
    caregiver3.setPhone("20000001");
    caregiver3.setAddress("Caregiver Address #1");
    caregiver3.setUnit("");
    caregiver3.setPostal("200001");
    caregiver3.setStatus("Pending");
    caregiver3.setGender("Male");
    caregiver3.setSurname("Test #1");
    caregiver3.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    caregiver3.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    given(profileRepository.findById(caregiver3.getId())).willReturn(caregiver3);

    Profile caregiver = profileService.findCaregiverById(Integer.toString(caregiver3.getId()));

    assertThat(caregiver).isNotNull();
    assertThat(caregiver.getId()).isEqualTo(caregiver3.getId());

  }


  @Test
  @DisplayName("Test Unit #07: findCaregivers(member_id) should return caregivers")

  void shouldFindCaregivers() throws Exception {

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
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile caregiver3 = new Profile();

    caregiver3.setId(3);
    caregiver3.setNric_uen("S2000001B");
    caregiver3.setNames("Caregiver #1");
    caregiver3.setRole("Caregiver");
    caregiver3.setEmail("caregiver1@gmail.com");
    caregiver3.setPhone("20000001");
    caregiver3.setAddress("Caregiver Address #1");
    caregiver3.setUnit("");
    caregiver3.setPostal("200001");
    caregiver3.setStatus("Pending");
    caregiver3.setGender("Male");
    caregiver3.setSurname("Test #1");
    caregiver3.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    caregiver3.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> members1 = new ArrayList<>();
    members1.add(member2);

    List<Profile> caregivers1 = new ArrayList<>();
    caregivers1.add(caregiver3);

    member2.setCaregivers(caregivers1);

    caregiver3.setMembers(members1);

    given(profileRepository.findByMember_Id(member2.getId())).willReturn(caregivers1);

    List<Profile> caregivers = profileService.findCaregivers(Integer.toString(member2.getId()));

    assertThat(caregivers).isNotNull();
    assertThat(caregivers.size()).isEqualTo(caregivers1.size());

  }

  @Test
  @DisplayName("Test Unit #07a: findCaregivers(member_id, page, size) should return page of caregivers")

  void shouldFindCaregiversPage() throws Exception {

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
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile caregiver3 = new Profile();

    caregiver3.setId(3);
    caregiver3.setNric_uen("S2000001B");
    caregiver3.setNames("Caregiver #1");
    caregiver3.setRole("Caregiver");
    caregiver3.setEmail("caregiver1@gmail.com");
    caregiver3.setPhone("20000001");
    caregiver3.setAddress("Caregiver Address #1");
    caregiver3.setUnit("");
    caregiver3.setPostal("200001");
    caregiver3.setStatus("Pending");
    caregiver3.setGender("Male");
    caregiver3.setSurname("Test #1");
    caregiver3.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    caregiver3.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> members1 = new ArrayList<>();
    members1.add(member2);

    List<Profile> caregivers1 = new ArrayList<>();
    caregivers1.add(caregiver3);

    Page<Profile> items = new PageImpl<>(caregivers1);

    member2.setCaregivers(caregivers1);

    caregiver3.setMembers(members1);

    given(profileRepository.findByMember_Id(member2.getId(), PageRequest.of(0, PAGINATION_SIZE))).willReturn(items);

    Page<Profile> caregivers = profileService.findCaregivers(Integer.toString(member2.getId()), 0, PAGINATION_SIZE);

    assertThat(caregivers).isNotNull();
    assertThat(caregivers.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #08: findProfileByEmail(username) should return profile")

  void shouldFindProfileByEmail() throws Exception {

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
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    given(profileRepository.findByEmail(member2.getEmail())).willReturn(member2);

    Profile profile = profileService.findProfileByEmail(member2.getEmail());

    assertThat(profile).isNotNull();
    assertThat(profile.getId()).isEqualTo(member2.getId());

  }


  @Test
  @DisplayName("Test Unit #09: findProviders() should return providers")

  void shouldFindProviders() throws Exception {

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

    List<Profile> providers1 = new ArrayList<>();
    providers1.add(partner7);

    List<String> roles = new ArrayList<>();
    roles.add("Partner - FSP");
    roles.add("Volunteer - FSP");

    given(profileRepository.findByStatusAndRoleIn("Active", roles, Sort.by(Sort.Direction.ASC, "names"))).willReturn(providers1);

    List<Profile> providers = profileService.findProviders();

    assertThat(providers).isNotNull();
    assertThat(providers.size()).isEqualTo(providers1.size());

  }


  @Test
  @DisplayName("Test Unit #10: findCaregiverByNric(nric) should return caregiver")

  void shouldFindCaregiverByNric() throws Exception {

    Profile caregiver3 = new Profile();

    caregiver3.setId(3);
    caregiver3.setNric_uen("S2000001B");
    caregiver3.setNames("Caregiver #1");
    caregiver3.setRole("Caregiver");
    caregiver3.setEmail("caregiver1@gmail.com");
    caregiver3.setPhone("20000001");
    caregiver3.setAddress("Caregiver Address #1");
    caregiver3.setUnit("");
    caregiver3.setPostal("200001");
    caregiver3.setStatus("Pending");
    caregiver3.setGender("Male");
    caregiver3.setSurname("Test #1");
    caregiver3.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    caregiver3.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    given(profileRepository.findByNric_Uen(caregiver3.getNric_uen().toUpperCase())).willReturn(caregiver3);

    Profile caregiver = profileService.findCaregiverByNric(caregiver3.getNric_uen());

    assertThat(caregiver).isNotNull();
    assertThat(caregiver.getId()).isEqualTo(caregiver3.getId());

  }


  @Test
  @DisplayName("Test Unit #11: findMembers() should return members")

  void shouldFindMembers() throws Exception {

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
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> members1 = new ArrayList<>();
    members1.add(member2);

    List<String> roles = new ArrayList<>();
    roles.add("Member");

    given(profileRepository.findByRoleIn(roles, Sort.by(Sort.Direction.ASC, "names"))).willReturn(members1);

    List<Profile> members = profileService.findMembers();

    assertThat(members).isNotNull();
    assertThat(members.size()).isEqualTo(members1.size());

  }

  @Test
  @DisplayName("Test Unit #11a: findMembers(page, size) should return page of members")

  void shouldFindMembersPage() throws Exception {

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
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> members1 = new ArrayList<>();
    members1.add(member2);

    Page<Profile> items = new PageImpl<>(members1);

    List<String> roles = new ArrayList<>();
    roles.add("Member");

    given(profileRepository.findByRoleIn(roles, PageRequest.of(0, PAGINATION_SIZE, Sort.by(Sort.Direction.ASC, "names")))).willReturn(items);

    Page<Profile> members = profileService.findMembers(0, PAGINATION_SIZE);

    assertThat(members).isNotNull();
    assertThat(members.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #12: findPartners() should return partners")

  void shouldfindPartners() throws Exception {

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
    partner7.setStatus("Pending");
    partner7.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> partners1 = new ArrayList<>();
    partners1.add(partner7);

    List<String> roles = new ArrayList<>();
    roles.add("Partner - FSP");
    roles.add("Partner - FDR");

    given(profileRepository.findByRoleIn(roles, Sort.by(Sort.Direction.ASC, "names"))).willReturn(partners1);

    List<Profile> partners = profileService.findPartners();

    assertThat(partners).isNotNull();
    assertThat(partners.size()).isEqualTo(partners1.size());

  }

  @Test
  @DisplayName("Test Unit #12a: findPartners(page, size) should return page of partners")

  void shouldfindPartnersPage() throws Exception {

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
    partner7.setStatus("Pending");
    partner7.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    partner7.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> partners1 = new ArrayList<>();
    partners1.add(partner7);

    Page<Profile> items = new PageImpl<>(partners1);

    List<String> roles = new ArrayList<>();
    roles.add("Partner - FSP");
    roles.add("Partner - FDR");

    given(profileRepository.findByRoleIn(roles, PageRequest.of(0, PAGINATION_SIZE, Sort.by(Sort.Direction.ASC, "names")))).willReturn(items);

    Page<Profile> partners = profileService.findPartners(0, PAGINATION_SIZE);

    assertThat(partners).isNotNull();
    assertThat(partners.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #13: findVolunteers() should return volunteers")

  void shouldFindVolunteers() throws Exception {

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
    volunteer8.setStatus("Pending");
    volunteer8.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer8.setGender("Male");
    volunteer8.setSurname("Test #1");
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> volunteers1 = new ArrayList<>();
    volunteers1.add(volunteer8);

    List<String> roles = new ArrayList<>();
    roles.add("Volunteer - FSP");
    roles.add("Volunteer - FDR");

    given(profileRepository.findByRoleIn(roles, Sort.by(Sort.Direction.ASC, "names"))).willReturn(volunteers1);

    List<Profile> volunteers = profileService.findVolunteers();

    assertThat(volunteers).isNotNull();
    assertThat(volunteers.size()).isEqualTo(volunteers1.size());

  }

  @Test
  @DisplayName("Test Unit #13a: findVolunteers(page, size) should return page of volunteers")

  void shouldFindVolunteersPage() throws Exception {

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
    volunteer8.setStatus("Pending");
    volunteer8.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    volunteer8.setGender("Male");
    volunteer8.setSurname("Test #1");
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> volunteers1 = new ArrayList<>();
    volunteers1.add(volunteer8);

    Page<Profile> items = new PageImpl<>(volunteers1);

    List<String> roles = new ArrayList<>();
    roles.add("Volunteer - FSP");
    roles.add("Volunteer - FDR");

    given(profileRepository.findByRoleIn(roles, PageRequest.of(0, PAGINATION_SIZE, Sort.by(Sort.Direction.ASC, "names")))).willReturn(items);

    Page<Profile> volunteers = profileService.findVolunteers(0, PAGINATION_SIZE);

    assertThat(volunteers).isNotNull();
    assertThat(volunteers.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #14: deleteProfileById(id) should return profile")

  void shouldDeleteProfileById() throws Exception {

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
    member2.setStatus("Pending");
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    Profile member2d = new Profile();
    
    member2d.setId(2);
    member2d.setNric_uen("S1000001A");
    member2d.setNames("Member #1");
    member2d.setRole("Member");
    member2d.setEmail("member1@gmail.com");
    member2d.setPhone("10000001");
    member2d.setAddress("55 Pipit Road");
    member2d.setUnit("#11-08");
    member2d.setPostal("370055");
    member2d.setStatus("Deleted");
    member2d.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2d.setGender("Female");
    member2d.setSurname("Test #1");
    member2d.setDob(LocalDate.of(1971, 1, 1));
    member2d.setDisabilities("Disability #1");
    member2d.setIncome(1000);
    member2d.setHousehold(1);
    member2d.setDiet("Halal");
    member2d.setAllergies("Allergy #1");
    member2d.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    member2d.setUpdated(localDateTime);

    given(profileRepository.findById(member2.getId())).willReturn(member2);
    given(profileRepository.saveAndFlush(member2d)).willReturn(member2);

    Profile profile = profileService.deleteProfileById(Integer.toString(member2.getId()));

    assertThat(profile).isNotNull();
    assertThat(profile.getId()).isEqualTo(member2d.getId());
    assertThat(profile.getStatus()).isEqualTo(member2d.getStatus());
    assertThat(profile.getUpdated()).isNotNull();
      // Note: profileService.deleteProfileById can return any profile with slightly different value of updated property.

  }


  @Test
  @DisplayName("Test Unit #15: findProfileById(id) should return profile")

  void shouldFindProfileById() throws Exception {

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
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    given(profileRepository.findById(member2.getId())).willReturn(member2);

    Profile profile = profileService.findProfileById(Integer.toString(member2.getId()));

    assertThat(profile).isNotNull();
    assertThat(profile.getId()).isEqualTo(member2.getId());

  }


  @Test
  @DisplayName("Test Unit #16: findProfileByEmailAndPassword(username, password) should return profile")

  void shouldFindProfileByEmailAndPassword() throws Exception {

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
    member2.setPassword("$2a$10$fOgauIyAtk6sQ9FKdzlwPelqS62LXG4wgxut7zkxdivIK14.dJXKK");
    member2.setGender("Female");
    member2.setSurname("Test #1");
    member2.setDob(LocalDate.of(1971, 1, 1));
    member2.setDisabilities("Disability #1");
    member2.setIncome(1000);
    member2.setHousehold(1);
    member2.setDiet("Halal");
    member2.setAllergies("Allergy #1");
    member2.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));

    given(profileRepository.findByEmailAndPassword(member2.getEmail(), member2.getPassword())).willReturn(member2);

    Profile profile = profileService.findProfileByEmailAndPassword(member2.getEmail(), member2.getPassword());

    assertThat(profile).isNotNull();
    assertThat(profile.getId()).isEqualTo(member2.getId());

  }


  @Test
  @DisplayName("Test Unit #17: findRiders() should return riders")

  void shouldFindRiders() throws Exception {

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
    volunteer8.setCreated(LocalDateTime.of(2025, 6, 11, 0, 0));
    volunteer8.setUpdated(LocalDateTime.of(2025, 6, 11, 0, 0));

    List<Profile> riders1 = new ArrayList<>();
    riders1.add(volunteer8);

    List<String> roles = new ArrayList<>();
    roles.add("Partner - FDR");
    roles.add("Volunteer - FDR");

    given(profileRepository.findByStatusAndRoleIn("Active", roles, Sort.by(Sort.Direction.ASC, "names"))).willReturn(riders1);

    List<Profile> riders = profileService.findRiders();

    assertThat(riders).isNotNull();
    assertThat(riders.size()).isEqualTo(riders1.size());

  }


 /********************************************************************************
  Note:
  sendEmail(address, subject, text) private method to be tested by other ways.
  ********************************************************************************/



}
