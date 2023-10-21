package com.vault.devfood.api.controller;

import com.vault.devfood.domain.model.dtos.StateCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.StateFindResponseDTO;
import com.vault.devfood.domain.service.StateService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/states")
public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping
    public ResponseEntity<Page<StateFindResponseDTO>> findAll(
        @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<StateFindResponseDTO> page = stateService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateFindResponseDTO> findById(@PathVariable UUID id){
        StateFindResponseDTO stateFindResponseDTO = stateService.findById(id);
        return ResponseEntity.ok(stateFindResponseDTO);
    }

    @PostMapping
    public ResponseEntity<StateFindResponseDTO> create(@Valid @RequestBody StateCreateRequestDTO requestDTO){
        StateFindResponseDTO stateFindResponseDTO = stateService.create(requestDTO);
        URI location = createURI(stateFindResponseDTO.getId());

        return ResponseEntity.created(location).body(stateFindResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateFindResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody StateCreateRequestDTO requestDTO){
        StateFindResponseDTO stateFindResponseDTO = stateService.update(id, requestDTO);
        URI location = createURI();

        return ResponseEntity.ok().location(location).body(stateFindResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StateFindResponseDTO> delete(@PathVariable UUID id){
        stateService.delete(id);

        return ResponseEntity.noContent().build();
    }

    private URI createURI(UUID id){
        return ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    private URI createURI(){
        return ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
    }
}
