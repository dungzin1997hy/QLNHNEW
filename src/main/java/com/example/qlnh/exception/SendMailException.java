package com.example.qlnh.exception;

import javax.annotation.Nonnull;

public class SendMailException extends RuntimeException {
  private static final long serialVersionUID = -2682891936269833225L;
  private static final String DEFAULT_MESSAGE = "Error when sending mail";

  public SendMailException() {
    super(DEFAULT_MESSAGE);
  }

  public SendMailException(@Nonnull final String message) {
    super(message);
  }

  public SendMailException(@Nonnull final String message, @Nonnull final Throwable cause) {
    super(message, cause);
  }

  public SendMailException(@Nonnull final Throwable cause) {
    super(DEFAULT_MESSAGE, cause);
  }
}
