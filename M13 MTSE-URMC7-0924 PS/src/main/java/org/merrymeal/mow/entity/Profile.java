/*
 * Class Name:  Profile
 * Description: Entity
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 * 1.01     Release with partial mobile app         Ivonne Lim  04-Sep-2025
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

/********************************************************************************
 Version 1.01
 ********************************************************************************/
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id")
/********************************************************************************/

@Entity
@Table(name = "profile")

@Data // Note: @Data consists of @ToString, @EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor

public class Profile {



  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private int id;


  @Column(name = "NRIC_UEN", unique = true, nullable = false, length = 10)

  @NotEmpty(message = "NRIC / UEN field should not be empty.")
  @Size(message = "NRIC / UEN field should not be greater than 10 characters.", min = 0, max = 10)
  @Pattern(message = "NRIC / UEN field should only contain alphanumeric.", regexp = "^[A-Za-z0-9]{0,10}$")

  private String nric_uen;


  @Column(name = "Names", unique = false, nullable = false, length = 80)

  @NotEmpty(message = "Names field should not be empty.")
  @Size(message = "Names field should not be greater than 80 characters.", min = 0, max = 80)

  private String names;


  @Column(name = "Surname", unique = false, nullable = true, length = 40)

  @Size(message = "Surname field should not be greater than 40 characters.", min = 0, max = 40)

  private String surname; // Note: Optional for Partner profile


  @Column(name = "Gender", unique = false, nullable = true, length = 6)

  @Size(message = "Gender field should not be greater than 6 characters.", min = 0, max = 6)

  private String gender;  // Note: Optional for Partner profile


  @Column(name = "Role", unique = false, nullable = false, length = 16)

  @NotEmpty(message = "Role field should not be empty.")
  @Size(message = "Role field should not be greater than 16 characters.", min = 0, max = 16)

  private String role;


  @Column(name = "Email", unique = true, nullable = false, length = 80)

  @NotEmpty(message = "Email field should not be empty.")
  @Size(message = "Email field should not be greater than 80 characters.", min = 0, max = 80)
  @Email(message = "Email field should be valid.")

  private String email;


  @Column(name = "Phone", unique = false, nullable = false, length = 10)

  @NotEmpty(message = "Phone field should not be empty.")
  @Size(message = "Phone field should not be greater than 10 characters.", min = 0, max = 10)

  private String phone;


  @Column(name = "Address", unique = false, nullable = false, length = 255)

  @NotEmpty(message = "Address field should not be empty.")
  @Size(message = "Address field should not be greater than 255 characters.", min = 0, max = 255)

  private String address;


  @Column(name = "Unit", unique = false, nullable = true, length = 10)

  @Size(message = "Unit field should not be greater than 10 characters.", min = 0, max = 10)

  private String unit;


  @Column(name = "Postal", unique = false, nullable = false, length = 6)

  @NotEmpty(message = "Postal field should not be empty.")
  @Size(message = "Postal field should not be greater than 6 characters.", min = 0, max = 6)
  @Pattern(message = "Postal field should only contain digits.", regexp = "^[0-9]{0,6}$")

  private String postal;


  @Column(name = "Region", unique = false, nullable = true, length = 10)

  @Size(message = "Region field should not be greater than 10 characters.", min = 0, max = 10)

  private String region; // Note: Alternative technique to Geocoding API
                         //       Also used as PrintService index


  @Column(name = "Status", unique = false, nullable = false, length = 10)

  @NotEmpty(message = "Status field should not be empty.")
  @Size(message = "Status field should not be greater than 10 characters.", min = 0, max = 10)

  private String status;


  @Column(name = "Notes", unique = false, nullable = true, length = 255)

  @Size(message = "Notes field should not be greater than 255 characters.", min = 0, max = 255)

  private String notes;


/********************************************************************************
  @Column(name = "Created", unique = false, nullable = true, length = 20)

  @Size(message = "Created field should not be greater than 20 characters.", min = 0, max = 20)

  private String created;
    // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/

  @Column(name = "Created", unique = false, nullable = true)

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

  private LocalDateTime created;
    // Note: Using String data type instead is a temporary workaround
 

/********************************************************************************
  @Column(name = "Updated", unique = false, nullable = true, length = 20)

  @Size(message = "Updated field should not be greater than 20 characters,", min = 0, max = 20)

  private String updated;
    // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/

  @Column(name = "Updated", unique = false, nullable = true)

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

  private LocalDateTime updated;
    // Note: Using String data type instead is a temporary workaround


/********************************************************************************
  @Column(name = "Scheduled", unique = false, nullable = true, length = 20)

  @Size(message = "Scheduled field should not be greater than 20 characters.", min = 0, max = 20)

  private String scheduled; // Note: Applicable to Member role
    // Note: Using Timestamp data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/

  @Column(name = "Scheduled", unique = false, nullable = true)

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

  private LocalDateTime scheduled;
    // Note: Using String data type instead is a temporary workaround


/********************************************************************************
  @Column(name = "DOB", unique = false, nullable = true, length = 10)

  @Size(message = "DOB field should not be greater than 10 characters.", min = 0, max = 20)

  private String dob; // Note: Applicable to Member role
    // Note: Using Date data type instead will cause binding mismatch in ModelAttribute
 ********************************************************************************/

