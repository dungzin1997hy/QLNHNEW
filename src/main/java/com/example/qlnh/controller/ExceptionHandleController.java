package com.example.qlnh.controller;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.exception.AccountLockException;
import com.example.qlnh.exception.BookingExistedException;
import com.example.qlnh.exception.DuplicateException;
import com.example.qlnh.exception.InvalidTokenException;
import com.example.qlnh.exception.NotFoundException;
import com.example.qlnh.exception.SendMailException;
import com.example.qlnh.exception.TableIsNotUsed;
import com.example.qlnh.exception.TableIsUsed;
import com.example.qlnh.exception.WrongEmailOrPasswordException;
import com.example.qlnh.exception.WrongPasswordException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandleController {
  @ExceptionHandler({MethodArgumentNotValidException.class})
  private ResponseEntity<CommonRes<Map<String, String>>> handleValidate(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return getObjectResponseEntity(errors);
  }

  @ExceptionHandler({MissingServletRequestParameterException.class})
  private ResponseEntity<CommonRes<Map<String, String>>> handleMissingParams(
      MissingServletRequestParameterException ex) {
    Map<String, String> errors = new HashMap<>();
    String fieldName = ex.getParameterName();
    String errorMessage = " is missing";
    errors.put(fieldName, errorMessage);
    return getObjectResponseEntity(errors);
  }

  @ExceptionHandler({SQLException.class})
  private ResponseEntity<Object> handleSQLException() {
    CommonRes<String> response = new CommonRes<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add("result", "NG");
    response.setResult("NG");
    response.setData("Some thing wrong!!");
    return ResponseEntity.badRequest().headers(headers).body(response);
  }

  @ExceptionHandler({BookingExistedException.class})
  private ResponseEntity<CommonRes<String>> handleBookingExistedException(
      BookingExistedException existedException) {
    CommonRes<String> response = new CommonRes<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add("result", "NG");
    response.setResult("NG");
    response.setData(existedException.getMessage());
    return ResponseEntity.badRequest().headers(headers).body(response);
  }

  @ExceptionHandler({TableIsNotUsed.class})
  private ResponseEntity<CommonRes<String>> handleTableIsNotUsed(TableIsNotUsed tableIsNotUsed) {
    CommonRes<String> response = new CommonRes<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add("result", "NG");
    response.setResult("NG");
    response.setData(tableIsNotUsed.getMessage());
    return ResponseEntity.badRequest().headers(headers).body(response);
  }

  @ExceptionHandler({TableIsUsed.class})
  private ResponseEntity<CommonRes<String>> handleTableIsUsed(TableIsUsed tableIsUsed) {
    CommonRes<String> response = new CommonRes<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add("result", "NG");
    response.setResult("NG");
    response.setData(tableIsUsed.getMessage());
    return ResponseEntity.badRequest().headers(headers).body(response);
  }

  @ExceptionHandler({DuplicateException.class})
  private ResponseEntity<CommonRes<String>> handleBookingExistedException(
      DuplicateException duplicateException) {
    CommonRes<String> response = new CommonRes<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add("result", "NG");
    response.setResult("NG");
    response.setData(String.format("Duplicate %s !!", duplicateException.getMessage()));
    return ResponseEntity.badRequest().headers(headers).body(response);
  }

  @ExceptionHandler({NotFoundException.class})
  private ResponseEntity<CommonRes<String>> handleBookingExistedException(
      NotFoundException notFoundException) {
    CommonRes<String> response = new CommonRes<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add("result", "NG");
    response.setResult("NG");
    response.setData(String.format("%s not found !!", notFoundException.getMessage()));
    return ResponseEntity.badRequest().headers(headers).body(response);
  }

  @ExceptionHandler({WrongEmailOrPasswordException.class})
  private ResponseEntity<CommonRes<String>> handleUsernameNotFoundException(
      WrongEmailOrPasswordException exception) {
    CommonRes<String> response = new CommonRes<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add("result", "NG");
    response.setResult("NG");
    response.setData(exception.getMessage());
    return ResponseEntity.badRequest().headers(headers).body(response);
  }

  @ExceptionHandler({AccountLockException.class})
  private ResponseEntity<CommonRes<String>> handleAccountLockException(
      AccountLockException exception) {
    CommonRes<String> response = new CommonRes<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add("result", "NG");
    response.setResult("NG");
    response.setData(exception.getMessage());
    return ResponseEntity.badRequest().headers(headers).body(response);
  }

  @ExceptionHandler({WrongPasswordException.class})
  private ResponseEntity<CommonRes<String>> handleWrongPasswordException(
      WrongPasswordException exception) {
    CommonRes<String> response = new CommonRes<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add("result", "NG");
    response.setResult("NG");
    response.setData(exception.getMessage());
    return ResponseEntity.badRequest().headers(headers).body(response);
  }

  @ExceptionHandler({SendMailException.class})
  private ResponseEntity<CommonRes<String>> handleSendMailException(SendMailException exception) {
    CommonRes<String> response = new CommonRes<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add("result", "NG");
    response.setResult("NG");
    response.setData(exception.getMessage());
    return ResponseEntity.badRequest().headers(headers).body(response);
  }

  @ExceptionHandler({InvalidTokenException.class})
  private ResponseEntity<CommonRes<String>> handleInvalidTokenException(
      InvalidTokenException exception) {
    CommonRes<String> response = new CommonRes<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add("result", "NG");
    response.setResult("NG");
    response.setData(exception.getMessage());
    return ResponseEntity.badRequest().headers(headers).body(response);
  }

  private ResponseEntity<CommonRes<Map<String, String>>> getObjectResponseEntity(
      Map<String, String> errors) {
    CommonRes<Map<String, String>> response = new CommonRes<>();
    HttpHeaders headers = new HttpHeaders();
    headers.add("result", "NG");
    response.setResult("NG");
    response.setData(errors);
    return ResponseEntity.badRequest().headers(headers).body(response);
  }
}
