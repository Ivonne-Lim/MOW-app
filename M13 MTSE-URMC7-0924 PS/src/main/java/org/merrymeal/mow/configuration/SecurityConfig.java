/*
 * Class Name:  SecurityConfig
 * Description: Configuration
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 * 1.01     Release with partial mobile app         Ivonne Lim  04-Sep-2025     
 */


package org.merrymeal.mow.configuration;


import org.merrymeal.mow.service.UserDetailsServiceImpl;
import org.merrymeal.mow.service.CustomOAuth2UserService;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.Customizer;

import org.springframework.http.HttpMethod;


@Configuration
@EnableWebSecurity

public class SecurityConfig {



 /********************************************************************************
  Version 1.01
  ********************************************************************************/

  public static final String[] ANONYMOUS_GET_ENDPOINTS_API = {


  };


  public static final String[] ANONYMOUS_POST_ENDPOINTS_API = {

    "/login/api"
      // Note: URL defined in Access REST controller

  };

 /********************************************************************************/


  public static final String[] ANONYMOUS_GET_ENDPOINTS = {

    "/access",
      // Note: URL defined in Access controller
    "/login_failure", "/logout_success"
      // Note: URL defined in Access controller

  };


  public static final String[] ANONYMOUS_POST_ENDPOINTS = {

    "/login"
      // Note: URL defined in Access controller

  };


  public static final String[] WHITELIST_GET_ENDPOINTS = {

    "/pdf/*.pdf",
      // Note: PDF files   located in static folder

    "/images/*.jpg", "/images/*.png",
      // Note: Image files located in static folder

    "/js/*.js",
      // Note: JS  files   located in static folder

    "/css/*.css",
      // Note: CSS files   located in static folder

    "/header.jsp", "/footer.jsp",
      // Note: Includes    located in views  folder

    "/access_denial",
      // Note: URL defined in Access Denial controller

    "/home", "/",
      // Note: URL defined in Home controller

    "/contacts",
      // Note: URL defined in Contacts controller

    "/registration",
      // Note: URL defined in Registration controller
    "/registration_member", "/registration_partner", "/registration_volunteer",
      // Note: URL defined in Registration controller
    "/registration_member_caregivers",
      // Note: URL defined in Registration controller
    "/registration_member_caregivers_caregiver",
      // Note: URL defined in Registration controller
    "/registration_member_caregivers_caregiver_retrieval"
    // Note: URL defined in Registration controller

  };


  public static final String[] WHITELIST_POST_ENDPOINTS = {

    "/registration_member", "/registration_partner", "/registration_volunteer",
      // Note: URL defined in Registration controller
    "/registration_member_caregivers",
      // Note: URL defined in Registration controller
    "/registration_member_caregivers_deletion",
      "/registration_member_caregivers_caregiver"
      // Note: URL defined in Registration controller

  };


  public static final String[] ANY_GET_ENDPOINTS = {

/********************************************************************************
    Note: No "/logout_failure"
 ********************************************************************************/

    "/login_success",
      // Note: URL defined in Access controller

    "/service-feedback",
      // Note: URL defined in Feedback controller
    "/service-feedback_submission"
      // Note: URL defined in Feedback controller

  };


  public static final String[] ANY_POST_ENDPOINTS = {

/********************************************************************************
    Note: No "/access"
 ********************************************************************************/

    "/logout"
	  // Note: URL defined in Access controller

  };


  public static final String[] ADMIN_GET_ENDPOINTS = {

    "/dashboard",
      // Note: URL defined in Home controller

    "/evaluation",
      // Note: URL defined in Evaluation controller
    "/evaluation_members", "/evaluation_partners", "/evaluation_volunteers",
      // Note: URL defined in Evaluation controller
    "/evaluation_members_member", "/evaluation_partners_partner", "/evaluation_volunteers_volunteer",
      // Note: URL defined in Evaluation controller
    "evaluation_members_member_caregivers",
      // Note: URL defined in Evaluation controller
    "/evaluation_members_member_caregivers_caregiver",
      // Note: URL defined in Evaluation controller

    "/meal-delivery_assignment"
      // Note: URL defined in Pickups controller    

  };


