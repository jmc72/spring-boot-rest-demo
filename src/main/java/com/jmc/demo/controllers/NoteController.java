package com.jmc.demo.controllers;

import com.jmc.demo.entities.Note;
import com.jmc.demo.error.NoteNotFoundException;
import com.jmc.demo.repositories.NoteRepository;
import com.jmc.demo.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {
  @Autowired
  NoteService noteService;

  @GetMapping("/notes")
  List<Note> findAll() {
    return noteService.findAll();
  }

  @GetMapping("/notes/{id}")
  Note findById(@PathVariable Long id) {
    return noteService.findById(id);
  }

  @PostMapping("/notes")
  Note createNote(@RequestBody Note note) {
    return noteService.createNote(note);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/notes/{id}")
  void deleteNote(@PathVariable Long id) {
    noteService.deleteNote(id);
  }

  @PutMapping("/notes/{id}")
  Note updateNote(@PathVariable Long id, @RequestBody Note note) {
    return noteService.updateNote(id, note);
  }
}
