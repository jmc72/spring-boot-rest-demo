package com.jmc.demo.controllers;

import com.jmc.demo.controllers.NoteController;
import com.jmc.demo.entities.Note;
import com.jmc.demo.error.NoteInvalidFormatException;
import com.jmc.demo.error.NoteNotFoundException;
import com.jmc.demo.repositories.NoteRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ControllerIntegrationTests {

  @Autowired
  NoteController noteController;

  @Test
  void contextLoads() {
  }

  @Test
  public void testFindAll() throws Exception {
    List<Note> notes = noteController.findAll();
    assertThat(notes.size(), is(2));
  }

  @Test
  public void testFindById() throws Exception {
    Note note = noteController.findById(1l);
    assertThat(note.getId(), is(1l));
  }

  @Test
  public void testCreateNote() throws Exception {
    String testDesc = "This is a test description";
    Note note = new Note(testDesc);

    noteController.createNote(note);
    List<Note> notes = noteController.findAll();
    assertThat(notes.size(), is(3));

    Note newNote = notes.get(2);
    assertThat(newNote.getId(), is(3l));
    assertThat(newNote.getCreationDate(), is(note.getCreationDate()));
    assertThat(newNote.getDescription(), is(testDesc));
  }

  @Test
  public void testUpdateNote() throws Exception {
    String testDesc = "This is a new test description";
    Long testId = 1l;

    Note note = noteController.findById(testId);
    note.setDescription(testDesc);

    noteController.updateNote(testId, note);

    List<Note> notes = noteController.findAll();
    assertThat(notes.size(), is(2));

    Note newNote = notes.get(0);
    assertThat(newNote.getId(), is(1l));
    assertThat(newNote.getCreationDate(), is(note.getCreationDate()));
    assertThat(newNote.getDescription(), is(testDesc));
  }

  @Test
  public void testDeleteNote() throws Exception {
    Long testId = 1l;

    noteController.deleteNote(testId);

    List<Note> notes = noteController.findAll();
    assertThat(notes.size(), is(1));
  }

  @Test
  public void getMissingNote() throws Exception {
    Long testId = 100l;

    Exception exception = assertThrows(NoteNotFoundException.class, () -> {
      noteController.findById(testId);
    });

    assertTrue(exception.getMessage().contains(testId.toString()));
  }

  @Test
  public void deleteMissingNote() throws Exception {
    Long testId = 100l;

    Exception exception = assertThrows(NoteNotFoundException.class, () -> {
      noteController.deleteNote(testId);
    });

    assertTrue(exception.getMessage().contains(testId.toString()));
  }

  @Test
  public void updateMissingNote() throws Exception {
    Long testId = 100l;
    Note updatedNote = new Note("updated note");

    Exception exception = assertThrows(NoteNotFoundException.class, () -> {
      noteController.updateNote(testId, updatedNote);
    });

    assertTrue(exception.getMessage().contains(testId.toString()));
  }


  @Test
  public void createBlankNote() throws Exception {
    Note updatedNote = new Note("");

    Exception exception = assertThrows(NoteInvalidFormatException.class, () -> {
      noteController.createNote(updatedNote);
    });
  }
}
