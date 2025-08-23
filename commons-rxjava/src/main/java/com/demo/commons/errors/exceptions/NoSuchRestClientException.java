package com.demo.commons.errors.exceptions;

import com.demo.commons.constants.Symbol;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class NoSuchRestClientException extends GenericException {

  private static final String EXCEPTION_CODE = "00.00.06";

  public NoSuchRestClientException(String serviceName) {
    super(
        EXCEPTION_CODE,
        "No such rest client in properties" + Symbol.COLON_WITH_SPACE + serviceName,
        INTERNAL_SERVER_ERROR
    );
  }
}