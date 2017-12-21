package com.hypergrid.dataplatform.buyer.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hypergrid.common.domain.Customer;
import com.hypergrid.common.domain.Receipt;
import com.hypergrid.common.exception.BadRequestException;
import com.hypergrid.common.service.SellerTicketService;
import com.hypergrid.dataplatform.buyer.domain.TicketBookingResource;
import com.hypergrid.dataplatform.buyer.service.impl.BuyerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class BuyerServiceImplTest {

  @Mock
  private ApplicationContext applicationContext;

  @Mock
  private SellerTicketService sellerTicketService;

  private Customer customer;

  private Receipt receipt;

  private TicketBookingResource resource;

  @Spy
  @InjectMocks
  BuyerServiceImpl BuyerServiceImpl;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void tearDown() throws Exception {
  }

  private void init() {
    customer = new Customer();
    customer.setCreditCardNumber("1234");
    customer.setCustomerName("dummy");

    receipt = new Receipt();
    receipt.setCustomer(customer);
    receipt.setReceiptNumber("1234");

    resource = new TicketBookingResource();
    resource.setTicketType("ticketType");
    resource.setPrice(100.00d);
    resource.setCustomer(customer);
  }

  @Test
  public void test_buyTicket_whenValidInput_success() {
    init();
    when(applicationContext.getBean(SellerTicketService.class)).thenReturn(sellerTicketService);
    when(sellerTicketService.buyTicket(anyString(), anyDouble(), any())).thenReturn(receipt);
    BuyerServiceImpl.buyTicket(resource);
    assertEquals("1234", receipt.getReceiptNumber());
  }

  @Test(expected = BadRequestException.class)
  public void test_buyTicket_whenInalidInput_throwBadRequestException() {
    init();
    resource.setTicketType(null);
    when(applicationContext.getBean(SellerTicketService.class)).thenReturn(sellerTicketService);
    when(sellerTicketService.buyTicket(anyString(), anyDouble(), any())).thenReturn(receipt);
    BuyerServiceImpl.buyTicket(resource);
  }

}
