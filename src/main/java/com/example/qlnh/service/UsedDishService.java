package com.example.qlnh.service;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.UsedDishCreateDto;
import com.example.qlnh.dto.response.UsedDishResponseDto;
import java.util.List;

public interface UsedDishService {
  CommonRes<String> create(UsedDishCreateDto usedDishCreateDto);

  CommonRes<List<UsedDishResponseDto>> readByTable(int tableId);

  CommonRes<String> delete(int id);
}
