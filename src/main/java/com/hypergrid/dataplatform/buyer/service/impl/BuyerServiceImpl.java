package com.hypergrid.dataplatform.buyer.service.impl;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.hypergrid.common.domain.Customer;
import com.hypergrid.common.domain.Receipt;
import com.hypergrid.common.exception.ApplicationBaseException;
import com.hypergrid.common.exception.BadRequestException;
import com.hypergrid.common.service.SellerTicketService;
import com.hypergrid.dataplatform.buyer.domain.TicketBookingResource;
import com.hypergrid.dataplatform.buyer.service.BuyerService;

@Service
public class BuyerServiceImpl implements BuyerService {
  private static final Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);

  @Autowired
  private ApplicationContext context;

  @Override
  public Receipt buyTicket(final TicketBookingResource ticketBookingResource) throws ApplicationBaseException {
    SellerTicketService sellerService;
    Receipt receipt = null;
    final String ticketType = ticketBookingResource.getTicketType();
    final Double price = ticketBookingResource.getPrice();
    final Customer customer = ticketBookingResource.getCustomer();
    if (logger.isDebugEnabled()) {
      logger.debug("payload for buy ticket:  {} ", ticketBookingResource);
    }
    validateData(ticketType, price, customer);
    sellerService = context.getBean(SellerTicketService.class);
    receipt = sellerService.buyTicket(ticketType, price, customer);
    logger.info("response from rabbit mq {} ", receipt);
    return receipt;
  }

  private void validateData(final String ticketType, final Double price, final Customer customer) {
    if(StringUtils.isEmpty(ticketType) || price == null) {
      throw BadRequestException.getBuilder("invalid Inpur args")
          .status(4001)
          .fieldErrors(
              Arrays.asList("TicketType can not be null/empty", "price should be a numeric and mandatory field"))
          .build();
    } else if (customer == null) {
      throw BadRequestException.getBuilder("invalid Inpur args").status(4001)
          .fieldErrors(Arrays.asList("Please provide customer information")).build();
    }
  }

}
