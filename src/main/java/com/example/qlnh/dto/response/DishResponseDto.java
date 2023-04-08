package com.example.qlnh.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DishResponseDto {
  private int id;
  private String name;
  private int price;
  private String unit;
  private String image;
}
