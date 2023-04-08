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
public class BookingUpdateDto {
  @NotNull private int id;
  @NotNull private int tableId;
  @NotNull private int customerId;
  @NotNull private int timeBookId;
}
