package com.platform.be.restapi.controller;

import com.platform.be.restapi.model.Token;
import com.platform.be.restapi.service.AwsApiAuthenticationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.BadRequestException;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/aws/api/authentication/v1")
public class AwsApiAuthenticationController {

  @Autowired
  private AwsApiAuthenticationService awsApiAuthenticationService;

  @ApiOperation(value = "Get index page", response = ResponseEntity.class)
  @GetMapping(value = "/")
  public ResponseEntity<String> index() {
    ResponseEntity<String> response;
    try {
      response = ResponseEntity.status(HttpStatus.OK).body(awsApiAuthenticationService.index());
    } catch (BadRequestException e) {
      response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }
    return response;
  }

  @ApiOperation(value = "Get Token", response = ResponseEntity.class)
  @PostMapping(value = "/authentication", consumes = { "application/json" })
  public ResponseEntity<Token> authentication(@RequestBody Map<String, Object> payload) {
    ResponseEntity<Token> response;
    try {
      response = ResponseEntity.status(HttpStatus.OK).body(awsApiAuthenticationService.authentication(payload.toString()));
    } catch (BadRequestException e) {
      response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Token());
    } catch (HttpClientErrorException e) {
      response = ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Token());
    }
    return response;
  }

}
