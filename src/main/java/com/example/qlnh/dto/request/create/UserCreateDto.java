package com.example.qlnh.dto.request.create;

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
public class UserCreateDto {
  @NotNull
  @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email must be correct")
  private String email;

  @NotNull
  private String password;

  @NotNull
  @Pattern(regexp = "ADMIN|USER")
  private String role;
}
