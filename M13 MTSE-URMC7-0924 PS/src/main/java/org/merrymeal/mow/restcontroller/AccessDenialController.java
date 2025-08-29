/*
 * Class Name:  AccesssDenialController
 * Description: RestController
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow.restcontroller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;


@RestController

public class AccessDenialController {



  @GetMapping("/access_denial")

  public ModelAndView showAccessDenial() {

    ModelAndView model = new ModelAndView("access_denial");
    model.addObject("dateTime", LocalDateTime.now());
    return model;

  }



}
