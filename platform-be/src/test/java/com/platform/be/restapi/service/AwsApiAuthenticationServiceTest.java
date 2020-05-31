package com.platform.be.restapi.service;

import com.platform.be.restapi.properties.ApplicationProperties;
import com.platform.be.restapi.service.impl.AwsApiAuthenticationServiceImpl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request.Builder;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

/**
 * @formatter:off
 * 
 * TODO: 
 * 1) Fix docker-compose issue: Unable to find a @SpringBootConfiguration, you 
 * need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
 * 
 * 2) Implement JUnit mock tests, example JerseyRestClientServiceTest.java
 */
@RunWith(JUnitPlatform.class)
@Ignore
public class AwsApiAuthenticationServiceTest {

  private static final String REST_URI = "https://mock-url.com";
  
  @InjectMocks
  private AwsApiAuthenticationService awsApiAuthenticationService = new AwsApiAuthenticationServiceImpl();

  @Mock
  private OkHttpClient mockOkHttpClient;
  @Mock
  private Builder mockBuilder;
  @Mock
  private ApplicationProperties mockApplicationProperties;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void indexTest() throws IOException {
  }

  @Test
  public void authenticationTest() {
  }
}
