package com.demo.commons.restclient.error.extractor.poc;

import com.demo.commons.errors.dto.ErrorDto;
import com.demo.commons.restclient.error.RestClientErrorExtractor;
import com.demo.commons.restclient.error.RestClientErrorMapper;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public class DefaultErrorExtractor implements RestClientErrorExtractor {

  private final RestClientErrorMapper mapper;

  @Override
  public Optional<Map.Entry<String, String>> getCodeAndMessage(String jsonBody) {
    return mapper.getCodeAndMessage(jsonBody, ErrorDto.class, errorMapper);
  }

  private final Function<ErrorDto, Map.Entry<String, String>> errorMapper = errorDetail
      -> Map.of(errorDetail.getCode(), errorDetail.getMessage()).entrySet().iterator().next();

  @Override
  public boolean supports(Class<?> wrapperClass) {
    return wrapperClass.isAssignableFrom(ErrorDto.class);
  }

}
