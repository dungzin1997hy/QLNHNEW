package com.example.qlnh.controller;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.BillCreateDto;
import com.example.qlnh.dto.response.BillResponseDto;
import com.example.qlnh.service.BillService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/bill")
public class BillController {
  private final BillService billService;

  @PostMapping("/create")
  public CommonRes<String> create(@Valid @RequestBody BillCreateDto billCreateDto) {
    return billService.create(billCreateDto);
  }

  @GetMapping
  public CommonRes<List<BillResponseDto>> readAll(
      @RequestParam int pageIndex, @RequestParam int pageSize) {
    return billService.readAll(pageIndex, pageSize);
  }

  @GetMapping("/search")
  public CommonRes<List<BillResponseDto>> readByCreateAt(
      @RequestParam String dateTimeFrom,
      @RequestParam String dateTimeTo,
      @RequestParam int pageIndex,
      @RequestParam int pageSize) {
    return billService.readByCreateAt(pageIndex, pageSize, dateTimeFrom, dateTimeTo);
  }
}
