package com.example.qlnh.dto.request.create;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UsedDishCreateDto {
  @Min(1)
  private int amount;

  @NotNull private Integer tableId;
  @NotNull private Integer dishId;
}
