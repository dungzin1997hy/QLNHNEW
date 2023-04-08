package com.example.qlnh.dto.request.create;

import com.example.qlnh.common.PayType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BillCreateDto {
  @NotNull @NotEmpty private List<Integer> usedDishIds;

  @NotNull
  private int total;

  @NotNull
  private PayType payType;

  @NotNull
  private int tableId;

  private Integer customerId;

  @NotNull
  private int staffId;
}
