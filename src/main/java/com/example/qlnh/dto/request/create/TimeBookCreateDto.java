package com.example.qlnh.dto.request.create;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TimeBookCreateDto {
  @NotNull
  @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "time is must be correct")
  private String timeFrom;

  @NotNull
  @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "time is must be correct")
  private String timeTo;
}
