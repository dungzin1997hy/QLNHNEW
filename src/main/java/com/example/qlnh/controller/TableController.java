package com.example.qlnh.controller;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.TableCreateDto;
import com.example.qlnh.dto.request.update.TableSetDto;
import com.example.qlnh.dto.request.update.TableUpdateDto;
import com.example.qlnh.dto.response.TableResponseDto;
import com.example.qlnh.service.TableService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/table")
public class TableController {
  private final TableService tableService;

  @PostMapping("/create")
  public CommonRes<String> create(@Valid @RequestBody TableCreateDto tableDto) {
    return tableService.create(tableDto);
  }

  @GetMapping
  public CommonRes<List<TableResponseDto>> readAll() {
    return tableService.readAll();
  }

  @GetMapping("/search")
  public CommonRes<List<TableResponseDto>> readByName(@RequestParam String name) {
    return tableService.readByName(name);
  }

  @PutMapping("/update")
  public CommonRes<String> update(@Valid @RequestBody TableUpdateDto tableUpdateDto) {
    return tableService.update(tableUpdateDto);
  }

  @PutMapping("/set-table")
  public CommonRes<String> setTable(@Valid @RequestBody TableSetDto tableSetDto) {
    return tableService.setTable(tableSetDto);
  }

  @DeleteMapping("/delete")
  public CommonRes<String> delete(@RequestParam Integer id) {
    return tableService.delete(id);
  }
}
