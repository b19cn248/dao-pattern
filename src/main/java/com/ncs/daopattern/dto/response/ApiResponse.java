package com.ncs.daopattern.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
  private int status;
  private String message;
  private AccountData<T> data;
  private String timestamp;
}
