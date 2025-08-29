/*
 * Class Name:  MenuServiceTests
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

public class MenuServiceTests {



  @Mock
  private ProfileRepository profileRepository;

  @Mock
  private MenuRepository menuRepository;


  @InjectMocks
  private MenuService menuService;



  final int PAGINATION_SIZE = 2;
    // Default and maximum should be 15 as measured.



  @Test
  @DisplayName("Test Unit #01: findMenus(diet, username) should return menus")

  void shouldFindMenus() throws Exception {

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

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Chicken Chop");
    menu1.setDiet("Normal");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");
    menu1.setProvider(partner7);

    List<Menu> menus1 = new ArrayList<>();
    menus1.add(menu1);

    given(profileRepository.findByEmail(partner7.getEmail())).willReturn(partner7);
    given(menuRepository.findByDietAndProvider_Id("Normal", partner7.getId())).willReturn(menus1);

    List<Menu> menus = menuService.findMenus("Normal", partner7.getEmail());

    assertThat(menus).isNotNull();
    assertThat(menus.size()).isEqualTo(menus1.size());

  }

  @Test
  @DisplayName("Test Unit #01a: findMenus(diet, username, page, size) should return page of menus")

  void shouldFindMenusPage() throws Exception {

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

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Chicken Chop");
    menu1.setDiet("Normal");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");
    menu1.setProvider(partner7);

    List<Menu> menus1 = new ArrayList<>();
    menus1.add(menu1);

    Page<Menu> items = new PageImpl(menus1);

    given(profileRepository.findByEmail(partner7.getEmail())).willReturn(partner7);
    given(menuRepository.findByDietAndProvider_Id("Normal", partner7.getId(), PageRequest.of(0, PAGINATION_SIZE))).willReturn(items);

    Page<Menu> menus = menuService.findMenus("Normal", partner7.getEmail(), 0, PAGINATION_SIZE);

    assertThat(menus).isNotNull();
    assertThat(menus.getTotalPages()).isEqualTo(items.getTotalPages());

  }


  @Test
  @DisplayName("Test Unit #02: deleteMenuById(id) should return menu")

  void shouldDeleteMenuById() throws Exception {

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

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Chicken Chop");
    menu1.setDiet("Normal");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");
    menu1.setProvider(partner7);

    Menu menu1d = new Menu();

    menu1d.setId(1);
    menu1d.setSeq_day(1);
    menu1d.setSeq_time(1);
    menu1d.setName("Chicken Chop");
    menu1d.setDiet("Normal");
    menu1d.setFrozen("Yes");
    menu1d.setStatus("Deleted");
    menu1d.setProvider(partner7);

    given(menuRepository.findById(menu1.getId())).willReturn(menu1);
    given(menuRepository.saveAndFlush(menu1d)).willReturn(menu1d);

    Menu menu = menuService.deleteMenuById(Integer.toString(menu1.getId()));

    assertThat(menu).isNotNull();
    assertThat(menu.getId()).isEqualTo(menu1d.getId());
    assertThat(menu.getStatus()).isEqualTo(menu1d.getStatus());

  }


  @Test
  @DisplayName("Test Unit #03: findMenuById(id) should return menu")

  void shouldFindMenuById() throws Exception {

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

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Chicken Chop");
    menu1.setDiet("Normal");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");
    menu1.setProvider(partner7);

    given(menuRepository.findById(menu1.getId())).willReturn(menu1);

    Menu menu = menuService.findMenuById(Integer.toString(menu1.getId()));

    assertThat(menu).isNotNull();
    assertThat(menu.getId()).isEqualTo(menu1.getId());

  }


  @Test
  @DisplayName("Test Unit #04: saveMenu(menu, username) should return menu")

  void shouldSaveMenu() throws Exception {

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

    Menu menu1 = new Menu();

    menu1.setId(1);
    menu1.setSeq_day(1);
    menu1.setSeq_time(1);
    menu1.setName("Chicken Chop");
    menu1.setDiet("Normal");
    menu1.setFrozen("Yes");
    menu1.setStatus("Active");

    Menu menu1u = new Menu();

    menu1u.setId(1);
    menu1u.setSeq_day(1);
    menu1u.setSeq_time(1);
    menu1u.setName("Chicken Chop");
    menu1u.setDiet("Normal");
    menu1u.setFrozen("Yes");
    menu1u.setStatus("Active");
    menu1u.setProvider(partner7);

    given(profileRepository.findByEmail(partner7.getEmail())).willReturn(partner7);
    given(menuRepository.saveAndFlush(menu1u)).willReturn(menu1u);

    Menu menu = menuService.saveMenu(menu1, partner7.getEmail());

    assertThat(menu).isNotNull();
    assertThat(menu.getId()).isEqualTo(menu1u.getId());

  }



}
