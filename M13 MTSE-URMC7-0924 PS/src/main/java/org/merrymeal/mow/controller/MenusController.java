/*
 * Class Name:  MenusController
 * Description: Controller
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.controller;


import org.merrymeal.mow.entity.Profile;
import org.merrymeal.mow.entity.Menu;
import org.merrymeal.mow.entity.Meal;
import org.merrymeal.mow.entity.Pickup;
import org.merrymeal.mow.entity.Feedback;

import org.merrymeal.mow.service.ProfileService;
import org.merrymeal.mow.service.MenuService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;

import jakarta.validation.Valid;

import java.security.Principal;

import java.util.List;
import java.util.ArrayList;


@Controller

public class MenusController {



  @Autowired
  private ProfileService profileService;

  @Autowired
  private MenuService    menuService;


  private final String PAGINATION_SIZE = "2";
    // Note: Default and maximum should be "15" as measured.



  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/menu-planning")

  public String showMenuPlanning(
    Principal principal,
    Model model) {

    return "menu-planning";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/menu-planning_halal")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showMenuPlanningHalal(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Menu> menus = new ArrayList<>();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menus = menuService.findMenus("Halal", username);

    }

    model.addAttribute("menus", menus);

    return "menu-planning_halal";

  }

 ********************************************************************************/

  public String showMenuPlanningHalal(
    Principal principal,
    Model model,
    @RequestParam(name = "page",     required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",     required = false, defaultValue = PAGINATION_SIZE) int size,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Menu> menus = new ArrayList<>();
    int pages = 0;

    Page<Menu> items = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = menuService.findMenus("Halal", username, page, size);

      menus = items.getContent();
      pages = items.getTotalPages();

    }

    model.addAttribute("menus", menus);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "menu-planning_halal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/menu-planning_veg")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showMenuPlanningVeg(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Menu> menus = new ArrayList<>();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menus = menuService.findMenus("Vegetarian", username);

    }

    model.addAttribute("menus", menus);

    return "menu-planning_veg";

  }

 ********************************************************************************/

  public String showMenuPlanningVeg(
    Principal principal,
    Model model,
    @RequestParam(name = "page",     required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",     required = false, defaultValue = PAGINATION_SIZE) int size,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Menu> menus = new ArrayList<>();
    int pages = 0;

    Page<Menu> items = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = menuService.findMenus("Vegetarian", username, page, size);

      menus = items.getContent();
      pages = items.getTotalPages();

    }

    model.addAttribute("menus", menus);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "menu-planning_veg";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/menu-planning_soft")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showMenuPlanningSoft(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Menu> menus = new ArrayList<>();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menus = menuService.findMenus("Soft", username);

    }

    model.addAttribute("menus", menus);

    return "menu-planning_soft";

  }

 ********************************************************************************/

  public String showMenuPlanningSoft(
    Principal principal,
    Model model,
    @RequestParam(name = "page",     required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",     required = false, defaultValue = PAGINATION_SIZE) int size,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Menu> menus = new ArrayList<>();
    int pages = 0;

    Page<Menu> items = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = menuService.findMenus("Soft", username, page, size);

      menus = items.getContent();
      pages = items.getTotalPages();

    }

    model.addAttribute("menus", menus);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "menu-planning_soft";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/menu-planning_normal")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String showMenuPlanningNormal(
    Principal principal,
    Model model,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Menu> menus = new ArrayList<>();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menus = menuService.findMenus("Normal", username);

    }

    model.addAttribute("menus", menus);    

    return "menu-planning_normal";

  }

 ********************************************************************************/

  public String showMenuPlanningNormal(
    Principal principal,
    Model model,
    @RequestParam(name = "page",     required = false, defaultValue = "0") int page,
    @RequestParam(name = "size",     required = false, defaultValue = PAGINATION_SIZE) int size,
    @RequestParam(name = "username", required = false) String username) {
      // Note: username parameter for testing

    List<Menu> menus = new ArrayList<>();
    int pages = 0;

    Page<Menu> items = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = menuService.findMenus("Normal", username, page, size);

      menus = items.getContent();
      pages = items.getTotalPages();

    }

    model.addAttribute("menus", menus);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "menu-planning_normal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/menu-planning_halal_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteMenuPlanningHalal(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id) {

    Menu menu = new Menu();

    String username = null;

    List<Menu> menus = new ArrayList<>();

    if (id != null && !id.equals("")) {
      menu = menuService.deleteMenuById(id);
    }

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menus = menuService.findMenus("Halal", username);

    }

    model.addAttribute("menus", menus);

    return "menu-planning_halal";

  }

 ********************************************************************************/

  public String deleteMenuPlanningHalal(
    Principal principal,
    Model model,
    @RequestParam(name = "id",   required = true) String id,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    Menu menu = new Menu();

    String username = null;

    List<Menu> menus = new ArrayList<>();
    int pages = 0;

    Page<Menu> items = null;

    if (id != null && !id.equals("")) {
      menu = menuService.deleteMenuById(id);
    }

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = menuService.findMenus("Halal", username, page, size);

      menus = items.getContent();
      pages = items.getTotalPages();

    }

    model.addAttribute("menus", menus);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "menu-planning_halal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/menu-planning_veg_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteMenuPlanningVeg(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id) {

    Menu menu = new Menu();

    String username = null;

    List<Menu> menus = new ArrayList<>();

    if (id != null && !id.equals("")) {
      menu = menuService.deleteMenuById(id);
    }

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menus = menuService.findMenus("Vegetarian", username);

    }

    model.addAttribute("menus", menus);

    return "menu-planning_veg";

  }

 ********************************************************************************/

  public String deleteMenuPlanningVeg(
    Principal principal,
    Model model,
    @RequestParam(name = "id",   required = true) String id,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    Menu menu = new Menu();

    String username = null;

    List<Menu> menus = new ArrayList<>();
    int pages = 0;

    Page<Menu> items = null;

    if (id != null && !id.equals("")) {
      menu = menuService.deleteMenuById(id);
    }

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = menuService.findMenus("Vegetarian", username, page, size);

      menus = items.getContent();
      pages = items.getTotalPages();

    }

    model.addAttribute("menus", menus);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "menu-planning_veg";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/menu-planning_soft_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteMenuPlanningSoft(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id) {

    Menu menu = new Menu();

    String username = null;

    List<Menu> menus = new ArrayList<>();

    if (id != null && !id.equals("")) {
      menu = menuService.deleteMenuById(id);
    }

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menus = menuService.findMenus("Soft", username);

    }

    model.addAttribute("menus", menus);

    return "menu-planning_soft";

  }

 ********************************************************************************/

  public String deleteMenuPlanningSoft(
    Principal principal,
    Model model,
    @RequestParam(name = "id",   required = true) String id,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    Menu menu = new Menu();

    String username = null;

    List<Menu> menus = new ArrayList<>();
    int pages = 0;

    Page<Menu> items = null;

    if (id != null && !id.equals("")) {
      menu = menuService.deleteMenuById(id);
    }

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = menuService.findMenus("Soft", username, page, size);

      menus = items.getContent();
      pages = items.getTotalPages();

    }

    model.addAttribute("menus", menus);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "menu-planning_soft";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/menu-planning_normal_deletion")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String deleteMenuPlanningNormal(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = true) String id) {

    Menu menu = new Menu();

    String username = null;

    List<Menu> menus = new ArrayList<>();

    if (id != null && !id.equals("")) {
      menu = menuService.deleteMenuById(id);
    }

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menus = menuService.findMenus("Normal", username);

    }

    model.addAttribute("menus", menus);

    return "menu-planning_normal";

  }

 ********************************************************************************/

  public String deleteMenuPlanningNormal(
    Principal principal,
    Model model,
    @RequestParam(name = "id",   required = true) String id,
    @RequestParam(name = "page", required = false, defaultValue = "0") int page,
    @RequestParam(name = "size", required = false, defaultValue = PAGINATION_SIZE) int size) {

    Menu menu = new Menu();

    String username = null;

    List<Menu> menus = new ArrayList<>();
    int pages = 0;

    Page<Menu> items = null;

    if (id != null && !id.equals("")) {
      menu = menuService.deleteMenuById(id);
    }

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      items = menuService.findMenus("Normal", username, page, size);

      menus = items.getContent();
      pages = items.getTotalPages();

    }

    model.addAttribute("menus", menus);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "menu-planning_normal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/menu-planning_halal_menu")

  public String showMenuPlanningHalalMenu(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = false) String id) {

    String username  = null;

	Profile provider = new Profile();

    Menu menu = new Menu();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {
      provider = profileService.findProfileByEmail(username);
    }

    if (id != null && !id.equals("")) {
      menu = menuService.findMenuById(id);
    }

    if (provider.getRole() != null && (provider.getRole().equals("Partner - FSP") || provider.getRole().equals("Volunteer - FSP"))) {
      model.addAttribute("provider", provider);
    }

    model.addAttribute("menu", menu);

    return "menu-planning_halal_menu";

  }
  

  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/menu-planning_veg_menu")

  public String showMenuPlanningVegMenu(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = false) String id) {

    String username  = null;

    Profile provider = new Profile();

    Menu menu = new Menu();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {
      provider = profileService.findProfileByEmail(username);
    }

    if (id != null && !id.equals("")) {
      menu = menuService.findMenuById(id);
    }

    if (provider.getRole() != null && (provider.getRole().equals("Partner - FSP") || provider.getRole().equals("Volunteer - FSP"))) {
      model.addAttribute("provider", provider);
    }

    model.addAttribute("menu", menu);

    return "menu-planning_veg_menu";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/menu-planning_soft_menu")

  public String showMenuPlanningSoftMenu(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = false) String id) {

    String username  = null;

    Profile provider = new Profile();

    Menu menu = new Menu();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {
      provider = profileService.findProfileByEmail(username);
    }

    if (id != null && !id.equals("")) {
      menu = menuService.findMenuById(id);
    }

    if (provider.getRole() != null && (provider.getRole().equals("Partner - FSP") || provider.getRole().equals("Volunteer - FSP"))) {
      model.addAttribute("provider", provider);
    }

    model.addAttribute("menu", menu);

    return "menu-planning_soft_menu";

  }

 
  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @GetMapping("/menu-planning_normal_menu")

  public String showMenuPlanningNormalMenu(
    Principal principal,
    Model model,
    @RequestParam(name = "id", required = false) String id) {

    String username  = null;

    Profile provider = new Profile();

    Menu menu = new Menu();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {
      provider = profileService.findProfileByEmail(username);
    }

    if (id != null && !id.equals("")) {
      menu = menuService.findMenuById(id);
    }

    if (provider.getRole() != null && (provider.getRole().equals("Partner - FSP") || provider.getRole().equals("Volunteer - FSP"))) {
      model.addAttribute("provider", provider);
    }

    model.addAttribute("menu", menu);

    return "menu-planning_normal_menu";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/menu-planning_halal_menu")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String saveMenuPlanningHalalMenu(
    Principal principal,
    Model model,
    @ModelAttribute("menu") @Valid Menu menu) {

    String username = null;

    List<Menu> menus = new ArrayList<>();

    if (principal != null) {
        username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menu  = menuService.saveMenu(menu, username);

      menus = menuService.findMenus("Halal", username);

    }

    model.addAttribute("menus", menus);

    return "menu-planning_halal";

  }

 ********************************************************************************/

  public String saveMenuPlanningHalalMenu(
    Principal principal,
    Model model,
    @ModelAttribute("menu") @Valid Menu menu) {

    String username = null;

    List<Menu> menus = new ArrayList<>();
    int pages = 0;

    int page  = 0;
    int size  = Integer.parseInt(PAGINATION_SIZE);

    Page<Menu> items = null;

    if (principal != null) {
        username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menu  = menuService.saveMenu(menu, username);

      items = menuService.findMenus("Halal", username, page, size);

      menus = items.getContent();
      pages = items.getTotalPages();

    }

    model.addAttribute("menus", menus);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "menu-planning_halal";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/menu-planning_veg_menu")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String saveMenuPlanningVegMenu(
    Principal principal,
    Model model,
    @ModelAttribute("menu") @Valid Menu menu) {

    String username = null;

    List<Menu> menus = new ArrayList<>();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menu  = menuService.saveMenu(menu, username);

      menus = menuService.findMenus("Vegetarian", username);

    }

    model.addAttribute("menus", menus);

    return "menu-planning_veg";

  }

 ********************************************************************************/

  public String saveMenuPlanningVegMenu(
    Principal principal,
    Model model,
    @ModelAttribute("menu") @Valid Menu menu) {

    String username = null;

    List<Menu> menus = new ArrayList<>();
    int pages = 0;

    int page  = 0;
    int size  = Integer.parseInt(PAGINATION_SIZE);

    Page<Menu> items = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menu  = menuService.saveMenu(menu, username);

      items = menuService.findMenus("Vegetarian", username, page, size);

      menus = items.getContent();
      pages = items.getTotalPages();

    }

    model.addAttribute("menus", menus);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "menu-planning_veg";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/menu-planning_soft_menu")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String saveMenuPlanningSoftMenu(
    Principal principal,
    Model model,
    @ModelAttribute("menu") @Valid Menu menu) {

    String username = null;

    List<Menu> menus = new ArrayList<>();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menu  = menuService.saveMenu(menu, username);

      menus = menuService.findMenus("Soft", username);

    }

    model.addAttribute("menus", menus);

    return "menu-planning_soft";

  }

 ********************************************************************************/

  public String saveMenuPlanningSoftMenu(
    Principal principal,
    Model model,
    @ModelAttribute("menu") @Valid Menu menu) {

    String username = null;

    List<Menu> menus = new ArrayList<>();
    int pages = 0;

    int page  = 0;
    int size  = Integer.parseInt(PAGINATION_SIZE);

    Page<Menu> items = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menu  = menuService.saveMenu(menu, username);

      items = menuService.findMenus("Soft", username, page, size);

      menus = items.getContent();
      pages = items.getTotalPages();

    }

    model.addAttribute("menus", menus);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "menu-planning_soft";

  }


  @PreAuthorize("hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')")
  @PostMapping("/menu-planning_normal_menu")

 /********************************************************************************
  Note: Switched to simple pagination.
        See above PAGINATION_SIZE constant.
  ********************************************************************************

  public String saveMenuPlanningNormalMenu(
    Principal principal,
    Model model,
    @ModelAttribute("menu") @Valid Menu menu) {

    String username = null;

    List<Menu> menus = new ArrayList<>();

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menu  = menuService.saveMenu(menu, username);

      menus = menuService.findMenus("Normal", username);

    }

    model.addAttribute("menus", menus);

    return "menu-planning_normal";

  }

 ********************************************************************************/

  public String saveMenuPlanningNormalMenu(
    Principal principal,
    Model model,
    @ModelAttribute("menu") @Valid Menu menu) {

    String username = null;

    List<Menu> menus = new ArrayList<>();
    int pages = 0;

    int page  = 0;
    int size  = Integer.parseInt(PAGINATION_SIZE);

    Page<Menu> items = null;

    if (principal != null) {
      username = principal.getName();
    }

    if (username != null && !username.equals("")) {

      menu  = menuService.saveMenu(menu, username);

      items = menuService.findMenus("Normal", username, page, size);

      menus = items.getContent();
      pages = items.getTotalPages();

    }

    model.addAttribute("menus", menus);
    model.addAttribute("pages", pages);

    model.addAttribute("current", page);
    model.addAttribute("size",    size);

    return "menu-planning_normal";

  }



}
