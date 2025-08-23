package com.demo.commons.errors.exceptions;

import com.demo.commons.errors.dto.ErrorDto;
import com.demo.commons.errors.dto.ErrorOrigin;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class GenericException extends RuntimeException {

    protected ErrorDto errorDetail;
    protected HttpStatusCode httpStatus;

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorDetail = ErrorDto.builder()
            .origin(ErrorOrigin.OWN)
            .code(code)
            .message(message)
            .build();
    }
}
