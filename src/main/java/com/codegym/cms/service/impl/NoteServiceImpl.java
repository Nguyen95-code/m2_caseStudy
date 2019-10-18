package com.codegym.cms.service.impl;

import com.codegym.cms.model.Note;
import com.codegym.cms.model.NoteType;
import com.codegym.cms.repository.NoteRepository;
import com.codegym.cms.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteRepository noteRepository;

    @Override
    public Page<Note> findAll(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public Note findById(Long id) {
        return noteRepository.findOne(id);
    }

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void remove(Long id) {
        noteRepository.delete(id);
    }

    @Override
    public Page<Note> findAllByTitleContaining(String name, Pageable pageable) {
        return noteRepository.findAllByTitleContaining(name,pageable);
    }

    @Override
    public List<Integer> getNumberPage(Page<Note> notes) {
        int notePage = notes.getTotalPages();
        List<Integer> notePages = new ArrayList<>();
        for (int i = 0; i < notePage; i++) {
            notePages.add(i);
        }
        return notePages;
    }

    @Override
    public Iterable<Note> findAllByNoteType(NoteType noteType) {
        return noteRepository.findAllByNoteType(noteType);
    }
}
