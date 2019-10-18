package com.codegym.cms.service;

import com.codegym.cms.model.NoteType;

public interface NoteTypeService extends GeneralService<NoteType> {
    Iterable<NoteType> findAll();
}