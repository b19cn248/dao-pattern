package com.ncs.daopattern.controller;

import com.ncs.daopattern.dto.response.AccountResponse;
import com.ncs.daopattern.dto.response.BaseResponse;
import com.ncs.daopattern.model.User;
import com.ncs.daopattern.service.MessageService;
import com.ncs.daopattern.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final MessageService messageService;

  @GetMapping("/getAll")
  public BaseResponse<List<User>> listAll(@RequestHeader(name = "Accept-Language",
        defaultValue = "en") String language) throws SQLException {
    return BaseResponse.of(HttpStatus.OK.value(), messageService.getMessage("Success", language),
          userService.getAllUser());
  }

  @GetMapping("/getAllAccount")
  public BaseResponse<List<AccountResponse>> listAllAccount(@RequestHeader(name = "Accept-Language",
        defaultValue = "en") String language) throws Exception {
    return BaseResponse.of(HttpStatus.OK.value(), messageService.getMessage("Success", language),
          userService.getAccountResponseExternalApi());
  }

  @DeleteMapping("/{id}")
  public BaseResponse<Void> deleteUser(@PathVariable(name = "id") int id,
                                       @RequestHeader(name = "Accept-Language", defaultValue = "en") String language)
        throws SQLException {
    userService.delete(id);
    return BaseResponse.of(HttpStatus.OK.value(), messageService.getMessage("Success", language),
          null);
  }

}
