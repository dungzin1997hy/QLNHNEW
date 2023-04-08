package com.example.qlnh.exception;

import javax.annotation.Nonnull;

public class NotFoundException extends RuntimeException {
  private static final long serialVersionUID = -2682891936269833225L;
  private static final String DEFAULT_MESSAGE = "Not Found.";

  public NotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  public NotFoundException(@Nonnull final String message) {
    super(message);
  }

  public NotFoundException(@Nonnull final String message, @Nonnull final Throwable cause) {
    super(message, cause);
  }

  public NotFoundException(@Nonnull final Throwable cause) {
    super(DEFAULT_MESSAGE, cause);
  }
}
