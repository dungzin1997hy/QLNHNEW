package com.example.qlnh.dto.request.update;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerUpdateDto {
  @NotNull private int id;
  @NotNull private String name;
  @NotNull private String phoneNumber;
  private String email;
}
