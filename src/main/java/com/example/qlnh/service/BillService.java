package com.example.qlnh.service;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.BillCreateDto;
import com.example.qlnh.dto.response.BillResponseDto;
import java.util.List;

public interface BillService {
  CommonRes<String> create(BillCreateDto billDto);

  CommonRes<List<BillResponseDto>> readAll(int pageIndex, int pageSize);

  CommonRes<List<BillResponseDto>> readByCreateAt(
      int pageIndex, int pageSize, String dateTimeFrom, String dateTimeTo);
}
