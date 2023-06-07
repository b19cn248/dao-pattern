package com.ncs.daopattern.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariPool {
  private static HikariPool instance = null;
  private Connection connection = null;
  private HikariDataSource ds;

  private HikariPool() {
    // Khoi tao doi tuong connection
    HikariConfig config = new HikariConfig();
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setJdbcUrl("jdbc:mysql://localhost:3306/user");
    config.setUsername("root");
    config.setPassword("admin1234");
    config.setMaximumPoolSize(2);
    ds = new HikariDataSource(config);
  }

  public static HikariPool getInstance() { // synchronized : han che su dung
    if (instance == null) {
      instance = new HikariPool();
    }
    return instance;
  }

  public Connection getConnection() {
    try {
      return ds.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
