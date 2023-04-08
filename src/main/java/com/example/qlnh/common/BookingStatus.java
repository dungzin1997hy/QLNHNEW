package com.example.qlnh.common;

public enum BookingStatus {
  COMPLETED("completed"),
  IN_PROGRESS("in progress");
  private final String bookingStatus;

  BookingStatus(String bookingStatus) {
    this.bookingStatus = bookingStatus;
  }

  public String getValue() {
    return bookingStatus;
  }
}
