package com.platform.be.restapi.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.platform.be.restapi.model.Token;
import com.platform.be.restapi.properties.ApplicationProperties;
import com.platform.be.restapi.service.AwsApiAuthenticationService;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.BadRequestException;

//import javax.ws.rs.core.MediaType;

@Service
public class AwsApiAuthenticationServiceImpl implements AwsApiAuthenticationService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private ApplicationProperties applicationProperties;

  private OkHttpClient okHttpClient = new OkHttpClient();
  private RestTemplate restTemplate = new RestTemplate();
  private HttpHeaders httpHeaders;

  private static final String CONTENT_TYPE = "Content-Type";
  private static final String APPLICATION_JSON = "application/json";

  @Override
  public String index() {
    Request request = new Request.Builder().url(applicationProperties.baseUrl + "/").get().build();

    JsonObject jsonObject = getJsonObject(request);
    if (null == jsonObject || null == jsonObject.get("message")) {
      throw new BadRequestException();
    }

    return jsonObject.get("message").getAsString();
  }

  @Override
  public Token authentication(String payload) {
    RequestBody body = RequestBody.create(MediaType.parse(APPLICATION_JSON), payload);
    String uri = applicationProperties.baseUrl + "/authentication";

    Request request = new Request.Builder().url(uri).addHeader(CONTENT_TYPE, APPLICATION_JSON).post(body).build();
    JsonObject jsonObject = getJsonObject(request);
    if (null == jsonObject || null == jsonObject.get("token")) {
      throw new BadRequestException();
    }
    // RestTemplate
//    JsonObject jsonObjectRT = getJsonObject(uri);
//    if (null == jsonObjectRT || null == jsonObjectRT.get("token")) {
//      throw new BadRequestException();
//    }

    Token token = new Gson().fromJson(jsonObject.get("token").getAsJsonObject(), Token.class);
    return token;
  }

  /**
   * Get JsonObject from request using OkHttp
   * 
   * @param request
   * @return JsonObject
   */
  private JsonObject getJsonObject(Request request) {
    try {
      Response response = okHttpClient.newCall(request).execute();
      JsonParser parser = new JsonParser();
      JsonElement jsonElement = parser.parse(response.body().string());
      JsonObject jsonResponse = jsonElement.getAsJsonObject();
      logger.info("Endpoint: {} - response: {}", request.httpUrl(), jsonResponse);
      return jsonResponse;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new BadRequestException();
    }
  }

  /**
   * Get JsonObject from url using RestTemplate
   *
   * @param uri
   * @return jsonResponse
   */
  private JsonObject getJsonObject(String uri) {
    try {
//      HttpHeaders headers = new HttpHeaders();
//      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//      HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//      ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
//      JsonObject jsonResponse = new Gson().fromJson(result.getBody(), JsonObject.class);

      JsonObject jsonResponse = restTemplate.getForObject(uri, JsonObject.class);
      logger.info("Endpoint: {} - response: {}", uri, jsonResponse);
      return jsonResponse;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw e;
    }
  }

}