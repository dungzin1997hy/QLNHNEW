package com.example.qlnh.controller;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.TimeBookCreateDto;
import com.example.qlnh.dto.request.update.TimebookUpdateDto;
import com.example.qlnh.dto.response.TimeBookResponseDto;
import com.example.qlnh.service.TimeBookService;
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
@RequestMapping("/time-book")
@RequiredArgsConstructor
@Validated
public class TimeBookController {
  private final TimeBookService timeBookService;

  @PostMapping("/create")
  public CommonRes<String> create(@Valid @RequestBody TimeBookCreateDto timeBookCreateDto) {
    return timeBookService.create(timeBookCreateDto);
  }

  @GetMapping
  public CommonRes<List<TimeBookResponseDto>> readAll() {
    return timeBookService.readAll();
  }

  @PutMapping("/update")
  public CommonRes<String> update(@Valid @RequestBody TimebookUpdateDto timebookUpdateDto) {
    return timeBookService.update(timebookUpdateDto);
  }

  @DeleteMapping("/delete")
  public CommonRes<String> delete(@RequestParam Integer id) {
    return timeBookService.delete(id);
  }
}
