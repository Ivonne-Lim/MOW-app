/*
 * Interface Name:  PickupRepository
 * Description: Repository
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.repository;


import org.merrymeal.mow.entity.Pickup;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


@Repository

public interface PickupRepository extends JpaRepository<Pickup, Integer> {


  @Query (value =
    "SELECT * " +
      "FROM pickup " +
     "WHERE Status IN :Statuses " +
       "AND Rider_ID = :Rider_ID " +
    "ORDER BY Time_End ASC",
    nativeQuery = true)
    // @Query used because List<Pickup> findByRider_IdAndStatusIn(int rider_id, List<String> statuses)
    //   cannot handle Time_End column name

  List<Pickup> findByRider_IdAndStatusIn(@Param("Rider_ID") int rider_id, @Param("Statuses") List<String> statuses);
    // Note: For Pickup service

  @Query (value =
    "SELECT * " +
      "FROM pickup " +
     "WHERE Status IN :Statuses " +
       "AND Rider_ID = :Rider_ID " +
    "ORDER BY Time_End ASC",
    nativeQuery = true)
    // @Query used because List<Pickup> findByRider_IdAndStatusIn(int rider_id, List<String> statuses)
    //   cannot handle Time_End column name

  Page<Pickup> findByRider_IdAndStatusIn(@Param("Rider_ID") int rider_id, @Param("Statuses") List<String> statuses, Pageable pageable);
    // Note: For Pickup service


  @Query(value =
    "SELECT * " +
      "FROM pickup " +
     "WHERE Status IN :Statuses " +
       "AND Meal_ID IN ( " +
         "SELECT ID " +
           "FROM meal " +
          "WHERE Member_ID = :Member_ID ) " +
    "ORDER BY Time_Accept ASC",
    nativeQuery = true)

  List<Pickup> findByMember_IdAndStatusIn(@Param("Member_ID") int member_id, @Param("Statuses") List<String> statuses);
    // Note: For Pickup service

  @Query(value =
    "SELECT * " +
      "FROM pickup " +
     "WHERE Status IN :Statuses " +
       "AND Meal_ID IN ( " +
         "SELECT ID " +
           "FROM meal " +
          "WHERE Member_ID = :Member_ID ) " +
    "ORDER BY Time_Accept ASC",
    nativeQuery = true)

  Page<Pickup> findByMember_IdAndStatusIn(@Param("Member_ID") int member_id, @Param("Statuses") List<String> statuses, Pageable pageable);
    // Note: For Pickup service


  Pickup saveAndFlush(Pickup pickup);
    // Note: For Pickup service


  Pickup findById(int id);
    // Note: For Pickup service
    // Note: For Feedback service


  long countByStatusIn(List<String> statuses);
    // Note: For Pickup service



}
