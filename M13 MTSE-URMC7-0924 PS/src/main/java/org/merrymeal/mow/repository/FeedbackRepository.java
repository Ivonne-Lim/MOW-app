/*
 * Interface Name:  FeedbackRepository
 * Description: Repository
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.repository;


import org.merrymeal.mow.entity.Feedback;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


@Repository

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {



  List<Feedback> findAll(Sort sort);
    // Note: For Feedback service

  Page<Feedback> findAll(Pageable pageable);
    // Note: For Feedback service


  List<Feedback> findByMember_Id(int id, Sort sort);
    // Note: For Feedback service

  Page<Feedback> findByMember_Id(int id, Pageable pageable);
    // Note: For Feedback service


  @Query(value =
    "SELECT * " +
      "FROM feedback " +
     "WHERE Status = 'Active' " +
       "AND Pickup_ID IN ( " +
         "SELECT ID " +
           "FROM pickup " +
           "WHERE Meal_ID IN ( " +
              "SELECT ID " +
                "FROM meal " +
               "WHERE Provider_ID = :ID ) ) " +
    "ORDER BY ID DESC", 
    nativeQuery = true)

  List<Feedback> findByProvider_Id(@Param("ID") int id);
    // Note: For Feedback service

  @Query(value =
    "SELECT * " +
      "FROM feedback " +
     "WHERE Status = 'Active' " +
       "AND Pickup_ID IN ( " +
         "SELECT ID " +
           "FROM pickup " +
           "WHERE Meal_ID IN ( " +
              "SELECT ID " +
                "FROM meal " +
               "WHERE Provider_ID = :ID ) ) " +
    "ORDER BY ID DESC", 
    nativeQuery = true)

  Page<Feedback> findByProvider_Id(@Param("ID") int id, Pageable pageable);
    // Note: For Feedback service


  @Query(value =
    "SELECT * " +
      "FROM feedback " +
     "WHERE Status = 'Active' " +
       "AND Pickup_ID IN ( " +
          "SELECT ID " +
            "FROM pickup " +
           "WHERE Rider_ID = :ID ) " +
    "ORDER BY ID DESC",
    nativeQuery = true)

  List<Feedback> findByRider_Id(@Param("ID") int id);
    // Note: For Feedback service

  @Query(value =
    "SELECT * " +
      "FROM feedback " +
     "WHERE Status = 'Active' " +
       "AND Pickup_ID IN ( " +
          "SELECT ID " +
            "FROM pickup " +
           "WHERE Rider_ID = :ID ) " +
    "ORDER BY ID DESC",
    nativeQuery = true)

  Page<Feedback> findByRider_Id(@Param("ID") int id, Pageable pageable);
    // Note: For Feedback service


  Feedback findById(int id);
    // Note: For Feedback service


  Feedback saveAndFlush(Feedback submission);
    // Note: For Feedback service


  long countByStatus(String status);
    // Note: For Feedback service


  @Query(value =
    "SELECT COUNT(*) " +
      "FROM feedback " +
     "WHERE Status = :Status " +
       "AND (Rate_Meal >= 3 " +
       "AND  Rate_Delivery >= 3)",
    nativeQuery = true)

  long countByStatusAndGood(@Param("Status") String status);
    // Note: For Feedback service


  @Query(value =
	"SELECT COUNT(*) " +
      "FROM feedback " +
     "WHERE Status = :Status " +
       "AND (Rate_Meal < 3 " +
        "OR  Rate_Delivery < 3)",
    nativeQuery = true)

  long countByStatusAndPoor(@Param("Status") String status);



}
