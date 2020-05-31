package com.platform.be.restapi.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({"classpath:response.properties"})
public class ResponseProperties {

  @Value("${index}")
  public String index;

}
