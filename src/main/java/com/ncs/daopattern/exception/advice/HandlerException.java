package com.ncs.daopattern.exception.advice;

import com.ncs.daopattern.dto.response.BaseResponse;
import com.ncs.daopattern.dto.response.Error;
import com.ncs.daopattern.exception.base.BaseException;
import com.ncs.daopattern.exception.base.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
public class HandlerException {
    private final MessageSource messageSource;

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<BaseResponse<Error>> handleException(BaseException ex, WebRequest webRequest) {

        String language = webRequest.getHeader("Accept-Language");

        return ResponseEntity
                .status(ex.getStatus())
                .body(getError(
                        ex.getStatus(),
                        ex.getCode(),
                        Objects.isNull(language) ? new Locale("en") : new Locale(language),
                        ex.getParams())
                );
    }

    private BaseResponse<Error> getError(int status, String code, Locale locale, Map<String, String> params) {
        return BaseResponse.of(status,
                HttpStatus.valueOf(status).getReasonPhrase(),
                Error.of(code, params));
    }

    private String getMessage(String code, Locale locale, Map<String, String> params) {
        var message = getMessage(code, locale);
        if (params != null && !params.isEmpty()) {
            for (var param : params.entrySet()) {
                message = message.replace(getMessageParamsKey(param.getKey()), param.getValue());
            }
        }
        return message;
    }

    private String getMessageParamsKey(String key) {
        return "%" + key + "%";
    }

    private String getMessage(String code, Locale locale) {
        try {
            return messageSource.getMessage(code, null, locale);
        } catch (Exception ex) {
            return code;
        }
    }
}
