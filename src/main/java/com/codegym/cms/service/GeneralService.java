package com.codegym.cms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GeneralService<E> {

    Page<E> findAll(Pageable pageable);

    E findById(Long id);

    void save(E e);

    void remove(Long id);


}
