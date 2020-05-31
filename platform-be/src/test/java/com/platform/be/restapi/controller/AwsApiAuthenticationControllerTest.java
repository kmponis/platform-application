package com.platform.be.restapi.controller;

import com.platform.be.restapi.properties.ResponseProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AwsApiAuthenticationControllerTest {

  @Autowired
  private ResponseProperties responseProperties;

  @Autowired
  private MockMvc mockMvc;

  private static final String baseUrl = "/aws/api/authentication/v1";

  /*
   * Test URL '/'
   */
  @Test
  public void whenIndex_thenExpect200() throws Exception {
    this.mockMvc.perform(get(baseUrl + "/")).andExpect(status().isOk())
        .andExpect(content().string(containsString(responseProperties.index)));
  }

  /*
   * Test URL '/authentication'
   */
  @Test
  public void whenAuthentication_withNoContentType_thenExpect415() throws Exception {
    this.mockMvc.perform(post(baseUrl + "/authentication")).andExpect(status().is(415));
  }

  @Test
  public void whenAuthentication_withNoContent_thenExpect400() throws Exception {
    this.mockMvc.perform(post(baseUrl + "/authentication").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(400));
  }

  @Test
  public void whenAuthentication_withWrongContent_thenExpect400() throws Exception {
    this.mockMvc.perform(post(baseUrl + "/authentication").content("{\"username\":\"adm\", \"password\":\"admin\"}")
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(400));
  }

  @Test
  public void whenAuthentication_withAdminContent_thenExpect200() throws Exception {
    this.mockMvc.perform(post(baseUrl + "/authentication").content("{\"username\":\"admin\", \"password\":\"admin\"}")
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(200));
  }

}
