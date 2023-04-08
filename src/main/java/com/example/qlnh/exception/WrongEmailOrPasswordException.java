package com.example.qlnh.exception;

import javax.annotation.Nonnull;

public class WrongEmailOrPasswordException extends RuntimeException {
  private static final long serialVersionUID = -2682891936269833225L;
  private static final String DEFAULT_MESSAGE = "Email or password is wrong.";

  public WrongEmailOrPasswordException() {
    super(DEFAULT_MESSAGE);
  }

  public WrongEmailOrPasswordException(@Nonnull final String message) {
    super(message);
  }

  public WrongEmailOrPasswordException(@Nonnull final String message, @Nonnull final Throwable cause) {
    super(message, cause);
  }

  public WrongEmailOrPasswordException(@Nonnull final Throwable cause) {
    super(DEFAULT_MESSAGE, cause);
  }
}
