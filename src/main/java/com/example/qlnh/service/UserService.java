package com.example.qlnh.service;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.UserLoginDto;
import com.example.qlnh.dto.request.create.UserCreateDto;
import com.example.qlnh.dto.request.update.UserForgotPasswordDto;
import com.example.qlnh.dto.request.update.UserUpdateDto;

public interface UserService {
  CommonRes<String> create(UserCreateDto userCreateDto);

  CommonRes<String> login(UserLoginDto userLoginDto);

  CommonRes<String> update(UserUpdateDto userUpdateDto);

  CommonRes<String> forgotPassword(String email);

  CommonRes<String> processForgotPassword(UserForgotPasswordDto userForgotPasswordDto);
}
