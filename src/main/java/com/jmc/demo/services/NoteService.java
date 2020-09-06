package com.jmc.demo.services;

import com.jmc.demo.entities.Note;
import com.jmc.demo.error.NoteInvalidFormatException;
import com.jmc.demo.error.NoteNotFoundException;
import com.jmc.demo.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NoteService {
  @Autowired
  private NoteRepository repository;

  public List<Note> findAll() {
    return repository.findAll();
  }

  public Note findById(Long id) throws NoteNotFoundException {
    return repository.findById(id).orElseThrow(() -> new NoteNotFoundException(id.toString()));
  }

  public Note createNote(Note note) {
    if (note.getCreationDate() == null) {
      note.setCreationDate(LocalDateTime.now());
    }
    if (StringUtils.isEmpty(note.getDescription())) {
      throw new NoteInvalidFormatException(note.toString());
    }

    return repository.save(note);
  }

  public void deleteNote(Long id) throws NoteNotFoundException {
    Note note = repository.findById(id).orElseThrow(() -> new NoteNotFoundException(id.toString()));
    repository.delete(note);
  }

  public Note updateNote(Long id, Note newNote) throws NoteNotFoundException  {
    Note note = repository.findById(id).orElseThrow(() -> new NoteNotFoundException(id.toString()));
    if (StringUtils.isEmpty(newNote.getDescription())) {
      throw new NoteInvalidFormatException(newNote.toString());
    }

    note.setDescription(newNote.getDescription());
    return repository.save(note);
  }
}
