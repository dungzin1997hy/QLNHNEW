package com.example.qlnh.service;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.TableCreateDto;
import com.example.qlnh.dto.request.update.TableSetDto;
import com.example.qlnh.dto.request.update.TableUpdateDto;
import com.example.qlnh.dto.response.TableResponseDto;
import java.util.List;

public interface TableService {
  CommonRes<String> create(TableCreateDto tableDto);

  CommonRes<List<TableResponseDto>> readAll();

  CommonRes<List<TableResponseDto>> readByName(String name);

  CommonRes<String> update(TableUpdateDto tableUpdateDto);

  CommonRes<String> setTable(TableSetDto tableSetDto);

  CommonRes<String> delete(int id);
}
