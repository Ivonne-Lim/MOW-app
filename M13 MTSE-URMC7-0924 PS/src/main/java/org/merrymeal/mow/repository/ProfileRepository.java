/*
 * Interface Name:  ProfileRepository
 * Description: Repository
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.repository;


import org.merrymeal.mow.entity.Profile;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


@Repository

public interface ProfileRepository extends JpaRepository<Profile, Integer> {



  Profile findById(int id);
    // Note: For Profile service
    // Note: For Menu service
    // Note: For Pickup service
    // Note: For Feedback service


  Profile saveAndFlush(Profile profile);
    // Note: For Profile service


  @Query(value =
    "SELECT * " +
      "FROM profile " +
     "WHERE ID IN ( " +
       "SELECT Caregiver_ID " +
         "FROM caregiver_member " +
        "WHERE Member_ID = :Member_ID ) " +
    "ORDER BY Created ASC",
    nativeQuery = true)

  List<Profile> findByMember_Id(@Param("Member_ID") int member_id);
    // Note: For Profile service

  @Query(value =
    "SELECT * " +
      "FROM profile " +
     "WHERE ID IN ( " +
       "SELECT Caregiver_ID " +
         "FROM caregiver_member " +
        "WHERE Member_ID = :Member_ID ) " +
    "ORDER BY Created ASC",
    nativeQuery = true)

  Page<Profile> findByMember_Id(@Param("Member_ID") int member_id, Pageable pageable);
    // Note: For Profile service


  Profile findByEmail(String email);
    // Note: For Profile service
    // Note: For Menu service
    // Note: For Pickup service
    // Note: For Feedback service


  List<Profile> findByStatusAndRoleIn(String status, List<String> roles, Sort sort);
    // Note: For Profile service
    // Note: For Meal service


  @Query(value =
    "SELECT * " +
      "FROM profile " +
     "WHERE NRIC_UEN = :NRIC_UEN",
    nativeQuery = true)
  // @Query used because Profile findByNric_Uen(String nric_uen)
  //   cannot handle NRIC_UEN column name

  Profile findByNric_Uen(@Param("NRIC_UEN") String nric_uen);
    // Note: For Profile service


  List<Profile> findByRoleIn(List<String> roles, Sort sort);
    // Note: For Profile service

  Page<Profile> findByRoleIn(List<String> roles, Pageable pageable);
    // Note: For Profile service

  
  Profile findByEmailAndPassword(String email, String password);
    // Note: For Profile service


  List<Profile> findByDietAndStatusAndRoleIn(String diet, String status, List<String> roles, Sort sort);
    // Note: For Meal service


  long countByStatusAndRoleIn(String status, List<String> roles);
    // Note: For Profile service


  long countByDietAndStatusAndRoleIn(String diet, String status, List<String> roles);
    // Note: For Profile service


  @Query(value =
    "SELECT * " +
      "FROM profile " +
     "WHERE Role = 'Member' " +
       "AND Status = 'Active' " +
       "AND NOW() > (Scheduled - INTERVAL :Notice DAY) " +
     "ORDER BY Created ASC",
    nativeQuery = true)

  List<Profile> findByScheduled(@Param("Notice") int notice);
    // Note: For Profile service



}
