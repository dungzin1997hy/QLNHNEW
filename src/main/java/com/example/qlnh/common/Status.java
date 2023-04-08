package com.example.qlnh.common;

public enum Status {
  USING("using"),
  FREE("free");
  private final String status;

  Status(String status) {
    this.status = status;
  }

  public String getValue() {
    return status;
  }
}
