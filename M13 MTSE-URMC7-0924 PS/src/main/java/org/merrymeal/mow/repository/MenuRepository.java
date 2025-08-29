/*
 * Interface Name:  MenuRepository
 * Description: Repository
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.repository;


import org.merrymeal.mow.entity.Menu;

import org.merrymeal.mow.model.RepertoireDTO;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


@Repository

public interface MenuRepository extends JpaRepository<Menu, Integer> {



  @Query(value =
    "SELECT * " +
      "FROM menu " +
      "WHERE Diet = :Diet " +
        "AND Provider_ID = :Provider_ID " +
    "ORDER BY Seq_Day ASC, Seq_Time ASC",
    nativeQuery = true)
    // @Query used because List<Menu> findByDietAndProvider_Id(String diet, String provider_id, Sort sort)
    //   cannot handle Seq_Day and Seq_Time column names
 
  List<Menu> findByDietAndProvider_Id(@Param("Diet") String diet, @Param("Provider_ID") int provider_id);
    // Note: For Menu service

  @Query(value =
    "SELECT * " +
      "FROM menu " +
      "WHERE Diet = :Diet " +
        "AND Provider_ID = :Provider_ID " +
    "ORDER BY Seq_Day ASC, Seq_Time ASC",
    nativeQuery = true)
    // @Query used because List<Menu> findByDietAndProvider_Id(String diet, String provider_id, Sort sort)
    //   cannot handle Seq_Day and Seq_Time column names
		 
  Page<Menu> findByDietAndProvider_Id(@Param("Diet") String diet, @Param("Provider_ID") int provider_id, Pageable pageable);
    // Note: For Menu service


  Menu findById(int id);
    // Note: For Profile service
    // Note: For Menu service


  Menu saveAndFlush(Menu menu);
    // Note: For Menu service


  @Query(value =
    "SELECT p.NRIC_UEN AS nric_uen, p.Names AS names, p.Surname AS surname, m.Diet AS criterion, COUNT(m.Name) AS count " +
      "FROM profile p JOIN menu m " +
        "ON p.ID = m.Provider_ID " +
     "WHERE p.Status = 'Active' " +
       "AND m.Status = 'Active' " +
       "AND m.Diet = :Diet " +
     "GROUP BY p.NRIC_UEN, p.Names, p.Surname, m.Diet",
     nativeQuery = true)
  // @Query is on menu-specific repertoire hence put in menu repository 

  List<RepertoireDTO> groupByDietAndProvider(@Param("Diet") String diet);
    // Note: For Menu service



}