  public static final String[] ADMIN_POST_ENDPOINTS = {

    "/evaluation_members_deletion", "/evaluation_partners_deletion", "/evaluation_volunteers_deletion",
      "/evaluation_members_member", "/evaluation_partners_partner", "/evaluation_volunteers_volunteer",
      // Note: URL defined in Evaluation controller
      "/evaluation_members_member_caregivers",
      // Note: URL defined in Evaluation controller
      "/evaluation_members_member_caregivers_deletion",
      "/evaluation_members_member_caregivers_caregiver",
      // Note: URL defined in Evaluation controller

    "/meal-delivery_assignment",
      // Note: URL defined in Pickups controller

  };

  
  public static final String[] MEMBER_ADMIN_GET_ENDPOINTS = {


  };


  public static final String[] MEMBER_ADMIN_POST_ENDPOINTS = {

    "/service-feedback_deletion",
      "/service-feedback_submission"
      // Note: URL defined in Feedback controller

  };


  public static final String[] MEMBER_GET_ENDPOINTS = {


  };


  public static final String[] MEMBER_POST_ENDPOINTS = {


  };


  public static final String[] PROVIDER_MEMBER_ADMIN_GET_ENDPOINTS = {

    "/meal-preparation",
      // Note: URL defined in Meals controller
    "/meal-preparation_halal", "/meal-preparation_veg", "/meal-preparation_soft", "/meal-preparation_normal",
      // Note: URL defined in Meals controller

  };


  public static final String[] PROVIDER_MEMBER_ADMIN_POST_ENDPOINTS = {


  };

  
  public static final String[] PROVIDER_GET_ENDPOINTS = {

    "/menu-planning",
      // Note: URL defined in Menus controller
    "/menu-planning_halal", "/menu-planning_veg", "/menu-planning_soft", "/menu-planning_normal",
      // Note: URL defined in Menus controller
    "/menu-planning_halal_menu", "/menu-planning_veg_menu", "/menu-planning_soft_menu", "/menu-planning_normal_menu"
      // Note: URL defined in Menus controller

  };


  public static final String[] PROVIDER_POST_ENDPOINTS = {

    "/menu-planning_halal_deletion", "/menu-planning_veg_deletion", "/menu-planning_soft_deletion", "/menu_planning_normal_deletion",
      "/menu-planning_halal_menu", "/menu-planning_veg_menu", "/menu-planning_soft_menu", "/menu-planning_normal_menu",
      // Note: URL defined in Menus controller

    "/meal-preparation_halal_generation", "/meal-preparation_veg_generation", "/meal-preparation_soft_generation", "/meal-preparation_normal_generation",
      "/meal-preparation_halal_updation", "/meal-preparation_veg_updation", "/meal-preparation_soft_updation", "/meal-preparation_normal_updation",
      "/meal-preparation_halal_deletion", "/meal-preparation_veg_deletion", "/meal-preparation_soft_deletion", "/meal-preparation_normal_deletion",
      "/meal-preparation_halal_printing", "/meal-preparation_veg_printing", "/meal-preparation_soft_printing", "/meal-preparation_normal_printing"
      // Note: URL defined in Meals controller

  };


  public static final String[] RIDER_MEMBER_ADMIN_GET_ENDPOINTS = {

    "/meal-delivery"
      // Note: URL defined in Pickups controller

  };

 
  public static final String[] RIDER_MEMBER_ADMIN_POST_ENDPOINTS = {


  };


  public static final String[] RIDER_MEMBER_GET_ENDPOINTS = {

    "/meal-delivery_current",
      // Note: URL defined in Pickups controller
    "/meal-delivery_current_pickup_caregivers"
      // Note: URL defined in Pickups controller

  };


  public static final String[] RIDER_MEMBER_POST_ENDPOINTS = {


  };


  public static final String[] RIDER_GET_ENDPOINTS = {

    "/meal-delivery_acceptance",
      // Note: URL defined in Pickups controller
    "/meal-delivery_current_pickup"
      // Note: URL defined in Pickups controller

  };


