package com.demo.commons.errors.exceptions;

import com.demo.commons.constants.Symbol;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class NoSuchRestClientErrorExtractorException extends GenericException {

  private static final String EXCEPTION_CODE = "00.00.05";

  public NoSuchRestClientErrorExtractorException(Class<?> errorWrapperClass) {
    super(
        EXCEPTION_CODE,
        "No such implementation of rest client error extractor" + Symbol.COLON_WITH_SPACE + errorWrapperClass.getName(),
        INTERNAL_SERVER_ERROR
    );
  }
}
