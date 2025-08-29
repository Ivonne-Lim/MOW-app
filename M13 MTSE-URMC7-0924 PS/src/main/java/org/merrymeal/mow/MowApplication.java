/*
 * Class Name:  MowApplication
 * Description: SpringBootApplication
 *
 * Version  Change                                  Programmer  Date
 * 1.00     Initial Release                         Ivonne Lim  28-Aug-2025
 */


package org.merrymeal.mow;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
  // Note: Extends   only for WAR packaging
import org.springframework.boot.builder.SpringApplicationBuilder;
  // Note: @Override only for WAR packaging


@SpringBootApplication

public class MowApplication extends SpringBootServletInitializer {



  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		  
    return builder.sources(MowApplication.class);
    
  }

	  
  public static void main(String[] args) {

    SpringApplication.run(MowApplication.class, args);

  }



}
