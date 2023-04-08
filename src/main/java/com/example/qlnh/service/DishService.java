package com.example.qlnh.service;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.DishCreateDto;
import com.example.qlnh.dto.request.update.DishUpdateDto;
import com.example.qlnh.dto.response.DishResponseDto;
import java.util.List;

public interface DishService {
  CommonRes<String> create(DishCreateDto dishCreateDto);

  CommonRes<List<DishResponseDto>> readAll();

  CommonRes<List<DishResponseDto>> readByName(String name);

  CommonRes<String> update(DishUpdateDto dishUpdateDto);

  CommonRes<String> delete(int id);
}
