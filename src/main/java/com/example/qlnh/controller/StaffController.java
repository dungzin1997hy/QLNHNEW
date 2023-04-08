package com.example.qlnh.controller;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.StaffCreateDto;
import com.example.qlnh.dto.request.update.StaffUpdateDto;
import com.example.qlnh.dto.response.StaffResponseDto;
import com.example.qlnh.service.StaffService;
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
@Validated
@RequiredArgsConstructor
@RequestMapping("/staff")
public class StaffController {
  private final StaffService staffService;

  @PostMapping("/create")
  public CommonRes<String> create(@Valid @RequestBody StaffCreateDto staffCreateDto) {
    return staffService.create(staffCreateDto);
  }

  @GetMapping
  public CommonRes<List<StaffResponseDto>> readAll() {
    return staffService.readAll();
  }

  @GetMapping("/search")
  public CommonRes<List<StaffResponseDto>> readByPhoneNumber(@RequestParam String phoneNumber) {
    return staffService.readByPhoneNumber(phoneNumber);
  }

  @PutMapping("/update")
  public CommonRes<String> update(@Valid @RequestBody StaffUpdateDto staffUpdateDto) {
    return staffService.update(staffUpdateDto);
  }

  @DeleteMapping("/delete")
  public CommonRes<String> delete(@RequestParam Integer id) {
    return staffService.delete(id);
  }
}
