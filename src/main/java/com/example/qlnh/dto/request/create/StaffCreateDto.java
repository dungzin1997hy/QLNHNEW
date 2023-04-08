package com.example.qlnh.dto.request.create;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StaffCreateDto {
  @NotNull private String name;
  @NotNull private String phoneNumber;
  private String email;
  @NotNull private String idCard;
}
