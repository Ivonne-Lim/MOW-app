/*
 * Class Name:  ProfileService
 * Description: Service
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 * 1.01     Release with partial mobile app         Ivonne Lim  04-Sep-2025
 */


package org.merrymeal.mow.service;


import org.merrymeal.mow.entity.Profile;
import org.merrymeal.mow.entity.Menu;

import org.merrymeal.mow.repository.ProfileRepository;
import org.merrymeal.mow.repository.MenuRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Properties;
import java.util.Date;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
  // Note: Replacing java.sql.Timestamp;

import java.io.File;
import java.io.InputStream;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Message;
import javax.mail.Transport;
  // Note: JavaMail requires activation.jar (JAF) and javax.mail-1.6.2.jar (JMS)
  //       added into the User Library added into the Java Build Path under the Project Properties
  // Update: Switched to Maven dependencies for WAR packaging since exported runnable JAR will not work with Spring Boot

import java.nio.charset.StandardCharsets;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

/********************************************************************************
 Version 1.01
 ********************************************************************************/
import org.jasypt.util.password.StrongPasswordEncryptor;
/********************************************************************************/


@Service
@Transactional

public class ProfileService {

	

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private MenuRepository    menuRepository;

  @Autowired
  private PasswordEncoder   passwordEncoder;


/********************************************************************************
 Version 1.01
 ********************************************************************************/
  private StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
/********************************************************************************/


  // Note: Following services for Registration and Evaluation controllers


  public Profile saveProfile(Profile profile, String menu_halal_id, String menu_veg_id, String menu_soft_id, String menu_normal_id, String provider_id, String member_id) {

    Menu menu_halal  = new Menu();
    Menu menu_veg    = new Menu();
    Menu menu_soft   = new Menu();
    Menu menu_normal = new Menu();

    Profile provider = new Profile();

    Profile member   = new Profile();

    LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

/********************************************************************************
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedLocalDateTime = localDateTime.format(formatter);
      // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/

    profile.setNric_uen(profile.getNric_uen().toUpperCase());

    if (menu_halal_id != null && !menu_halal_id.equals("")) {
      menu_halal = menuRepository.findById(Integer.parseInt(menu_halal_id));
      profile.setMenu_halal(menu_halal);
    }
    if (menu_veg_id != null && !menu_veg_id.equals("")) {
      menu_veg   = menuRepository.findById(Integer.parseInt(menu_veg_id));
      profile.setMenu_veg(menu_veg);
    }
    if (menu_soft_id != null && !menu_soft_id.equals("")) {
      menu_soft  = menuRepository.findById(Integer.parseInt(menu_soft_id));
      profile.setMenu_soft(menu_soft);
    }
    if (menu_normal_id != null && !menu_normal_id.equals("")) {
      menu_normal = menuRepository.findById(Integer.parseInt(menu_normal_id));
      profile.setMenu_normal(menu_normal);
    }
	 
    if (provider_id != null && !provider_id.equals("")) {
      provider = profileRepository.findById(Integer.parseInt(provider_id));
      profile.setProvider(provider);      
    }

    if (profile.getId() == 0) {

      if (profile.getRole() == null || profile.getRole().equals("")) {
        if (profile.getPassword() == null || profile.getPassword().equals("")) {
          profile.setRole("Caregiver");
        } else {
          profile.setRole("Member");
        }
      }

      if (profile.getStatus() == null || profile.getStatus().equals("")) {
        profile.setStatus("Pending");
      }

      if (profile.getPassword() != null && !profile.getPassword().equals("")) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
       /********************************************************************************
        Version 1.01
        ********************************************************************************/
        profile.setPassword_api(encryptor.encryptPassword(profile.getPassword()));
       /********************************************************************************/
      }

