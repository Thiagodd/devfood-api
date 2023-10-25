package com.vault.devfood.domain.service;

import com.vault.devfood.domain.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public interface CrudService<T extends BaseEntity, ID extends Serializable> {


    Page<T> findAll(Pageable pageable);

    T findById(ID id);

    T create(T entity);

    T update(ID id, T entity);

    void delete(ID id);
}
