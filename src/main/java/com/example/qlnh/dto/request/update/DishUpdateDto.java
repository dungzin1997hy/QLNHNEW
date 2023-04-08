package com.example.qlnh.dto.request.update;

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
public class DishUpdateDto {
  @NotNull private Integer id;

  @NotNull @NotEmpty private String name;

  @Min(1)
  private int price;

  @Pattern(regexp = "plate|bowl", message = "unit must be plate or bowl")
  @NotNull(message = "unit is required")
  private String unit;

  @NotNull private String image;
}
