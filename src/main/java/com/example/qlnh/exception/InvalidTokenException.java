package com.example.qlnh.exception;

import javax.annotation.Nonnull;

public class InvalidTokenException extends RuntimeException {
  private static final long serialVersionUID = -2682891936269833225L;
  private static final String DEFAULT_MESSAGE = "Invalid token!!";

  public InvalidTokenException() {
    super(DEFAULT_MESSAGE);
  }

  public InvalidTokenException(@Nonnull final String message) {
    super(message);
  }

  public InvalidTokenException(@Nonnull final String message, @Nonnull final Throwable cause) {
    super(message, cause);
  }

  public InvalidTokenException(@Nonnull final Throwable cause) {
    super(DEFAULT_MESSAGE, cause);
  }
}