      if (profile.getRole() != null && profile.getRole().equals("Caregiver")) {
    	member = profileRepository.findById(Integer.parseInt(member_id));
        profile.getMembers().add(member); // Note: Ensure caregiver linked to member
      }

/********************************************************************************
      profile.setCreated(formattedLocalDateTime);
        // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/
      profile.setCreated(localDateTime);
        // Note: Using String data type instead is a temporary workaround

    } else {

      Profile existingRecord = new Profile();

      existingRecord = profileRepository.findById(profile.getId());

      if (profile.getPassword() != null && existingRecord.getPassword() != null && !profile.getPassword().equals(existingRecord.getPassword())) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
       /********************************************************************************
        Version 1.01
        ********************************************************************************/
        profile.setPassword_api(encryptor.encryptPassword(profile.getPassword()));
       /********************************************************************************/
      } // Note: Encode password if changed for existing profile

      if (profile.getRole() != null && profile.getRole().equals("Member")) {
          profile.setCaregivers(profileRepository.findByMember_Id(profile.getId()));
      }

      if (profile.getRole() != null && profile.getRole().equals("Caregiver")) {
      	member = profileRepository.findById(Integer.parseInt(member_id));
        if (profile.getMembers() != null && !profile.getMembers().contains(member)) {
          profile.getMembers().add(member); // Note: // Ensure caregiver linked to member
        }
      }

