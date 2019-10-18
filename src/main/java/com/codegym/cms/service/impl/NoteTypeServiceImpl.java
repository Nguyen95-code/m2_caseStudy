package com.codegym.cms.service.impl;

import com.codegym.cms.model.Note;
import com.codegym.cms.model.NoteType;
import com.codegym.cms.repository.NoteRepository;
import com.codegym.cms.repository.NoteTypeRepository;
import com.codegym.cms.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class NoteTypeServiceImpl implements NoteTypeService {
    @Autowired
    NoteTypeRepository noteTypeRepository;
    @Autowired
    NoteRepository noteRepository;

    @Override
    public Page<NoteType> findAll(Pageable pageable) {
        return noteTypeRepository.findAll(pageable);
    }

    @Override
    public NoteType findById(Long id) {
        return noteTypeRepository.findOne(id);
    }

    @Override
    public void save(NoteType noteType) {
        noteTypeRepository.save(noteType);
    }

    @Override
    public void remove(Long id) {
        NoteType noteType = findById(id);
        List<Note> notes = (List<Note>) noteRepository.findAllByNoteType(noteType);
        for(int i=0; i<notes.size(); i++){
            noteRepository.delete(notes.get(i));
        }
        noteTypeRepository.delete(id);

    }

    @Override
    public Iterable<NoteType> findAll() {
        return noteTypeRepository.findAll();
    }
}
