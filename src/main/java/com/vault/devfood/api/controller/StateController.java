package com.vault.devfood.api.controller;

import com.vault.devfood.domain.model.State;
import com.vault.devfood.domain.service.CrudService;
import com.vault.devfood.domain.service.StateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/state")
public class StateController extends CrudController<State, UUID> {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @Override
    public CrudService<State, UUID> getService() {
        return this.stateService;
    }
}
