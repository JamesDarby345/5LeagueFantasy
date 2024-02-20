package com.example.LeagueFantasy.exception;

import org.springframework.http.HttpStatus;

public class FiveLeagueFantasyException extends RuntimeException {
  private final HttpStatus status;

  // Constructor to set the message and HTTP Status
  public FiveLeagueFantasyException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  // Getter
  public HttpStatus getStatus() {
    return this.status;
  }
}