/********************************************************************************
      profile.setUpdated(formattedLocalDateTime);
        // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/
      profile.setUpdated(localDateTime);
        // Note: Using String data type instead is a temporary workaround

      if (profile.getStatus() != null && profile.getStatus().equals("Active") && !existingRecord.getStatus().equals("Active")) {
        String email   = profile.getEmail();
        String subject = "MerryMeal's Meals on Wheels (MOW): Registration Approved";
        String message = String.format("%s %s %s \n\n",
                           "Your registration, as", profile.getRole(), "for MerryMeal's Meals on Wheels (MOW), has been approved.")
                       + String.format("%s \n\n",
                           "You can start using the MOW application through login with the email address and password.");
        Boolean outcome = false;
        outcome = this.sendEmail(email, subject, message);
      }

    }

    profile = profileRepository.saveAndFlush(profile);

    if (profile.getRole() != null && profile.getRole().equals("Caregiver")) {
      if (member.getCaregivers() != null && !member.getCaregivers().contains(profile)) {
        member.getCaregivers().add(profile); // Note: Ensure member linked to caregiver
        member = profileRepository.saveAndFlush(member);
      }
    }

    return profile;

  }


  public Profile deleteCaregiverById(String id, String member_id) {

    Profile caregiver = new Profile();

    Profile member    = new Profile();

    caregiver = profileRepository.findById(Integer.parseInt(id));

    member    = profileRepository.findById(Integer.parseInt(member_id));

    caregiver.getMembers().remove(member);  // Note: Ensure caregiver unlinked from member

    member.getCaregivers().remove(caregiver); // Note: Ensure member unlinked from caregiver

    caregiver = profileRepository.saveAndFlush(caregiver);
    
    member    = profileRepository.saveAndFlush(member);

    return caregiver;

  }


  public Profile findCaregiverById(String id) {

    Profile caregiver = new Profile();

    caregiver = profileRepository.findById(Integer.parseInt(id));

    return caregiver;

  }


  // Note: Following services for Registration, Evaluation and Pickups controller


  public List<Profile> findCaregivers(String member_id) {

    List<Profile> caregivers = new ArrayList<>();

    caregivers = profileRepository.findByMember_Id(Integer.parseInt(member_id));

    return caregivers;

  }

  public Page<Profile> findCaregivers(String member_id, int page, int size) {

    Page<Profile> items = null;

    items = profileRepository.findByMember_Id(Integer.parseInt(member_id), PageRequest.of(page, size));

    return items;

  }


  // Note: Following services for Registration controller


  public Profile findProfileByEmail(String username) {

    Profile profile = new Profile();

    profile = profileRepository.findByEmail(username);

    return profile;

  }


  public List<Profile> findProviders() {

    List<Profile> providers = new ArrayList<>();

    List<String> roles = new ArrayList<>();
    roles.add("Partner - FSP");
    roles.add("Volunteer - FSP");

    providers = profileRepository.findByStatusAndRoleIn("Active", roles,
                                                        Sort.by(Sort.Direction.ASC, "names"));

    return providers;

  }


  public Profile findCaregiverByNric(String nric) {

    Profile caregiver = new Profile();

    caregiver = profileRepository.findByNric_Uen(nric.toUpperCase());

    return caregiver;

  }

 
  // Note: Following services for Evaluation controller


  public List<Profile> findMembers() {

    List<Profile> members = new ArrayList<>();

    List<String> roles    = new ArrayList<>();
    roles.add("Member");

    members = profileRepository.findByRoleIn(roles,
                                             Sort.by(Sort.Direction.ASC, "names"));

    return members;

  }

  public Page<Profile> findMembers(int page, int size) {

    Page<Profile> items = null;

    List<String> roles  = new ArrayList<>();
    roles.add("Member");

    items = profileRepository.findByRoleIn(roles,
                                           PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "names")));

    return items;

  }


  public List<Profile> findPartners() {

    List<Profile> partners = new ArrayList<>();

    List<String> roles     = new ArrayList<>();
    roles.add("Partner - FSP");
    roles.add("Partner - FDR");

    partners = profileRepository.findByRoleIn(roles,
                                              Sort.by(Sort.Direction.ASC, "names"));

    return partners;

  }

  public Page<Profile> findPartners(int page, int size) {

    Page<Profile> items = null;

    List<String> roles  = new ArrayList<>();
    roles.add("Partner - FSP");
    roles.add("Partner - FDR");

    items = profileRepository.findByRoleIn(roles,
                                           PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "names")));

    return items;

  }


  public List<Profile> findVolunteers() {

    List<Profile> volunteers = new ArrayList<>();

    List<String> roles       = new ArrayList<>();
    roles.add("Volunteer - FSP");
    roles.add("Volunteer - FDR");

    volunteers = profileRepository.findByRoleIn(roles,
                                                Sort.by(Sort.Direction.ASC, "names"));

    return volunteers;

  }

  public Page<Profile> findVolunteers(int page, int size) {

    Page<Profile> items = null;

    List<String> roles  = new ArrayList<>();
    roles.add("Volunteer - FSP");
    roles.add("Volunteer - FDR");

    items = profileRepository.findByRoleIn(roles,
                                           PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "names")));

    return items;

  }


  public Profile deleteProfileById(String id) {

    Profile profile = new Profile();

    LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

/********************************************************************************
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedLocalDateTime = localDateTime.format(formatter);
      // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/

    profile = profileRepository.findById(Integer.parseInt(id));

    profile.setStatus("Deleted");

/********************************************************************************
    profile.setUpdated(formattedLocalDateTime);
      // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/
    profile.setUpdated(localDateTime);
      // Note: Using String data type instead is a temporary workaround

    profile = profileRepository.saveAndFlush(profile);

    return profile;

  }


  public Profile findProfileById(String id) {

    Profile profile = new Profile();

    profile = profileRepository.findById(Integer.parseInt(id));

    return profile;

  }
  

  // Note: Following services for Access controller


  public Profile findProfileByEmailAndPassword(String username, String password) {

    Profile profile = new Profile();

    profile = profileRepository.findByEmailAndPassword(username, password);

    return profile;

  }


  // Note: Following services for Pickups controller


  public List<Profile> findRiders() {

    List<Profile> riders = new ArrayList<>();

    List<String> roles   = new ArrayList<>();
    roles.add("Partner - FDR");
    roles.add("Volunteer - FDR");

    riders = profileRepository.findByStatusAndRoleIn("Active", roles,
                                                     Sort.by(Sort.Direction.ASC, "names"));

    return riders;

  }


  // Note: Following services for Home controller

  
  public long countMembers() {

    long count = 0;

    List<String> roles = new ArrayList<>();
    roles.add("Member");
    
    count = profileRepository.countByStatusAndRoleIn("Active", roles);

    return count;

  }


  public long countMembersByDiet(String diet) {

    long count = 0;

    List<String> roles = new ArrayList<>();
    roles.add("Member");

    count = profileRepository.countByDietAndStatusAndRoleIn(diet, "Active", roles);

    return count;

  }


  public long countCaregivers() {

    long count = 0;

    List<String> roles = new ArrayList<>();
    roles.add("Caregiver");

    count = profileRepository.countByStatusAndRoleIn("Active", roles);

    return count;

  }


  public long countPartners() {

    long count = 0;

    List<String> roles = new ArrayList<>();
    roles.add("Partner - FSP");
    roles.add("Partner - FDR");
	    
    count = profileRepository.countByStatusAndRoleIn("Active", roles);

    return count;

  }


  public long countPartnersByRole(String role) {

    long count = 0;

    List<String> roles = new ArrayList<>();
    if (role.equals("Provider")) {
      roles.add("Partner - FSP");
    } else if (role.equals("Rider")){
      roles.add("Partner - FDR");
    }
	    
    count = profileRepository.countByStatusAndRoleIn("Active", roles);

    return count;

  }


  public long countVolunteers() {

    long count = 0;

    List<String> roles = new ArrayList<>();
    roles.add("Volunteer - FSP");
    roles.add("Volunteer - FDR");
		    
    count = profileRepository.countByStatusAndRoleIn("Active", roles);

    return count;

  }


  public long countVolunteersByRole(String role) {

    long count = 0;

    List<String> roles = new ArrayList<>();
    if (role.equals("Provider")) {
      roles.add("Volunteer - FSP");
    } else if (role.equals("Rider")){
      roles.add("Volunteer - FDR");
    }
		    
    count = profileRepository.countByStatusAndRoleIn("Active", roles);

    return count;

  }


  public List<Profile> findMembersScheduled(int notice) {

    List<Profile> members = new ArrayList<>();

    members = profileRepository.findByScheduled(notice);

    return members;

  }


  public List<Profile> findMembersPending() {

    List<Profile> members = new ArrayList<>();

    List<String> roles = new ArrayList<>();
    roles.add("Member");

    members = profileRepository.findByStatusAndRoleIn("Pending", roles,
                                                      Sort.by(Sort.Direction.ASC, "names"));

    return members;

  }


  public List<Profile> findCaregiversPending() {

    List<Profile> caregivers = new ArrayList<>();

    List<String> roles = new ArrayList<>();
    roles.add("Caregiver");

    caregivers = profileRepository.findByStatusAndRoleIn("Pending", roles,
                                                         Sort.by(Sort.Direction.ASC, "names"));

    return caregivers;

  }


  public List<Profile> findPartnersPending() {

    List<Profile> partners = new ArrayList<>();

    List<String> roles = new ArrayList<>();
    roles.add("Partner - FSP");
    roles.add("Partner - FDR");

    partners = profileRepository.findByStatusAndRoleIn("Pending", roles,
                                                        Sort.by(Sort.Direction.ASC, "names"));

    return partners;

  }


  public List<Profile> findVolunteersPending() {

    List<Profile> volunteers = new ArrayList<>();

    List<String> roles = new ArrayList<>();
    roles.add("Volunteer - FSP");
    roles.add("Volunteer - FDR");

    volunteers = profileRepository.findByStatusAndRoleIn("Pending", roles,
                                                         Sort.by(Sort.Direction.ASC, "names"));

    return volunteers;

  }


 /********************************************************************************
  Version 1.01
  ********************************************************************************/
 
  public String generateToken(String email) {

    String issuer     = "merrymeal.org";
    String subject    = email;
    Date   now        = new Date();
    Date   expiration = new Date(now.getTime() + (1000 * 60 * 60)); // Note: Expires after 1 hour
    String secretKey  = "placeholder";

    return Jwts.builder()
               .setIssuer(issuer)
               .setSubject(subject)
               .setIssuedAt(now)                   
               .setExpiration(expiration)
               .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
               .compact();

  }


  public Boolean validateToken(String token, Profile profile) {

    String issuer    = "merrymeal.org";
    Date   now       = new Date();
    String secretKey = "placeholder";

    try {

      Claims claims = Jwts.parserBuilder()
                          .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                          .build()
                          .parseClaimsJws(token)
                          .getBody();

      if (!claims.getIssuer().equals(issuer)) {
        return false;
      }

      if (profile != null && !claims.getSubject().equals(profile.getEmail())) {
        return false;
      }

      if (claims.getIssuedAt().getTime() > now.getTime()) {
        return false;
      }

      if (claims.getExpiration().getTime() < now.getTime()) {
        return false;
      }

      return true;

    } catch(Exception e) {

   	  return false;

    }

  }


  public Boolean validateToken(String token) {

    return validateToken(token, null);

  }

 /********************************************************************************/



  // Note: Following methods within Profile service


  private boolean sendEmail(String address, String subject, String text) {

    // Note: Set default credentials for SMTP Host

    final String ACCOUNT  = "management.merrymeal@gmail.com";

    final String PASSWORD = "placeholder placeholder placeholder placeholder";
      // Note: Set password generated for MOW app under Gmail's 2-Step Verification
      //       instead of "placeholder" for login

    // Note: Set Authenticator object with account and password

    Authenticator authenticator = new Authenticator() {

      protected PasswordAuthentication getPasswordAuthentication() {

        // Note: Try scanning account and password files

//      File accountFile           = null;
//      Scanner accountScanner     = null;
        InputStream accountStream  = null;
        String accountLine         = null;

//      File passwordFile          = null;
//      Scanner passwordScanner    = null;
        InputStream passwordStream = null;
        String passwordLine        = null;

        try {

//        accountFile     = new ClassPathResource("config/account.txt").getFile();
//        accountScanner  = new Scanner(accountFile);

//        if (accountScanner.hasNextLine()) {
//          accountLine     = accountScanner.nextLine();
//        }

          accountStream = new ClassPathResource("config/account.txt").getInputStream();
            // Note: Switched to this technique which also works for WAR packaging

          accountLine   = new String(accountStream.readAllBytes());
            // Note: Switched to this technique which also works for WAR packaging 

          System.out.println("Scanned account.txt in config: " + accountLine);
            // Note: May not print when this code is put in constructor
            //       so its code block need putting elsewhere and use other account if printing to troubleshoot

//        accountScanner.close();

//        passwordFile    = new ClassPathResource("config/password.txt").getFile();
//        passwordScanner = new Scanner(passwordFile);

//        if (passwordScanner.hasNextLine()) {
//          passwordLine    = passwordScanner.nextLine();
//        }

          passwordStream = new ClassPathResource("config/password.txt").getInputStream();
            // Note: Switched to this technique which also works for WAR packaging

          passwordLine   = new String(passwordStream.readAllBytes());
            // Note: Switched to this technique which also works for WAR packaging 

          System.out.println("Scanned password.txt in config: " + passwordLine);
            // Note: May not print when this code is put in constructor
            //       so its code block need putting elsewhere and use other password if printing to troubleshoot

//        passwordScanner.close();

        } catch(Exception e) {

          System.out.println(e.getMessage());
            // Note: May not print when this code is put in constructor
            //       so its code block need putting elsewhere if printing to troubleshoot

//        if (accountScanner != null) {
//          accountScanner.close();
//        }

//        if (passwordScanner != null) {
//          passwordScanner.close();
//        }

        }

        // Note: If successful use overriding credentials
        //                else use default credentials

        if ((accountLine  != null && !accountLine .equals(""))
         && (passwordLine != null && !passwordLine.equals(""))) {

          return new PasswordAuthentication(accountLine, passwordLine);

        } else {

          return new PasswordAuthentication(ACCOUNT, PASSWORD);

        }

      } // Note: Uses Polymorphism (Overriding)

    };

    // Note: Set SMTP Properties object with host URL, SSL protocol and 465 port

    Properties properties = new Properties();

    properties.setProperty("mail.smtp.host",                "smtp.gmail.com");

    properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    properties.setProperty("mail.smtp.socketFactory.port",  "465");

    properties.setProperty("mail.smtp.auth",                "true");
    properties.setProperty("mail.smtp.port",                "465");

    // Note: Set Session object with Properties and Authenticator objects

    Session session = Session.getDefaultInstance(properties, authenticator);

    try {

      // Note: Set MimeMessage object with Session object

      MimeMessage message = new MimeMessage(session);

      message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
      message.setSubject(subject);
      message.setText(text);
	    
      // Note: Send MimeMessage object

      Transport.send(message);

      System.out.println("250 2.0.0 OK - gsmtp");

      return true;

    } catch(Exception e) {

      System.out.println(e.getMessage());

      return false;

    }

  }



}
