package com.example.qlnh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
@JsonPropertyOrder({"result", "message", "data"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class CommonRes<T> implements Serializable {
  private String result;
  private String message;
  private T data;
}
