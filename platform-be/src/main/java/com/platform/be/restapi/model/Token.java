package com.platform.be.restapi.model;

import lombok.Data;

@SuppressWarnings("unused")
@Data
public class Token {
  private String userRole;
  private String accessToken;
  private Long sessionTimeOut;
}
