package com.example.qlnh.service.impl;

import com.example.qlnh.common.Status;
import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.TableCreateDto;
import com.example.qlnh.dto.request.update.TableSetDto;
import com.example.qlnh.dto.request.update.TableUpdateDto;
import com.example.qlnh.dto.response.TableResponseDto;
import com.example.qlnh.entities.Table;
import com.example.qlnh.exception.DuplicateException;
import com.example.qlnh.exception.NotFoundException;
import com.example.qlnh.exception.TableIsUsed;
import com.example.qlnh.repository.TableRepository;
import com.example.qlnh.service.TableService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class TableServiceImpl implements TableService {
  private final TableRepository tableRepository;
  private final ModelMapper modelMapper;

  @Override
  public CommonRes<String> create(TableCreateDto tableDto) {
    Table table = tableRepository.findTableByName(tableDto.getName());
    if (table != null) {
      throw new DuplicateException("tableName");
    }

    var tableInsert =
        Table.builder().name(tableDto.getName()).status(Status.FREE.getValue()).build();
    tableRepository.save(tableInsert);
    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Insert new table success");
    return response;
  }

  @Override
  public CommonRes<List<TableResponseDto>> readAll() {
    CommonRes<List<TableResponseDto>> response = new CommonRes<>();
    List<Table> tables = tableRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    List<TableResponseDto> tableResponseDtos =
        tables.stream().map(dish -> modelMapper.map(dish, TableResponseDto.class)).toList();
    response.setResult("OK");
    response.setData(tableResponseDtos);
    return response;
  }

  @Override
  public CommonRes<List<TableResponseDto>> readByName(String name) {
    CommonRes<List<TableResponseDto>> response = new CommonRes<>();
    List<Table> tables = tableRepository.findTableByNameContains(name);
    List<TableResponseDto> tableResponseDtos =
        tables.stream().map(table -> modelMapper.map(table, TableResponseDto.class)).toList();
    response.setResult("OK");
    response.setData(tableResponseDtos);
    return response;
  }

  @Override
  public CommonRes<String> update(TableUpdateDto tableUpdateDto) {
    Table table =
        tableRepository
            .findById(tableUpdateDto.getId())
            .orElseThrow(() -> new NotFoundException("tableId"));

    Table tableExisted =
        tableRepository.findTableByNameAndIdNot(tableUpdateDto.getName(), tableUpdateDto.getId());

    if (tableExisted != null) {
      throw new DuplicateException("tableName");
    }

    table.setName(tableUpdateDto.getName());
    tableRepository.save(table);
    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Update table success!!");
    return response;
  }

  @Override
  public CommonRes<String> setTable(TableSetDto tableSetDto) {
    Table table =
        tableRepository
            .findById(tableSetDto.getTableId())
            .orElseThrow(() -> new NotFoundException("tableId"));

    if (Status.USING.getValue().equals(table.getStatus())) {
      throw new TableIsUsed();
    }

    table.setStatus(Status.USING.getValue());
    tableRepository.save(table);
    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Set table success!!");
    return response;
  }

  @Override
  public CommonRes<String> delete(int id) {
    Table table = tableRepository.findById(id).orElseThrow(() -> new NotFoundException("tableId"));
    tableRepository.delete(table);
    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Delete table success!!");
    return response;
  }
}
