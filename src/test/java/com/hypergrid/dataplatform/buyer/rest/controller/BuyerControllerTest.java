package com.hypergrid.dataplatform.buyer.rest.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hypergrid.common.domain.Customer;
import com.hypergrid.common.domain.Receipt;
import com.hypergrid.common.exception.BadRequestException;
import com.hypergrid.dataplatform.buyer.domain.TicketBookingResource;
import com.hypergrid.dataplatform.buyer.service.impl.BuyerServiceImpl;

public class BuyerControllerTest {

  private MockMvc mockMvc;

  @InjectMocks
  private BuyerController buyerController;

  @Mock
  private BuyerServiceImpl buyerServiceImpl;

  private Receipt receipt;

  private TicketBookingResource ticketBookigResource;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(buyerController).build();
  }

  private void init() {
    Customer customer = new Customer();
    customer.setCreditCardNumber("12345");
    customer.setCustomerName("Dummy name");
    receipt = new Receipt();
    receipt.setReceiptNumber("1234");
    receipt.setPrice(Double.valueOf(100.00d));
    receipt.setReceiptDate(new Date());
    receipt.setCustomer(customer);

    ticketBookigResource = new TicketBookingResource();
    ticketBookigResource.setCustomer(customer);
    ticketBookigResource.setPrice(Double.valueOf(100.00));
    ticketBookigResource.setTicketType("ticketType");
  }

  @After
  public void tearDown() throws Exception {
    receipt = null;
  }

  @Test
  public void test_ping_success() throws Exception {
    mockMvc.perform(get("/v1/buyer/ping")).andExpect(status().isOk());
  }

  @Test
  public void test_buyTicket_whenValidInput_success() throws Exception {
    when(buyerServiceImpl.buyTicket(any())).thenReturn(receipt);
    init();
    mockMvc.perform(post("/v1/buyer/buyTicket").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(asJsonString(ticketBookigResource))).andExpect(status().isOk());
  }

  @Test
  public void test_buyTicket_whenInvalidInput_badRequestResponse() throws Exception {
    when(buyerServiceImpl.buyTicket(any())).thenThrow(BadRequestException.class);
    mockMvc.perform(post("/v1/buyer/buyTicket").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(asJsonString(ticketBookigResource))).andExpect(status().isBadRequest());
  }

  public static String asJsonString(final Object obj) throws JsonProcessingException {
    final ObjectMapper mapper = new ObjectMapper();
    final String jsonContent = mapper.writeValueAsString(obj);
    System.out.println(jsonContent);
    return jsonContent;
  }

}
