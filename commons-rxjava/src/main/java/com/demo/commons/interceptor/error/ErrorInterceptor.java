package com.demo.commons.interceptor.error;

import com.demo.commons.errors.dto.ErrorDto;
import com.demo.commons.errors.exceptions.GenericException;
import com.demo.commons.errors.selector.ResponseErrorSelector;
import com.demo.commons.logging.ErrorThreadContextInjector;
import com.demo.commons.logging.enums.LoggingType;
import com.demo.commons.properties.ConfigurationBaseProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorInterceptor extends ResponseEntityExceptionHandler {

  private final ErrorThreadContextInjector contextInjector;
  private final ConfigurationBaseProperties properties;
  private final ResponseErrorSelector responseErrorSelector;

  @ExceptionHandler({Throwable.class})
  public ResponseEntity<ErrorDto> handleException(Throwable ex, WebRequest request) {
    generateTrace(ex, request);

    ErrorDto error = ErrorDto.getDefaultError(properties);
    HttpStatusCode httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    if (ex instanceof ResourceAccessException || ex instanceof ConnectException) {
      httpStatus = HttpStatus.REQUEST_TIMEOUT;
    }

    if (ex instanceof GenericException genericException) {
      error = responseErrorSelector.toErrorDTO(genericException);
      httpStatus = genericException.getHttpStatus();
    }

    return new ResponseEntity<>(error, httpStatus);
  }

  //jakarta validations
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                HttpStatusCode status, WebRequest request) {
    generateTrace(ex, request);
    ErrorDto error = responseErrorSelector.toErrorDTO(ex);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  private void generateTrace(Throwable ex, WebRequest request) {
    if (properties.isLoggerPresent(LoggingType.ERROR)) {
      contextInjector.populateFromException(ex, request);
    }
  }
}