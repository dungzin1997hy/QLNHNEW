package com.example.qlnh.dto.request.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TableCreateDto {
  @NotEmpty
  @NotNull
  @Length(min = 1, max = 255)
  private String name;
}
