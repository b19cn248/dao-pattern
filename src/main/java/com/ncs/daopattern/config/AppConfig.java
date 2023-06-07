package com.ncs.daopattern.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncs.daopattern.dao.UserDao;
import com.ncs.daopattern.dao.UserDaoIml;
import com.ncs.daopattern.service.UserService;
import com.ncs.daopattern.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

  @Bean
  public UserDao userDao(RestTemplate restTemplate, ObjectMapper objectMapper) {
    return new UserDaoIml(restTemplate, objectMapper);
  }

  @Bean
  public UserService userService(UserDao userDao, RestTemplate restTemplate, ObjectMapper objectMapper) {
    return new UserServiceImpl(userDao, restTemplate, objectMapper);
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
