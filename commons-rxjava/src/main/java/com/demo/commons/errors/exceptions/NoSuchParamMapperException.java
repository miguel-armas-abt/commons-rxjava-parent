package com.demo.commons.errors.exceptions;

import com.demo.commons.constants.Symbol;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class NoSuchParamMapperException extends GenericException {

  private static final String EXCEPTION_CODE = "00.00.04";

  public NoSuchParamMapperException(Class<?> mapperClass) {
    super(
        EXCEPTION_CODE,
        "No such implementation of param mapper" + Symbol.COLON_WITH_SPACE + mapperClass.getName(),
        INTERNAL_SERVER_ERROR
    );
  }
}
