package com.example.qlnh.common;

public enum PayType {
  CASH("1"),
  CARD("0");
  private final String payType;

  PayType(String payType) {
    this.payType = payType;
  }

  public String getValue() {
    return payType;
  }
}
