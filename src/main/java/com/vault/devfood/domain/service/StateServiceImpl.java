package com.vault.devfood.domain.service;

import com.vault.devfood.domain.model.State;
import com.vault.devfood.domain.repository.StateRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StateServiceImpl extends CrudServiceImpl<State, UUID> implements StateService {

    private final StateRepository repository;

    public StateServiceImpl(StateRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<State, UUID> getRepository() {
        return this.repository;
    }
}
