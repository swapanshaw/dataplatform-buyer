/*
 * Copyright 2015, Optimal Payments PLC, 2 Place Alexis Nihon, suite 700, Westmount, Quebec, Canada
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of Optimal Payments PLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with Optimal
 * Payments.
 */

package com.hypergrid.dataplatform.buyer.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SwaggerController {

  @RequestMapping("/")
  public ModelAndView home() {
    return new ModelAndView("redirect:/swagger-ui.html");
  }

}
