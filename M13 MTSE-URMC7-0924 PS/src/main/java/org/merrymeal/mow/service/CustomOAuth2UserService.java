/*
 * Class Name:  CustomOAuth2UserService
 * Description: Service
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */



package org.merrymeal.mow.service;


import org.merrymeal.mow.entity.Profile;

import org.merrymeal.mow.repository.ProfileRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.ArrayList;



@Service
@Transactional


public class CustomOAuth2UserService extends DefaultOAuth2UserService {



  @Autowired
  private ProfileRepository profileRepository;



  public CustomOAuth2UserService() {

    // Note: Default constructor

  }


  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2User oAuth2User = super.loadUser(userRequest);
    String email = oAuth2User.getAttribute("email");

    Profile profile = profileRepository.findByEmail(email);

    if (profile == null || !profile.getStatus().equals("Active")) {

      throw new UsernameNotFoundException(String.format("%s (%s) %s.", "Username", email, "has no profile found by OAuth2"));

    } else {

   	  System.out.println(String.format("%s (%s) %s.", "Username", email, "has profile found by OAuth2"));

      List<GrantedAuthority> authorities = new ArrayList<>();
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + profile.getRole().toUpperCase());
        // Note: Set authority as "ROLE_USER" which hasAnyRole("USER") will retrieve.
      authorities.add(authority);

      return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "email");
      // Note: Return existing OAuth2User with authorities added.

    }

  }



}