  @Column(name = "DOB", unique = false, nullable = true)

  @DateTimeFormat(pattern = "yyyy-MM-dd")

  private LocalDate dob;
    // Note: Using String data type instead is a temporary workaround


  @Column(name = "Disabilities", unique = false, nullable = true, length = 255)

  @Size(message = "Disabilities field should not be greater than 255 characters.", min = 0, max = 255)

  private String disabilities; // Note: Applicable to Member role


  @Column(name = "Income", unique = false, nullable = true)

  private int income; // Note: Applicable to Member role


  @Column(name = "Household", unique = false, nullable = true)

  private int household; // Note: Applicable to Member role


  @Column(name = "File1", unique = false, nullable = true, length = 255)

  @Size(message = "File1 field should not be greater than 255 characters.", min = 0, max = 255)

  private String file1; // Note: Applicable to Member role


  @Column(name = "File2", unique = false, nullable = true, length = 255)

  @Size(message = "File2 field should not be greater than 255 characters.", min = 0, max = 255)

  private String file2; // Note: Applicable to Member role


  @Column(name = "File3", unique = false, nullable = true, length = 255)

  @Size(message = "File3 field should not be greater than 255 characters.", min = 0, max = 255)

  private String file3; // Note: Applicable to Member role


  @Column(name = "Password", unique = false, nullable = true, length = 64)

  @Size(message = "Password field should not be greater than 64 characters,", min = 0, max = 64)

  private String password;


  @Column(name = "Diet", unique = false, nullable = true, length = 10)

  @Size(message = "Diet field should not be greater than 10 characters.", min = 0, max = 10)

  private String diet; // Note: Applicable to Member role


  @Column(name = "Allergies", unique = false, nullable = true, length = 255)

  @Size(message = "Allergies field should not be greater than 255 characters.", min = 0, max = 255)

  private String allergies; // Note: Applicable to Member role


/********************************************************************************
 Version 1.01
 ********************************************************************************/

  @Column(name = "Password_API", unique = false, nullable = true, length = 64)

  @Size(message = "Password (API) field should not be greater than 64 characters,", min = 0, max = 64)

  private String password_api;

 /********************************************************************************/



  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Menu_Halal_ID",  referencedColumnName = "ID")

  private Menu menu_halal; // Note: Applicable to Provider role


  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Menu_Veg_ID",    referencedColumnName = "ID")

  private Menu menu_veg; // Note: Applicable to Provider role


  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Menu_Soft_ID",   referencedColumnName = "ID")

  private Menu menu_soft; // Note: Applicable to Provider role


  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Menu_Normal_ID", referencedColumnName = "ID")

  private Menu menu_normal; // Note: Applicable to Provider role



  @ManyToOne
  @JoinColumn(name = "Provider_ID", referencedColumnName = "ID")

  private Profile provider; // Note: Applicable to Rider role



  @OneToMany(mappedBy = "provider")

  private List<Profile> riders      = new ArrayList<>(); // Note: Applicable to Provider role


  @OneToMany(mappedBy = "provider")

  private List<Menu> menus          = new ArrayList<>(); // Note: Applicable to Provider role


  @OneToMany(mappedBy = "provider")

  private List<Meal> meals_provider = new ArrayList<>(); // Note: Applicable to Provider role


  @OneToMany(mappedBy = "member")

  private List<Meal> meals_member   = new ArrayList<>(); // Note: Applicable to Member role


  @OneToMany(mappedBy = "rider")

  private List<Pickup> pickups      = new ArrayList<>(); // Note: Applicable to Rider role


  @OneToMany(mappedBy = "member")

  private List<Feedback> feedbacks  = new ArrayList<>(); // Note: Applicable to Member role



  @ManyToMany
  @JoinTable(name = "caregiver_member", joinColumns = @JoinColumn(name = "Member_ID"), inverseJoinColumns = @JoinColumn(name = "Caregiver_ID"))

  private List<Profile> caregivers = new ArrayList<>(); // Note: Applicable to Member role


  @ManyToMany(mappedBy = "caregivers")

  private List<Profile> members    = new ArrayList<>(); // Note: Applicable to Caregiver role



}
