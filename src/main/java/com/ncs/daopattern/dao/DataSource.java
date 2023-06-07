package com.ncs.daopattern.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class DataSource {
  private static HikariConfig config = new HikariConfig();
  private static HikariDataSource ds;
  private static DataSource dataSource;

  static {
    config.setJdbcUrl("jdbc:mysql://localhost:3306/user");
    config.setUsername("root");
    config.setPassword("admin1234");
    config.setAutoCommit(false);
    config.setMaximumPoolSize(1);
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    ds = new HikariDataSource(config);
  }

  private DataSource() {
  }

  public Connection getConnection() throws SQLException {
    return ds.getConnection();
  }

  public static DataSource getInstance() {
    if (Objects.isNull(dataSource)) {
      dataSource = new DataSource();
    }
    return dataSource;
  }
}