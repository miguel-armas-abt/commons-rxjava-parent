package com.demo.commons.properties.logging;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ObfuscationTemplate {

  private Set<String> bodyFields;

  private Set<String> headers;
}