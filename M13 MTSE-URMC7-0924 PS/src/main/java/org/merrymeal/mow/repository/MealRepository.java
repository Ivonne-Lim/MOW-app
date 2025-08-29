/*
 * Interface Name:  MealRepository
 * Description: Repository
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.repository;


import org.merrymeal.mow.entity.Meal;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


@Repository

public interface MealRepository extends JpaRepository<Meal, Integer> {



  @Query(value =
    "SELECT * " +
      "FROM meal " +
      "WHERE Status != 'Ended' " +
        "AND Status != 'Deleted' " +
        "AND Provider_ID = :Provider_ID " +
        "AND Menu_ID IN ( " +
          "SELECT ID " +
            "FROM menu " +
           "WHERE diet = :Diet ) " +
    "ORDER BY ID ASC",
    nativeQuery = true)

  List<Meal> findByDietAndProvider_Id(@Param("Diet") String diet, @Param("Provider_ID") int provider_id);
    // Note: For Meal service

  @Query(value =
    "SELECT * " +
      "FROM meal " +
      "WHERE Status != 'Ended' " +
        "AND Status != 'Deleted' " +
        "AND Provider_ID = :Provider_ID " +
        "AND Menu_ID IN ( " +
          "SELECT ID " +
            "FROM menu " +
           "WHERE diet = :Diet ) " +
    "ORDER BY ID ASC",
    nativeQuery = true)

  Page<Meal> findByDietAndProvider_Id(@Param("Diet") String diet, @Param("Provider_ID") int provider_id, Pageable pageable);
    // Note: For Meal service


  @Query(value =
    "SELECT * " +
      "FROM meal " +
      "WHERE Status != 'Ended' " +
        "AND Status != 'Deleted' " +
        "AND Member_ID = :Member_ID " +
        "AND Menu_ID IN ( " +
          "SELECT Id " +
            "FROM menu " +
           "WHERE diet = :Diet ) " +
    "ORDER BY ID ASC",
    nativeQuery = true)
  
  List<Meal> findByDietAndMember_Id(@Param ("Diet") String diet, @Param("Member_ID") int member_id);
    // Note: For Meal service

  @Query(value =
    "SELECT * " +
      "FROM meal " +
      "WHERE Status != 'Ended' " +
        "AND Status != 'Deleted' " +
        "AND Member_ID = :Member_ID " +
        "AND Menu_ID IN ( " +
          "SELECT Id " +
            "FROM menu " +
           "WHERE diet = :Diet ) " +
    "ORDER BY ID ASC",
    nativeQuery = true)
		  
  Page<Meal> findByDietAndMember_Id(@Param ("Diet") String diet, @Param("Member_ID") int member_id, Pageable pageable);
    // Note: For Meal service


  @Query(value =
    "SELECT * " +
      "FROM meal " +
      "WHERE Status != 'Ended' " +
        "AND Status != 'Deleted' " +
        "AND Menu_ID IN ( " +
          "SELECT ID " +
            "FROM menu " +
           "WHERE diet = :Diet ) " +
    "ORDER BY ID ASC",
    nativeQuery = true)
		  
  List<Meal> findByDiet(@Param ("Diet") String diet);
    // Note: For Meal service

  @Query(value =
    "SELECT * " +
      "FROM meal " +
      "WHERE Status != 'Ended' " +
        "AND Status != 'Deleted' " +
        "AND Menu_ID IN ( " +
          "SELECT ID " +
            "FROM menu " +
           "WHERE diet = :Diet ) " +
    "ORDER BY ID ASC",
    nativeQuery = true)
				  
  Page<Meal> findByDiet(@Param ("Diet") String diet, Pageable pageable);
    // Note: For Meal service


  Meal findById(int id);
    // Note: For Meal service
    // Note: For Pickup service


  Meal saveAndFlush(Meal meal);
    // Note: For Meal service


  @Query(value =
    "SELECT * " +
      "FROM meal " +
      "WHERE Status = :Status " +
        "AND Provider_ID = :Provider_ID " +
        "AND Id NOT IN ( " +
          "SELECT Meal_ID " +
            "FROM pickup ) " +
    "ORDER BY Time_End ASC",
    nativeQuery = true)

  List<Meal> findByStatusAndPickupAndProvider_Id(@Param("Status") String status, @Param("Provider_ID") int provider_id);
    // Note: For Pickup service

  @Query(value =
    "SELECT * " +
      "FROM meal " +
      "WHERE Status = :Status " +
        "AND Provider_ID = :Provider_ID " +
        "AND Id NOT IN ( " +
          "SELECT Meal_ID " +
            "FROM pickup ) " +
    "ORDER BY Time_End ASC",
    nativeQuery = true)

  Page<Meal> findByStatusAndPickupAndProvider_Id(@Param("Status") String status, @Param("Provider_ID") int provider_id, Pageable pageable);
    // Note: For Pickup service


  @Query(value =
    "SELECT * " +
      "FROM meal " +
     "WHERE Status = :Status " +
       "AND ID NOT IN ( " +
         "SELECT Meal_ID " +
           "FROM pickup ) " +
    "ORDER BY Time_End ASC",
    nativeQuery = true)

  List<Meal> findByStatusAndPickup(@Param("Status") String status);
    // Note: For Pickup service

  @Query(value =
    "SELECT * " +
      "FROM meal " +
     "WHERE Status = :Status " +
       "AND ID NOT IN ( " +
         "SELECT Meal_ID " +
           "FROM pickup ) " +
    "ORDER BY Time_End ASC",
    nativeQuery = true)

  Page<Meal> findByStatusAndPickup(@Param("Status") String status, Pageable pageable);
    // Note: For Pickup service


  long countByStatusIn(List<String> statuses);
    // Note: For Meal service


  @Query(value =
    "SELECT COUNT(*) " +
      "FROM meal " +
     "WHERE Status = :Status " +
       "AND ID NOT IN ( " +
         "SELECT Meal_ID " +
           "FROM pickup ) " +
    "ORDER BY Time_End ASC",
    nativeQuery = true)

  long countByStatusAndPickup(@Param("Status") String status);
    // Note: For Pickup service



}
