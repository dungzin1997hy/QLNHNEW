package com.example.qlnh.controller;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.BookingCreateDto;
import com.example.qlnh.dto.request.update.BookingUpdateDto;
import com.example.qlnh.dto.response.BookingResponseDto;
import com.example.qlnh.service.BookingService;
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
@RequestMapping("/booking")
public class BookingController {
  private final BookingService bookingService;

  @PostMapping("/create")
  public CommonRes<String> create(@Valid @RequestBody BookingCreateDto bookingCreateDto) {
    return bookingService.create(bookingCreateDto);
  }

  @GetMapping("/search")
  public CommonRes<BookingResponseDto> readByCustomer(@RequestParam Integer customerId) {
    return bookingService.readByCustomer(customerId);
  }

  @PutMapping("/update")
  public CommonRes<String> update(@Valid @RequestBody BookingUpdateDto bookingUpdateDto) {
    return bookingService.update(bookingUpdateDto);
  }

  @DeleteMapping("/delete")
  public CommonRes<String> delete(@RequestParam Integer id) {
    return bookingService.delete(id);
  }

  @PostMapping("/check-in")
  public CommonRes<String> checkIn(@RequestParam Integer id) {
    return bookingService.checkIn(id);
  }
}
