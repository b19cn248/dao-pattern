package com.ncs.daopattern.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncs.daopattern.dto.response.AccountResponse;
import com.ncs.daopattern.dto.response.ApiResponse;
import com.ncs.daopattern.model.User;
import com.ncs.daopattern.service.UserService;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserDaoIml implements UserDao {
  private final RestTemplate restTemplate;

  private final ObjectMapper objectMapper;

  public UserDaoIml(RestTemplate restTemplate, ObjectMapper objectMapper) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
  }

  public List<AccountResponse> getAccountResponseExternalApi() throws IOException {
    String apiUrl = "http://localhost:8081/api/v1/accounts?all=true";
    ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
    if (response.getStatusCode().is2xxSuccessful()) {
      String responseBody = response.getBody();
      ApiResponse<AccountResponse> apiResponse = objectMapper.readValue(responseBody, new TypeReference<ApiResponse<AccountResponse>>() {
      });
      return apiResponse.getData().getAccounts();
    } else {
      // Xử lý lỗi từ API
      return null;
    }
  }

  @Override
  @Transactional
  public List<User> getAll() {
    String sql = "select *from users";
    List<User> list = new ArrayList<>();
    Connection connection = null;
    try {
      connection = DataSource.getInstance().getConnection();
      log.info("connection getAll :" + connection);
      getAccountResponseExternalApi();// loi
      PreparedStatement ps = connection.prepareStatement(sql); // neu xay ra loi thi connection se khong duoc dong, dung finally
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        list.add(new User(rs.getInt("user_id"),
              rs.getString("first_name"),
              rs.getString("last_name"),
              rs.getString("email")));
      }
    } catch (Exception e) {
      System.out.println(e);
//    } finally {
//      if (connection != null) {
//        try {
//          connection.close();
//        } catch (SQLException e) {
//          throw new RuntimeException(e);
//        }
//      }
    }
    return list;
  }

  @Override
  public void delete(int id) throws SQLException {
    String sql = "DELETE from users where user_Id = ?";
    try (Connection connection = DataSource.getInstance().getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
      log.info("connection 2: " + connection);
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Override
  public User get(int id) throws SQLException {
    String sql = "select *from users where user_id = ?";
    Connection connection = null;
    try {
      connection = HikariPool.getInstance().getConnection();
      log.info("connection get:" + connection);
      connection.setAutoCommit(false); // tắt tự động commit
      System.out.println(connection);
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) return new User(rs.getInt("user_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("email"));
      connection.commit(); // commit thủ công
    } catch (Exception e) {
      System.out.println(e);
      if (connection != null) {

      }
    } finally { // dù có lỗi hay không thì cũng thực hiện
      if (connection != null) {
        connection.setAutoCommit(true);
        connection.close();
      }
    }
    return null;
  }

  @Override
  public void save(User p) throws SQLException {
    String sql = "INSERT INTO users(first_name,last_name,email) values (?,?,?)";
    Connection connection = null;
    try {
      connection = HikariPool.getInstance().getConnection();
      connection.setAutoCommit(false);
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, p.getFirstName());
      ps.setString(2, p.getLastName());
      ps.setString(3, p.getEmail());
      ps.executeUpdate();
      connection.commit();
    } catch (Exception e) {
      System.out.println(e);
      connection.rollback();
    } finally {
      if (connection != null) {
        connection.setAutoCommit(true);
        connection.close();
      }
    }
  }

  @Override
  public void update(User p) throws SQLException {
    String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ? where user_Id = ?";
    Connection connection = null;
    try {
      connection = HikariPool.getInstance().getConnection();
      connection.setAutoCommit(false);
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, p.getFirstName());
      ps.setString(2, p.getLastName());
      ps.setString(3, p.getEmail());
      ps.setInt(4, p.getId());
      ps.executeUpdate();
      connection.commit();
    } catch (Exception e) {
      System.out.println(e);
      connection.rollback();
    } finally {
      if (connection != null) {
        connection.setAutoCommit(true);
        connection.close();
      }
    }
  }

//    @Override
//    public void delete(int id) throws SQLException{
//        String sql = "DELETE  from users where user_Id = ?";
//        Connection connection = null;
//        try {
//            connection = HikariPool.getInstance().getConnection();
//            connection.setAutoCommit(false);
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1,id);
//            ps.executeUpdate();
//            connection.commit();
//        }catch (Exception e) {
//            System.out.println(e);
//            connection.rollback();
//        }finally {
//            if (connection != null) {
//                connection.setAutoCommit(true);
//                connection.close();
//            }
//        }
//    }
}
