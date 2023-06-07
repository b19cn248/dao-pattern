//package com.ncs.daopattern.exception.advice;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.util.*;
//
//@ControllerAdvice
//public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        Map<String, Object> responseBody = new LinkedHashMap<>();
//        responseBody.put("timestamp",new Date());
//        responseBody.put("status",status.value());
//
//        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
//
//        List<String> listErros = new ArrayList<>();
//        for (FieldError fieldError:fieldErrors) {
//            String errorMessage = fieldError.getDefaultMessage();
//            listErros.add(errorMessage);
//        }
//        responseBody.put("errors",listErros);
//        return new ResponseEntity<>(responseBody,headers,status);
//    }
//}
