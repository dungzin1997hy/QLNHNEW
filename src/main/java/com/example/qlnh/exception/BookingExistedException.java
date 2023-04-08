package com.example.qlnh.exception;

import java.io.Serial;
import javax.annotation.Nonnull;

public class BookingExistedException extends RuntimeException {
  @Serial private static final long serialVersionUID = -2682891936269833225L;
  private static final String DEFAULT_MESSAGE = "Table in this time is booked";

  public BookingExistedException() {
    super(DEFAULT_MESSAGE);
  }

  public BookingExistedException(@Nonnull final String message) {
    super(message);
  }

  public BookingExistedException(@Nonnull final String message, @Nonnull final Throwable cause) {
    super(message, cause);
  }

  public BookingExistedException(@Nonnull final Throwable cause) {
    super(DEFAULT_MESSAGE, cause);
  }
}
