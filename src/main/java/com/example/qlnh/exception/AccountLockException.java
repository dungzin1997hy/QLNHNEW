package com.example.qlnh.exception;

import javax.annotation.Nonnull;

public class AccountLockException extends RuntimeException {
  private static final long serialVersionUID = -2682891936269833225L;
  private static final String DEFAULT_MESSAGE = "Your account is locked.";

  public AccountLockException() {
    super(DEFAULT_MESSAGE);
  }

  public AccountLockException(@Nonnull final String message) {
    super(message);
  }

  public AccountLockException(@Nonnull final String message, @Nonnull final Throwable cause) {
    super(message, cause);
  }

  public AccountLockException(@Nonnull final Throwable cause) {
    super(DEFAULT_MESSAGE, cause);
  }
}
