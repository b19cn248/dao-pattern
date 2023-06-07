package com.ncs.daopattern.dao;

import com.ncs.daopattern.model.User;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public interface UserDao {

    List<User> getAll() throws SQLException;

    User get(int id) throws SQLException;

    void save(User p) throws SQLException;

    void update(User p) throws SQLException;

    void delete(int id) throws SQLException;
}