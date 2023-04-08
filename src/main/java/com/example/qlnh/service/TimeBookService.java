package com.example.qlnh.service;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.TimeBookCreateDto;
import com.example.qlnh.dto.request.update.TimebookUpdateDto;
import com.example.qlnh.dto.response.TimeBookResponseDto;
import java.util.List;

public interface TimeBookService {
  CommonRes<String> create(TimeBookCreateDto timeBookCreateDto);

  CommonRes<List<TimeBookResponseDto>> readAll();

  CommonRes<String> update(TimebookUpdateDto timebookUpdateDto);

  CommonRes<String> delete(int id);
}
