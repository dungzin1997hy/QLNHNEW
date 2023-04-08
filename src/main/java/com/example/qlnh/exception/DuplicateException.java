package com.example.qlnh.exception;

import javax.annotation.Nonnull;

public class DuplicateException extends RuntimeException{
  private static final long serialVersionUID = -2682891936269833225L;
  private static final String DEFAULT_MESSAGE = "Duplicate.";

  public DuplicateException() {
    super(DEFAULT_MESSAGE);
  }

  public DuplicateException(@Nonnull final String message) {
    super(message);
  }

  public DuplicateException(@Nonnull final String message, @Nonnull final Throwable cause) {
    super(message, cause);
  }

  public DuplicateException(@Nonnull final Throwable cause) {
    super(DEFAULT_MESSAGE, cause);
  }

}
