/*
 * Class Name:  Feedback
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
@Table(name = "feedback")

@Data // Note: @Data consists of @ToString, @EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor

public class Feedback {



  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private int id;


  @Column(name = "Rate_Meal", unique = false, nullable = false)

  private int rate_meal;


  @Column(name = "Remarks_Meal", unique = false, nullable = true, length = 255)

  @Size(message = "Remarks Meal field should not be greater than 255 characters.", min = 0, max = 255)

  private String remarks_meal;


  @Column(name = "Rate_Delivery", unique = false, nullable = false)

  private int rate_delivery;


  @Column(name = "Remarks_Delivery", unique = false, nullable = true, length = 255)

  @Size(message = "Remarks Delivery field should not be greater than 255 characters.", min = 0, max = 255)

  private String remarks_delivery;


  @Column(name = "Status", unique = false, nullable = false, length = 10)

  @NotEmpty(message = "Status field should not be empty.")
  @Size(message = "Status field should not be greater than 10 characters.", min = 0, max = 10)

  private String status;



  @ManyToOne
  @JoinColumn(name = "Member_ID", referencedColumnName = "ID")

  private Profile member;


  @ManyToOne
  @JoinColumn(name = "Pickup_ID", referencedColumnName = "ID")

  private Pickup pickup;



}
