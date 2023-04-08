package com.example.qlnh.service;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.BookingCreateDto;
import com.example.qlnh.dto.request.update.BookingUpdateDto;
import com.example.qlnh.dto.response.BookingResponseDto;

public interface BookingService {
  CommonRes<String> create(BookingCreateDto bookingCreateDto);

  CommonRes<String> update(BookingUpdateDto bookingUpdateDto);

  CommonRes<BookingResponseDto> readByCustomer(int customerId);

  CommonRes<String> delete(int id);

  CommonRes<String> checkIn(int id);
}
