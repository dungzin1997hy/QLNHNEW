package com.example.qlnh.dto.request.create;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DishCreateDto {
  @NotEmpty(message = "name must not be empty")
  @NotNull(message = "name is required")
  private String name;

  @Min(1)
  private int price;

  @Pattern(regexp = "plate|bowl", message = "unit must be plate or bowl")
  @NotNull(message = "unit is required")
  private String unit;

  @NotNull private String image;
}
