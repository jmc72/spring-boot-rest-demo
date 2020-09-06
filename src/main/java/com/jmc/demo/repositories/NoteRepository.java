package com.jmc.demo.repositories;

import com.jmc.demo.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
