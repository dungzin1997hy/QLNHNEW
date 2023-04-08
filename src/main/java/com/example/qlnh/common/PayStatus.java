package com.example.qlnh.common;

public enum PayStatus {
  PAID("paid"),
  NOT_PAID("not paid");
  private final String payStatus;

  PayStatus(String payType) {
    this.payStatus = payType;
  }

  public String getValue() {
    return payStatus;
  }
}
