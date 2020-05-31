package com.platform.be.restapi.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({ "classpath:application.properties" })
public class ApplicationProperties {

  @Value("${base.uri}")
  public String baseUrl;

  @Value("${x.api.key}")
  public String xApiKey;

  @Value("${api.key}")
  public String apiKey;

}
