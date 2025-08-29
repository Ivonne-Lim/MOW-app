/*
 * Class Name:  MenuService
 * Description: Service
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

import org.merrymeal.mow.model.RepertoireDTO;

import org.merrymeal.mow.repository.ProfileRepository;
import org.merrymeal.mow.repository.MenuRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
  // Note: Replacing java.sql.Timestamp;


@Service
@Transactional

public class MenuService {



  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private MenuRepository    menuRepository;



  // Note: Following services for Registration and Menus controllers


  public List<Menu> findMenus(String diet, String username) {

	Profile provider = new Profile();

    List<Menu> menus = new ArrayList<>();
    
    provider = profileRepository.findByEmail(username);

    menus = menuRepository.findByDietAndProvider_Id(diet, provider.getId());
     // @Query used because findByDietAndProvider_Id(diet, provider,getId(),
     //   Sort.by(Sort.Direction.ASC, "Seq_Day").and(Sort.by(Sort.Direction.ASC, "Seq_Time")))
     //   cannot handle Seq_Day and Seq_Time column names

    return menus;

  }

  public Page<Menu> findMenus(String diet, String username, int page, int size) {

	Profile provider = new Profile();

    Page<Menu> items = null;
    
    provider = profileRepository.findByEmail(username);

    items = menuRepository.findByDietAndProvider_Id(diet, provider.getId(),
                                                    PageRequest.of(page,  size));
     // @Query used because findByDietAndProvider_Id(diet, provider,getId(),
     //   Sort.by(Sort.Direction.ASC, "Seq_Day").and(Sort.by(Sort.Direction.ASC, "Seq_Time")))
     //   cannot handle Seq_Day and Seq_Time column names

    return items;

  }


  // Note: Following services for Menus controller


  public Menu deleteMenuById(String id) {

    Menu menu = new Menu();

    menu = menuRepository.findById(Integer.parseInt(id));

    menu.setStatus("Deleted");

    menu = menuRepository.saveAndFlush(menu);

    return menu;

  }


  public Menu findMenuById(String id) {

    Menu menu = new Menu();

    menu = menuRepository.findById(Integer.parseInt(id));

    return menu;

  }


  public Menu saveMenu(Menu menu, String username) {

    Profile provider = new Profile();

    provider = profileRepository.findByEmail(username);

    menu.setProvider(provider);

    menu = menuRepository.saveAndFlush(menu);

    return menu;

  }


  // Note: Following services for Home controller


  public List<RepertoireDTO> groupMenuByDietAndProvider(String diet) {

    List<RepertoireDTO> repertoires = new ArrayList<>();

    repertoires = menuRepository.groupByDietAndProvider(diet);

    return repertoires;
    
  }



}
