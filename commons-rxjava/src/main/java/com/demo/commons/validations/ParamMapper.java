package com.demo.commons.validations;

import com.demo.commons.errors.exceptions.NoSuchParamMapperException;

import java.util.List;
import java.util.Map;

public interface ParamMapper<T> {

  Map.Entry<T, Map<String, String>> map(Map<String, String> params);

  boolean supports(Class<?> paramClass);

  static <T> ParamMapper<T> selectMapper(Class<T> paramClass, List<ParamMapper> paramMappers) {
    return paramMappers
        .stream()
        .filter(mapper -> mapper.supports(paramClass))
        .findFirst()
        .orElseThrow(() -> new NoSuchParamMapperException(paramClass));
  }
}