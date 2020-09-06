package com.jmc.demo.error;

public class NoteNotFoundException extends RuntimeException {
  public NoteNotFoundException(String message, Throwable e) {
    super("Note not found: " + message, e);
  }

  public NoteNotFoundException(String message) {
    super("Note not found: " + message);
  }

  public NoteNotFoundException() {
    super("Note not found");
  }
}
