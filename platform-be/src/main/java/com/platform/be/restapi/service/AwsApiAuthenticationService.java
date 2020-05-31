package com.platform.be.restapi.service;

import com.platform.be.restapi.model.Token;

public interface AwsApiAuthenticationService {

  /**
   * Index
   *
   * @return String value
   */
  public String index();

  /**
   * Authenticate User
   * 
   * @param A String in Json format having the 'username' and 'password' payload
   * @return A Token
   */
  public Token authentication(String payload);

}
