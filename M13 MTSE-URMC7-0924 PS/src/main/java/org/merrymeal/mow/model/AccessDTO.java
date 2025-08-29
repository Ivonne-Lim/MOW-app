/*
 * Class Name:  AccessDTO
 * Description: Model
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Release with partial mobile app         Ivonne Lim  04-Sep-2025
 */


package org.merrymeal.mow.model;


import org.merrymeal.mow.entity.Profile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data // Note: @Data consists of @ToString, @EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor

public class AccessDTO {

  private Profile profile;

  private String token;

} // Note: Data Transfer Object (DTO) for @RequestBody in REST API
