package com.demo.commons.validations;

import com.demo.commons.restserver.utils.RestServerUtils;
import io.reactivex.rxjava3.core.Single;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ParamValidator {

  private final List<ParamMapper> paramMappers;
  private final BodyValidator bodyValidator;

  public <T> Single<Map.Entry<T, Map<String, String>>> validateHeadersAndGet(HttpServletRequest servletRequest, Class<T> paramClass) {
    Map<String, String> headers = RestServerUtils.extractHeadersAsMap(servletRequest);
    return validateAndGet(headers, paramClass);
  }

  public <T> Single<Map.Entry<T, Map<String, String>>> validateQueryParamsAndGet(HttpServletRequest servletRequest, Class<T> paramClass) {
    Map<String, String> queryParams = RestServerUtils.extractQueryParamsAsMap(servletRequest);
    return validateAndGet(queryParams, paramClass);
  }

  public <T> Single<Map.Entry<T, Map<String, String>>> validateAndGet(Map<String, String> paramsMap, Class<T> paramClass) {
    ParamMapper<T> mapper = ParamMapper.selectMapper(paramClass, paramMappers);
    Map.Entry<T, Map<String, String>> tuple = mapper.map(paramsMap);

    return bodyValidator.validateAndGet(tuple.getKey())
        .map(param -> tuple);
  }
}