/*
 * Class Name:  LoginDTO
 * Description: Model
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Release with partial mobile app         Ivonne Lim  04-Sep-2025
 */


package org.merrymeal.mow.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data // Note: @Data consists of @ToString, @EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor

public class LoginDTO {

  private String email;

  private String password;

} // Note: Data Transfer Object (DTO) for @RequestBody in REST API
