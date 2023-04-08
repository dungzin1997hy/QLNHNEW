package com.example.qlnh.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StaffResponseDto {
  private int id;
  private String name;
  private String phoneNumber;
  private String email;
  private String idCard;
}
