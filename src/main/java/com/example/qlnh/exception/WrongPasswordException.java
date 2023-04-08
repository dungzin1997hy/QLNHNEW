package com.example.qlnh.exception;

import javax.annotation.Nonnull;

public class WrongPasswordException extends RuntimeException {
  private static final long serialVersionUID = -2682891936269833225L;
  private static final String DEFAULT_MESSAGE = "Your old password is wrong.";

  public WrongPasswordException() {
    super(DEFAULT_MESSAGE);
  }

  public WrongPasswordException(@Nonnull final String message) {
    super(message);
  }

  public WrongPasswordException(@Nonnull final String message, @Nonnull final Throwable cause) {
    super(message, cause);
  }

  public WrongPasswordException(@Nonnull final Throwable cause) {
    super(DEFAULT_MESSAGE, cause);
  }
}
