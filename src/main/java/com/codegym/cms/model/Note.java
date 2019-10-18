package com.codegym.cms.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "noteType_id")
    private NoteType noteType;

    public Note() {
    }

    public Note(String title, String content, NoteType noteType) {
        this.title = title;
        this.content = content;
        this.noteType = noteType;
    }

    public Note(Long id, String title, String content, NoteType noteType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.noteType = noteType;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", noteType=" + noteType +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public void setNoteType(NoteType noteType) {
        this.noteType = noteType;
    }
}
