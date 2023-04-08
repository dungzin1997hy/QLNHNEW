package com.example.qlnh.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BillResponseDto {
  private int id;
  private TableResponseDto table;
  private List<UsedDishResponseDto> usedDishes;
  private CustomerResponseDto customer;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssxxx")
  private OffsetDateTime createAt;

  private int total;
  private int payType;
}
