package com.ncs.daopattern.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncs.daopattern.dao.UserDao;
import com.ncs.daopattern.dto.response.AccountResponse;
import com.ncs.daopattern.dto.response.ApiResponse;
import com.ncs.daopattern.exception.base.ConflictException;
import com.ncs.daopattern.model.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class UserServiceImpl implements UserService {
  private UserDao userDao;

  private final RestTemplate restTemplate;

  private final ObjectMapper objectMapper;

  public UserServiceImpl(UserDao userDao, RestTemplate restTemplate, ObjectMapper objectMapper) {
    this.userDao = userDao;
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
  }

  @Override
  public User create(User user) {
    return null;
  }

  @Override
  public User update(User user) {
    return null;
  }

  @Override
  public User get(int id) {
    return null;
  }

  @Override
  @Transactional
  public List<User> getAllUser() throws SQLException {
    try {
      getAccountResponseExternalApi();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return userDao.getAll();
  }

  @Override
  public List<AccountResponse> getAccountResponseExternalApi() throws IOException {
//    String apiUrl = "http://localhost:8081/api/v1/accounts?all=true";
//    ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
//    if (response.getStatusCode().is2xxSuccessful()) {
//      String responseBody = response.getBody();
//      ApiResponse<AccountResponse> apiResponse = objectMapper.readValue(responseBody, new TypeReference<ApiResponse<AccountResponse>>() {
//      });
//      return apiResponse.getData().getAccounts();
//    } else {
//      // Xử lý lỗi từ API
//      return null;
//    }
    return null;
  }

  @Override
  public void delete(int id) throws SQLException {
    User user = userDao.get(id);
    if (user == null) {
      throw new ConflictException(String.valueOf(id));
    } else {
      userDao.delete(id);
    }
  }
}
