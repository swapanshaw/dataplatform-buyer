package com.hypergrid.dataplatform.buyer.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hypergrid.common.exception.BadRequestException;
import com.hypergrid.common.exception.ExceptionResponse;

@ControllerAdvice
public class ExceptionHandlingController {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ExceptionResponse> resourceNotFound(BadRequestException ex) {
    ExceptionResponse response = new ExceptionResponse();
    response.setErrorCode(ex.getStatusCode());
    response.setErrorMessage(ex.getMessage());
    response.setErrors(ex.getErrors());
    return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
  }
}