  public static final String[] RIDER_POST_ENDPOINTS = {

    "/meal-delivery_acceptance",
      // Note: URL defined in Pickups controller
    "/meal-delivery_current_pickup"
      // Note: URL defined in Pickups controller

  };



  public static final String LOGIN_PAGE_URL = "/access";

  public static final String LOGIN_PROCESS_URL = "/login";

  public static final String LOGIN_FAILURE_URL = "/login_failure";

  public static final String LOGIN_SUCCESS_URL = "/login_success";


  public static final String USERNAME_PARAMETER = "email";

  public static final String PASSWORD_PARAMETER = "password";


/********************************************************************************
  Note: No public static final String LOGOUT_PAGE_URL = "/access";
 ********************************************************************************/

  public static final String LOGOUT_PROCESS_URL = "/logout";

/********************************************************************************
  Note: No public static final String LOGOUT_FAILURE_URL = "/logout_failure";
 ********************************************************************************/

  public static final String LOGOUT_SUCCESS_URL = "/logout_success";


  public static final String ACCESS_DENIAL_URL = "/access_denial";



  @Bean
  
  public CustomOAuth2UserService getCustomOAuth2UserService() {

    return new CustomOAuth2UserService();

  }


  @Bean

  public UserDetailsService getUserDetailsService() {

    return new UserDetailsServiceImpl();

  }


  @Bean

  public BCryptPasswordEncoder getPasswordEncoder() {

    return new BCryptPasswordEncoder();

  }


  @Bean

  public DaoAuthenticationProvider authenticationProvider() {

    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

    daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
    daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());

    return daoAuthenticationProvider;

  }


  @Bean

  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http

