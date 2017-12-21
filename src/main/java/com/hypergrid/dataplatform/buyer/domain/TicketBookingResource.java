package com.hypergrid.dataplatform.buyer.domain;

import java.util.UUID;

import com.hypergrid.common.domain.Customer;

public class TicketBookingResource {

  private String guid;
  private String ticketType;
  private double price;
  private Customer customer;

  public TicketBookingResource() {
    this.guid = UUID.randomUUID().toString();
  }

  public String getTicketType() {
    return ticketType;
  }

  public void setTicketType(String ticketType) {
    this.ticketType = ticketType;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public String toString() {
    return "TicketBookingResource [guid=" + guid + ", ticketType=" + ticketType + ", price=" + price + ", customer="
        + customer + "]";
  }
}
