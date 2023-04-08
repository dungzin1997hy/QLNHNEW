package com.example.qlnh.service;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.StaffCreateDto;
import com.example.qlnh.dto.request.update.StaffUpdateDto;
import com.example.qlnh.dto.response.StaffResponseDto;
import java.util.List;

public interface StaffService {
  CommonRes<String> create(StaffCreateDto staffCreateDto);

  CommonRes<List<StaffResponseDto>> readAll();

  CommonRes<List<StaffResponseDto>> readByPhoneNumber(String phoneNumber);

  CommonRes<String> update(StaffUpdateDto staffUpdateDto);

  CommonRes<String> delete(int id);
}