/********************************************************************************
      .oauth2Login(Customizer.withDefaults())
 ********************************************************************************/
      .oauth2Login(oauth2 -> oauth2

         .userInfoEndpoint(userInfo -> userInfo
         .userService(getCustomOAuth2UserService()) ) ) 

      .formLogin(form -> form

        .loginPage(LOGIN_PAGE_URL)
        .loginProcessingUrl(LOGIN_PROCESS_URL)
        .failureUrl(LOGIN_FAILURE_URL)
        .permitAll()

        .defaultSuccessUrl(LOGIN_SUCCESS_URL)

        .usernameParameter(USERNAME_PARAMETER)
        .passwordParameter(PASSWORD_PARAMETER) )

      .authorizeHttpRequests(request -> request

/********************************************************************************
 Version 1.01 
 ********************************************************************************/
        .requestMatchers(HttpMethod.GET,  ANONYMOUS_GET_ENDPOINTS_API).anonymous()
        .requestMatchers(HttpMethod.POST, ANONYMOUS_POST_ENDPOINTS_API).anonymous()
/********************************************************************************/

        .requestMatchers(HttpMethod.GET,  ANONYMOUS_GET_ENDPOINTS).anonymous()
        .requestMatchers(HttpMethod.POST, ANONYMOUS_POST_ENDPOINTS).anonymous()
        .requestMatchers(HttpMethod.GET,  WHITELIST_GET_ENDPOINTS).permitAll()
        .requestMatchers(HttpMethod.POST, WHITELIST_POST_ENDPOINTS).permitAll()
        .requestMatchers(HttpMethod.GET,  ANY_GET_ENDPOINTS).hasAnyRole("ADMINISTRATOR", "MEMBER", "PARTNER - FSP", "PARTNER - FDR", "VOLUNTEER - FSP", "VOLUNTEER - FDR")
        .requestMatchers(HttpMethod.POST, ANY_POST_ENDPOINTS).hasAnyRole("ADMINISTRATOR", "MEMBER", "PARTNER - FSP", "PARTNER - FDR", "VOLUNTEER - FSP", "VOLUNTEER - FDR")
        .requestMatchers(HttpMethod.GET,  ADMIN_GET_ENDPOINTS).hasAnyRole("ADMINISTRATOR")
        .requestMatchers(HttpMethod.POST, ADMIN_POST_ENDPOINTS).hasAnyRole("ADMINISTRATOR")
        .requestMatchers(HttpMethod.GET,  MEMBER_ADMIN_GET_ENDPOINTS).hasAnyRole("ADMINISTRATOR", "MEMBER")
        .requestMatchers(HttpMethod.POST, MEMBER_ADMIN_POST_ENDPOINTS).hasAnyRole("ADMINISTRATOR", "MEMBER")
        .requestMatchers(HttpMethod.GET,  MEMBER_GET_ENDPOINTS).hasAnyRole("MEMBER")
        .requestMatchers(HttpMethod.POST, MEMBER_POST_ENDPOINTS).hasAnyRole("MEMBER")
        .requestMatchers(HttpMethod.GET,  PROVIDER_MEMBER_ADMIN_GET_ENDPOINTS).hasAnyRole("ADMINISTRATOR", "MEMBER", "PARTNER - FSP", "VOLUNTEER - FSP")
        .requestMatchers(HttpMethod.POST, PROVIDER_MEMBER_ADMIN_POST_ENDPOINTS).hasAnyRole("ADMINISTRATOR", "MEMBER", "PARTNER - FSP", "VOLUNTEER - FSP")
        .requestMatchers(HttpMethod.GET,  PROVIDER_GET_ENDPOINTS).hasAnyRole("PARTNER - FSP", "VOLUNTEER - FSP")
        .requestMatchers(HttpMethod.POST, PROVIDER_POST_ENDPOINTS).hasAnyRole("PARTNER - FSP", "VOLUNTEER - FSP")
        .requestMatchers(HttpMethod.GET,  RIDER_MEMBER_ADMIN_GET_ENDPOINTS).hasAnyRole("ADMINISTRATOR", "MEMBER", "PARTNER - FDR", "VOLUNTEER - FDR")
        .requestMatchers(HttpMethod.POST, RIDER_MEMBER_ADMIN_POST_ENDPOINTS).hasAnyRole("ADMINISTRATOR", "MEMBER", "PARTNER - FDR", "VOLUNTEER - FDR")
        .requestMatchers(HttpMethod.GET,  RIDER_MEMBER_GET_ENDPOINTS).hasAnyRole("MEMBER", "PARTNER - FDR", "VOLUNTEER - FDR")
        .requestMatchers(HttpMethod.POST, RIDER_MEMBER_POST_ENDPOINTS).hasAnyRole("MEMBER", "PARTNER - FDR", "VOLUNTEER - FDR")
        .requestMatchers(HttpMethod.GET,  RIDER_GET_ENDPOINTS).hasAnyRole("PARTNER - FDR", "VOLUNTEER - FDR")
        .requestMatchers(HttpMethod.POST, RIDER_POST_ENDPOINTS).hasAnyRole("PARTNER - FDR", "VOLUNTEER - FDR")

/********************************************************************************
        .anyRequest().authenticated()
 ********************************************************************************/
        .anyRequest().permitAll() )

      .logout(logout -> logout

        .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
        .permitAll()

        .logoutUrl(LOGOUT_PROCESS_URL)

        .deleteCookies("JSESSIONID", "remember-me")
        .clearAuthentication(true)
        .invalidateHttpSession(true) )

/********************************************************************************
 Version 1.01
 ********************************************************************************/
      .csrf(csrf -> csrf
        .ignoringRequestMatchers(ANONYMOUS_GET_ENDPOINTS_API)
        .ignoringRequestMatchers(ANONYMOUS_POST_ENDPOINTS_API) )
/********************************************************************************
        .disable() )
 ********************************************************************************/

/********************************************************************************
      .headers(headers -> headers

        .contentSecurityPolicy(csp -> csp
          .policyDirectives("script-src 'self' 'nonce-{random-value}';") ) )

      ISSUE
        Disabled CSP because also blocks event handlers and internal scripts
        even with domains added to directive and scripts put as external.

      ADDRESS
        Enable CSP after
        (1) Removing event handlers and putting in internal scripts as format:
            document.getElementById("id")
              .addEventListener("event", (listener) => function(parameter,...));
        (2) Adding nonce = "${random-value}" to <script> tags of internal scripts.
 ********************************************************************************/

      .exceptionHandling(exception-> exception

        .accessDeniedPage(ACCESS_DENIAL_URL) );

    return http.build();

  }



}
