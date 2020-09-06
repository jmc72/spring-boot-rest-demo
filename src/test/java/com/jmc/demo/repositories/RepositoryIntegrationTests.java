package com.jmc.demo.repositories;

import com.jmc.demo.entities.Note;
import com.jmc.demo.repositories.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class RepositoryIntegrationTests {

  @Autowired
  NoteRepository repository;

  @Test
  void contextLoads() {
  }

  @Test
  public void testGetNotes() throws Exception {
    List<Note> notes = repository.findAll();
    assertThat(notes.size(), is(2));
  }

  @Test
  public void testGetNote() throws Exception {
    Optional<Note> optionalNote = repository.findById(1l);
    assertTrue(optionalNote.isPresent());
    assertThat(optionalNote.get().getId(), is(1l));
  }

  @Test
  public void testCreateNote() throws Exception {
    String testDesc = "This is a test description";
    Note note = new Note(testDesc);

    repository.save(note);
    List<Note> notes = repository.findAll();
    assertThat(notes.size(), is(3));

    Note newNote = notes.get(2);
    assertThat(newNote.getId(), is(3l));
    assertThat(newNote.getCreationDate(), is(note.getCreationDate()));
    assertThat(newNote.getDescription(), is(testDesc));
  }

  @Test
  public void testUpdateNote() throws Exception {
    String testDesc = "This is a new test description";

    Note note = repository.findById(1l).get();
    note.setDescription(testDesc);

    repository.save(note);

    List<Note> notes = repository.findAll();
    assertThat(notes.size(), is(2));

    Note newNote = notes.get(0);
    assertThat(newNote.getId(), is(1l));
    assertThat(newNote.getCreationDate(), is(note.getCreationDate()));
    assertThat(newNote.getDescription(), is(testDesc));
  }

  @Test
  public void testDeleteNote() throws Exception {
    Note note = repository.findById(1l).get();
    repository.delete(note);

    List<Note> notes = repository.findAll();
    assertThat(notes.size(), is(1));

    Optional<Note> deletedNote = repository.findById(1l);
    assertFalse(deletedNote.isPresent());
  }
}
