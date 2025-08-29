/*
 * Class Name:  FeedbackService
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

import org.merrymeal.mow.repository.ProfileRepository;
import org.merrymeal.mow.repository.PickupRepository;
import org.merrymeal.mow.repository.FeedbackRepository;

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

public class FeedbackService {



  @Autowired
  private ProfileRepository  profileRepository;

  @Autowired
  private PickupRepository   pickupRepository;

  @Autowired
  private FeedbackRepository feedbackRepository;



  // Note: Following services for Feedback controller


  public List<Feedback> findSubmissions(String username) {

    Profile profile = new Profile();

    List<Feedback> submissions = new ArrayList<>();

    profile = profileRepository.findByEmail(username);

    if (profile.getRole() != null && profile.getRole().equals("Administrator")) {
      submissions = feedbackRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    } else if (profile.getRole() != null && profile.getRole().equals("Member")) {
      submissions = feedbackRepository.findByMember_Id(profile.getId(),
                                                       Sort.by(Sort.Direction.DESC, "id"));
    } else if (profile.getRole() != null && (profile.getRole().equals("Partner - FSP") || profile.getRole().equals("Volunteer - FSP"))) {
      submissions = feedbackRepository.findByProvider_Id(profile.getId());
    } else if (profile.getRole() != null && (profile.getRole().equals("Partner - FDR") || profile.getRole().equals("Volunteer - FDR"))) {
     submissions = feedbackRepository.findByRider_Id(profile.getId());
    }

    return submissions;

  }

  public Page<Feedback> findSubmissions(String username, int page, int size) {

   Profile profile = new Profile();

   Page<Feedback> items = null;

   profile = profileRepository.findByEmail(username);

   if (profile.getRole() != null && profile.getRole().equals("Administrator")) {
     items = feedbackRepository.findAll(PageRequest.of(page,  size, Sort.by(Sort.Direction.DESC, "id")));
   } else if (profile.getRole() != null && profile.getRole().equals("Member")) {
     items = feedbackRepository.findByMember_Id(profile.getId(),
                                                PageRequest.of(page,  size, Sort.by(Sort.Direction.DESC, "id")));
   } else if (profile.getRole() != null && (profile.getRole().equals("Partner - FSP") || profile.getRole().equals("Volunteer - FSP"))) {
     items = feedbackRepository.findByProvider_Id(profile.getId(), PageRequest.of(page,  size));
   } else if (profile.getRole() != null && (profile.getRole().equals("Partner - FDR") || profile.getRole().equals("Volunteer - FDR"))) {
     items = feedbackRepository.findByRider_Id(profile.getId(), PageRequest.of(page,  size));
   }

   return items;

 }


  public Feedback deleteSubmissionById(String id) {

    Feedback submission = new Feedback();

    submission = feedbackRepository.findById(Integer.parseInt(id));

    submission.setStatus("Deleted");

    submission = feedbackRepository.saveAndFlush(submission);

    return submission;

  }


  public Feedback findSubmissionById(String id) {

    Feedback submission = new Feedback();

    submission = feedbackRepository.findById(Integer.parseInt(id));

    return submission;

  }


  public Feedback saveSubmission(Feedback submission, String member_id, String pickup_id) {

    Profile member = new Profile();

    Pickup pickup  = new Pickup();

    member = profileRepository.findById(Integer.parseInt(member_id));
    submission.setMember(member);

    pickup = pickupRepository.findById(Integer.parseInt(pickup_id));
    submission.setPickup(pickup);

    submission = feedbackRepository.saveAndFlush(submission);
 
    return submission;

  }


  // Note: Following services for Home controller

  
  public long countSubmissions() {

    long count = 0;

    count = feedbackRepository.countByStatus("Active");

    return count;

  }


  public long countSubmissionsGood() {

    long count = 0;

    count = feedbackRepository.countByStatusAndGood("Active");

    return count;

  }


  public long countSubmissionsPoor() {

    long count = 0;

    count = feedbackRepository.countByStatusAndPoor("Active");

    return count;

  }



}
