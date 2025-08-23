package com.demo.commons.restclient.utils;

import com.demo.commons.properties.restclient.HeaderTemplate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpHeadersFiller {

  public static HttpHeaders fillHeaders(HeaderTemplate headerTemplate, Map<String, String> currentHeaders) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAll(HeadersFiller.fillHeaders(headerTemplate, currentHeaders));
    return httpHeaders;
  }
}