package com.hypergrid.dataplatform.buyer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hypergrid.common.service.BuyerTicketService;

@Service
public class BuyerTicketServiceImpl implements BuyerTicketService {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Override
  public String setTicketStatus(String receiptNumber, String status) {
    logger.info("Seller service responded back with status {} for receipt number {}", status, receiptNumber);
    return status;
  }
}
