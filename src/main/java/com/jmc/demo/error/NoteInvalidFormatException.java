package com.jmc.demo.error;

public class NoteInvalidFormatException extends RuntimeException {
  public NoteInvalidFormatException(String message, Throwable e) {
    super("Invalid Note Format: " + message, e);
  }

  public NoteInvalidFormatException(String message) {
    super("Invalid Note Format: " + message);
  }

  public NoteInvalidFormatException() {
    super("Invalid Note Format");
  }
}
