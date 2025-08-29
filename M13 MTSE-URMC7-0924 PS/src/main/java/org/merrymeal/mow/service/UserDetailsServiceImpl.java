/*
 * Class Name:  UserDetailsServiceImpl
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

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.User;


@Service
@Transactional

public class UserDetailsServiceImpl implements UserDetailsService {



    @Autowired
    private ProfileRepository profileRepository;



    public UserDetailsServiceImpl() {

      // Note: Default constructor 

    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      Profile profile = profileRepository.findByEmail(username);

      if (profile == null || !profile.getStatus().equals("Active")) {

        throw new UsernameNotFoundException(String.format("%s (%s) %s. %s.", "Username", username, "is not valid", "Please re-enter"));

      } else {

        UserBuilder userBuilder = User.builder();

        return userBuilder
          .username(profile.getEmail())
          .password(profile.getPassword())
          .roles(profile.getRole().toUpperCase())
          .build();

      }

    }



}
