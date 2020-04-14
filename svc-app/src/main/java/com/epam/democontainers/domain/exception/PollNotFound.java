package com.epam.democontainers.domain.exception;

public class PollNotFound extends RuntimeException {

  public PollNotFound() {
    super("Poll doesn't found");
  }
}
