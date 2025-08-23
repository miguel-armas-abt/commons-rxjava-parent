package com.demo.commons.errors.exceptions;

import com.demo.commons.errors.dto.ErrorDto;
import com.demo.commons.errors.dto.ErrorOrigin;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class RestClientException extends GenericException {

  public RestClientException(String code, String message, ErrorOrigin errorOrigin, HttpStatusCode httpStatusCode) {
    super(message);
    this.httpStatus = httpStatusCode;
    this.errorDetail = ErrorDto.builder()
        .origin(errorOrigin)
        .code(code)
        .message(message)
        .build();
  }
}
