package com.codegym.cms.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "types")
public class NoteType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String typeName;
    private String description;

    @OneToMany(mappedBy = "noteType")
    private Set<Note> notes;

    public NoteType(){
    }
    public NoteType(String typeName, String description){
        this.typeName = typeName;
        this.description = description;
    }
    public NoteType(Long id, String typeName, String description){
        this.id = id;
        this.typeName = typeName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
}
