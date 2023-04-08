package com.example.qlnh.controller;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.UserLoginDto;
import com.example.qlnh.dto.request.create.UserCreateDto;
import com.example.qlnh.dto.request.update.UserForgotPasswordDto;
import com.example.qlnh.dto.request.update.UserUpdateDto;
import com.example.qlnh.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @PostMapping("/registration")
  public CommonRes<String> create(@Valid @RequestBody UserCreateDto userCreateDto) {
    return userService.create(userCreateDto);
  }

  @PostMapping("/login")
  public CommonRes<String> login(@Valid @RequestBody UserLoginDto userLoginDto) {
    return userService.login(userLoginDto);
  }

  @PutMapping("/change-password")
  public CommonRes<String> update(@Valid @RequestBody UserUpdateDto userUpdateDto) {
    return userService.update(userUpdateDto);
  }

  @PostMapping("/forgot-password")
  public CommonRes<String> forgotPassword(@RequestParam String email) {
    return userService.forgotPassword(email);
  }

  @PutMapping("/forgot-password")
  public CommonRes<String> processForgotPassword(
      @Valid @RequestBody UserForgotPasswordDto userForgotPasswordDto) {
    return userService.processForgotPassword(userForgotPasswordDto);
  }
}
