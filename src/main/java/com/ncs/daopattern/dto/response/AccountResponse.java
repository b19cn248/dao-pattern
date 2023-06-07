package com.ncs.daopattern.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class AccountResponse {
  private String id;
  private String username;
  private boolean activated;

  public AccountResponse(String id, String username, boolean activated) {
    this.id = id;
    this.username = username;
    this.activated = activated;
  }
}
