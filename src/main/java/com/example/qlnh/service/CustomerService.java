package com.example.qlnh.service;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.CustomerCreateDto;
import com.example.qlnh.dto.request.update.CustomerUpdateDto;
import com.example.qlnh.dto.response.CustomerResponseDto;
import java.util.List;

public interface CustomerService {
  CommonRes<String> create(CustomerCreateDto customerCreateDto);

  CommonRes<List<CustomerResponseDto>> readAll();

  CommonRes<List<CustomerResponseDto>> readByPhoneNumber(String phoneNumber);

  CommonRes<String> update(CustomerUpdateDto customerUpdateDto);

  CommonRes<String> delete(int id);
}
