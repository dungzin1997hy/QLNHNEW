package com.example.qlnh.dto.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UsedDishResponseDto {
  private int id;
  private int amount;
  private OffsetDateTime time;
  private TableResponseDto table;
  private DishResponseDto dish;
  private String status;
}
