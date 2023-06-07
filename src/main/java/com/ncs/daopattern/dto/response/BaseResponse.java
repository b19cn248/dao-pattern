package com.ncs.daopattern.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ncs.daopattern.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import  static  com.ncs.daopattern.constant.JdbcDaoPatternConstant.MessageResponse.SUCCESS;
@Data
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseResponse<T> {
    private int status;
    private String message;
    private String timestamp;
    private T data;

    public static <T> BaseResponse<T> of(int status, String message, T data) {
        return of(status, message, DateUtils.getCurrentDateString(), data);
    }

    public static <T> BaseResponse<T> ofSuccess(int status, T data) {
        return of(status, SUCCESS, DateUtils.getCurrentDateString(), data);
    }
}