package com.jmc.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Note {

  @Transient
  static private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime creationDate;

  private String description;

  public Note() {}

  public Note(String description) {
    this.description = description;
    this.creationDate = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public String toString() {
    String formattedDate = creationDate == null ? "null" : creationDate.format(dateFormatter);
    return String.format("id: %d created: %s desc: %s", id, formattedDate, description);
  }
}
