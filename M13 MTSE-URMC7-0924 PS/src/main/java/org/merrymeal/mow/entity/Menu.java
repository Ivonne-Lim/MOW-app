/*
 * Class Name:  Menu
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
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"Seq_Day", "Seq_Time", "Diet", "Provider_ID"})})

@Data // Note: @Data consists of @ToString, @EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor

public class Menu {



  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private int id;


  @Column(name = "Seq_Day", unique = false, nullable = false)

  private int seq_day;


  @Column(name = "Seq_Time", unique = false, nullable = false)

  private int seq_time;


  @Column(name = "Name", unique = false, nullable = false, length = 20)

  @NotEmpty(message = "Name field should not be empty.")
  @Size(message = "Name field should not be greater than 20 characters.", min = 0, max = 20)

  private String name;


  @Column(name = "Diet", unique = false, nullable = false, length = 10)

  @NotEmpty(message = "Diet field should not be empty.")
  @Size(message = "Diet field should not be greater than 10 characters.", min = 0, max = 10)

  private String diet;


  @Column(name = "Frozen", unique = false, nullable = false, length = 3)

  @NotEmpty(message = "Frozen field should not be empty.")
  @Size(message = "Frozen field should not be greater than 3 characters.", min = 0, max = 3)

  private String frozen;


  @Column(name = "Status", unique = false, nullable = false, length = 10)

  @NotEmpty(message = "Status field should not be empty.")
  @Size(message = "Status field should not be greater than 10 characters.", min = 0, max = 10)

  private String status;



  @ManyToOne
  @JoinColumn(name = "Provider_ID", referencedColumnName = "ID")

  private Profile provider;



  @OneToMany (mappedBy = "menu")

  private List<Meal> meals = new ArrayList<>();



  @OneToOne(mappedBy = "menu_halal")

  private Profile provider_halal;


  @OneToOne(mappedBy = "menu_veg")

  private Profile provider_veg;


  @OneToOne(mappedBy = "menu_soft")

  private Profile provider_soft;


  @OneToOne(mappedBy = "menu_normal")

  private Profile provider_normal;



}
