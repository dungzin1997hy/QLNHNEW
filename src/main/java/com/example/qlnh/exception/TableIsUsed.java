package com.example.qlnh.exception;

import javax.annotation.Nonnull;

public class TableIsUsed extends RuntimeException {
  private static final long serialVersionUID = -2682891936269833225L;
  private static final String DEFAULT_MESSAGE = "Table is used.";

  public TableIsUsed() {
    super(DEFAULT_MESSAGE);
  }

  public TableIsUsed(@Nonnull final String message) {
    super(message);
  }

  public TableIsUsed(@Nonnull final String message, @Nonnull final Throwable cause) {
    super(message, cause);
  }

  public TableIsUsed(@Nonnull final Throwable cause) {
    super(DEFAULT_MESSAGE, cause);
  }
}
