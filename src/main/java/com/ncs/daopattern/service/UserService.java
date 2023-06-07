package com.ncs.daopattern.service;

import com.ncs.daopattern.dto.response.AccountResponse;
import com.ncs.daopattern.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public User create(User user);
    public User update(User user);
    public User get(int id);
    public List<User> getAllUser() throws SQLException;
    public void delete(int id) throws SQLException;

    public List<AccountResponse> getAccountResponseExternalApi() throws Exception;
}
