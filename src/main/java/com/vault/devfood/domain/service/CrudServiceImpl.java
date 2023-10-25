package com.vault.devfood.domain.service;

import com.vault.devfood.domain.model.BaseEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

@Service
public abstract class CrudServiceImpl<T extends BaseEntity, ID extends Serializable> implements CrudService<T, ID> {

    protected abstract JpaRepository<T, ID> getRepository();

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public T findById(ID id) {
        Optional<T> existingEntity = getRepository().findById(id);
        if (existingEntity.isPresent()) {
            return existingEntity.get();
        } else {
            throw new EntityNotFoundException("Entity with ID " + id + " not found.");
        }
    }

    @Override
    public T create(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public T update(ID id, T entity) {
        Optional<T> existingEntity = getRepository().findById(id);
        if (existingEntity.isPresent()) {
            existingEntity.get().update(entity);
            return getRepository().save(existingEntity.get());
        } else {
            throw new EntityNotFoundException("Entity with ID " + id + " not found.");
        }
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }
}
