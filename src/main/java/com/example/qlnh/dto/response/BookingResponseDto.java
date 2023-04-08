package com.example.qlnh.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookingResponseDto {
  private int id;
  private TableResponseDto table;
  private CustomerResponseDto customer;
  private TimeBookResponseDto timeBook;
  private String status;
}
