package com.hypergrid.dataplatform.buyer.service;

import com.hypergrid.common.domain.Receipt;
import com.hypergrid.common.exception.ApplicationBaseException;
import com.hypergrid.dataplatform.buyer.domain.TicketBookingResource;

public interface BuyerService {

  /**
   * buy ticket based on the the information provided in {@link TicketBookingResource}
   * 
   * @param ticketBookingResource {@link TicketBookingResource} booing info
   * @return receipt {@link Receipt} receipt of booking
   */
  public Receipt buyTicket(final TicketBookingResource ticketBookingResource) throws ApplicationBaseException;
}
