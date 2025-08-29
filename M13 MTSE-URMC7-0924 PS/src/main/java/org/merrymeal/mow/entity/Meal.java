/*
 * Class Name:  Meal
 * Description: Entity
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.entity;


import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDateTime;
  // Note: Replacing java.sql.Timestamp;
import java.time.LocalDate;
  // Note: Replacing java.sql.Date;


@Entity
@Table(name = "meal")

@Data // Note: @Data consists of @ToString, @EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor

public class Meal {



  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private int id;


/********************************************************************************
  @Column(name = "Time_Avail", unique = false, nullable = true, length = 20)

  @Size(message = "Time Avail field should not be greater than 20 characters.", min = 0, max = 20)

  private String time_avail;
    // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/

  @Column(name = "Time_Avail", unique = false, nullable = true)
  
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

  private LocalDateTime time_avail;
    // Note: Using String data type instead is a temporary workaround


/********************************************************************************
  @Column(name = "Time_Start", unique = false, nullable = true, length = 20)

  @Size(message = "Time Start field should not be greater than 20 characters.", min = 0, max = 20)

  private String time_start;
    // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/

  @Column(name = "Time_Start", unique = false, nullable = true)

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

  private LocalDateTime time_start;
    // Note: Using String data type instead is a temporary workaround


/********************************************************************************
  @Column(name = "Time_End", unique = false, nullable = true, length = 20)

  @Size(message = "Time End field should not be greater than 20 characters.", min = 0, max = 20)

  private String time_end;
    // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/

  @Column(name = "Time_End", unique = false, nullable = true)

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

  private LocalDateTime time_end;
    // Note: Using String data type instead is a temporary workaround


  @Column(name = "Status", unique = false, nullable = false, length = 10)

  @NotEmpty(message = "Status field should not be empty.")
  @Size(message = "Status field should not be greater than 10 characters.", min = 0, max = 10)

  private String status;



  @ManyToOne
  @JoinColumn(name = "Menu_ID",     referencedColumnName = "ID")

  private Menu menu;


  @ManyToOne
  @JoinColumn(name = "Provider_ID", referencedColumnName = "ID")

  private Profile provider;


  @ManyToOne
  @JoinColumn(name = "Member_ID",   referencedColumnName = "ID")

  private Profile member;



  @OneToOne(mappedBy = "meal")

  private Pickup pickup;



}
