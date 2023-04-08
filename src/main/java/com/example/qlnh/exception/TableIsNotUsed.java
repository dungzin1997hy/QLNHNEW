package com.example.qlnh.exception;

import javax.annotation.Nonnull;

public class TableIsNotUsed extends RuntimeException {
  private static final long serialVersionUID = -2682891936269833225L;
  private static final String DEFAULT_MESSAGE = "Table is not used.";

  public TableIsNotUsed() {
    super(DEFAULT_MESSAGE);
  }

  public TableIsNotUsed(@Nonnull final String message) {
    super(message);
  }

  public TableIsNotUsed(@Nonnull final String message, @Nonnull final Throwable cause) {
    super(message, cause);
  }

  public TableIsNotUsed(@Nonnull final Throwable cause) {
    super(DEFAULT_MESSAGE, cause);
  }
}
