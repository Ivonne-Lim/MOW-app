/*
 * Class Name:  AccessRestController
 * Description: RestController
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Release with partial mobile app         Ivonne Lim  04-Sep-2025
 */


package org.merrymeal.mow.restcontroller;


import org.merrymeal.mow.entity.Profile;
import org.merrymeal.mow.entity.Menu;
import org.merrymeal.mow.entity.Meal;
import org.merrymeal.mow.entity.Pickup;
import org.merrymeal.mow.entity.Feedback;

import org.merrymeal.mow.model.LoginDTO;
import org.merrymeal.mow.model.AccessDTO;

import org.merrymeal.mow.service.ProfileService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

import org.jasypt.util.password.StrongPasswordEncryptor;


@RestController

@CrossOrigin(origins = {"*"})

public class AccessRestController {



  @Autowired
  private ProfileService profileService;


  private StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();



  @PostMapping(value = "/login/api", produces = "application/json")

  public ResponseEntity<AccessDTO> loginAccess(
     @RequestBody LoginDTO loginDTO) {

    Profile profile = new Profile();

    profile = profileService.findProfileByEmail(loginDTO.getEmail());

    // Note: Return NOT_FOUND if email not found or password not matching
    if ((profile == null) || (!encryptor.checkPassword(loginDTO.getPassword(), profile.getPassword_api()))) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    AccessDTO accessDTO = new AccessDTO(profile, profileService.generateToken(loginDTO.getEmail()));
    return new ResponseEntity<>(accessDTO, HttpStatus.OK);

  }



}
