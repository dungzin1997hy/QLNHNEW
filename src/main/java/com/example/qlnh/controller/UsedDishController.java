package com.example.qlnh.controller;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.UsedDishCreateDto;
import com.example.qlnh.dto.request.update.UserUpdateDto;
import com.example.qlnh.dto.response.UsedDishResponseDto;
import com.example.qlnh.service.UsedDishService;
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
@RequestMapping("/used-dish")
public class UsedDishController {
  private final UsedDishService usedDishService;

  @PostMapping("/create")
  public CommonRes<String> create(@Valid @RequestBody UsedDishCreateDto usedDishCreateDto) {
    return usedDishService.create(usedDishCreateDto);
  }

  @GetMapping("/search")
  public CommonRes<List<UsedDishResponseDto>> readByTable(@RequestParam Integer tableId) {
    return usedDishService.readByTable(tableId);
  }

  @DeleteMapping("/delete")
  public CommonRes<String> delete(@RequestParam Integer id) {
    return usedDishService.delete(id);
  }

}
