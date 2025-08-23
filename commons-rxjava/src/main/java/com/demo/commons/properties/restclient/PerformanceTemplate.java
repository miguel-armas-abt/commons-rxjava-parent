package com.demo.commons.properties.restclient;

import com.demo.commons.restclient.enums.ConcurrencyLevel;
import com.demo.commons.restclient.enums.TimeoutLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerformanceTemplate {

  private TimeoutLevel timeout;
  private ConcurrencyLevel concurrency;
}