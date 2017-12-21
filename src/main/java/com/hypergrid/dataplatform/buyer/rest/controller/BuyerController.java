package com.hypergrid.dataplatform.buyer.rest.controller;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hypergrid.common.domain.Receipt;
import com.hypergrid.common.exception.ApplicationBaseException;
import com.hypergrid.dataplatform.buyer.domain.TicketBookingResource;
import com.hypergrid.dataplatform.buyer.service.BuyerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * BuyerController {@link BuyerController} consists rest api related to ticket buying
 * 
 * @author swapanshaw
 *
 */
@RestController
@RequestMapping("/v1/buyer")
public class BuyerController {

  private static final Logger logger = LoggerFactory.getLogger(BuyerController.class);

  @Autowired
  private BuyerService BuyerService;

  @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Sample rest api to check service availibility")
  @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad Request")})
  @ResponseBody
  public ResponseEntity<String> greeting() throws InterruptedException {
    logger.info("Service is up and running.");
    return new ResponseEntity<String>(JSONObject.quote("Service is up and running"), HttpStatus.OK);
  }

  @RequestMapping(value = "/buyTicket", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON,
      consumes = MediaType.APPLICATION_JSON)
  @ApiOperation(value = "To search user details information")
  @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad Request")})
  @ResponseBody
  public ResponseEntity<Receipt> buyTicket(@RequestBody TicketBookingResource ticketBokingResource)
      throws ApplicationBaseException {
    logger.info("Buy ticket call is started for ticket type {} ", ticketBokingResource.getTicketType());
    final Receipt receipt = BuyerService.buyTicket(ticketBokingResource);
    return new ResponseEntity<Receipt>(receipt, HttpStatus.OK);
  }

}
