package com.example.qlnh.controller;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.DishCreateDto;
import com.example.qlnh.dto.request.update.DishUpdateDto;
import com.example.qlnh.dto.response.DishResponseDto;
import com.example.qlnh.service.DishService;
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
@RequestMapping("/dish")
public class DishController {
  private final DishService dishService;

  @PostMapping("/create")
  public CommonRes<String> create(@Valid @RequestBody DishCreateDto dishCreateDto) {
    return dishService.create(dishCreateDto);
  }

  @GetMapping
  public CommonRes<List<DishResponseDto>> readAll() {
    return dishService.readAll();
  }

  @GetMapping("/search")
  public CommonRes<List<DishResponseDto>> readByName(@RequestParam String name) {
    return dishService.readByName(name);
  }

  @PutMapping("/update")
  public CommonRes<String> update(@Valid @RequestBody DishUpdateDto dishUpdateDto) {
    return dishService.update(dishUpdateDto);
  }

  @DeleteMapping("/delete")
  public CommonRes<String> delete(@RequestParam Integer id) {
    return dishService.delete(id);
  }
}
