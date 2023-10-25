package com.vault.devfood.api.controller;

import com.vault.devfood.domain.model.BaseEntity;
import com.vault.devfood.domain.model.State;
import com.vault.devfood.domain.service.CrudService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.UUID;

public abstract class CrudController<T extends BaseEntity, ID extends Serializable> {

    public abstract CrudService<T, ID> getService();

    @PostMapping
    public ResponseEntity<T> create(@Valid @RequestBody T entity) {
        var entityCreated = getService().create(entity);
        URI location = createURI(entityCreated.getId());
        return ResponseEntity.created(location).body(entityCreated);
    }

    @GetMapping
    public ResponseEntity<Page<T>> findAll(Pageable pageable) {
        Page<T> page = getService().findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        getService().delete(id);
    }

    private URI createURI(UUID id){
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

}
