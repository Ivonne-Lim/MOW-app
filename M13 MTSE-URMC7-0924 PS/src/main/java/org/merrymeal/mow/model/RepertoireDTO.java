/*
 * Interface Name:  RepertoireDTO
 * Description: Model
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.model;


public interface RepertoireDTO {

  // Profile

  String getNric_uen();

  String getNames();

  String getSurname();

  // Repertoire

  String getCriterion();

  long getCount();

}
