package com.vault.devfood.api.controller;

import com.vault.devfood.domain.model.dtos.StateCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.StateFindResponseDTO;
import com.vault.devfood.domain.service.StateServiceAntigo;
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
@RequestMapping("/api/v1/states2")
public class StateController2 {

    private final StateServiceAntigo stateServiceAntigo;

    public StateController2(StateServiceAntigo stateServiceAntigo) {
        this.stateServiceAntigo = stateServiceAntigo;
    }

    @GetMapping
    public ResponseEntity<Page<StateFindResponseDTO>> findAll(
        @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<StateFindResponseDTO> page = stateServiceAntigo.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateFindResponseDTO> findById(@PathVariable UUID id){
        StateFindResponseDTO stateFindResponseDTO = stateServiceAntigo.findById(id);
        return ResponseEntity.ok(stateFindResponseDTO);
    }

    @PostMapping
    public ResponseEntity<StateFindResponseDTO> create(@Valid @RequestBody StateCreateRequestDTO requestDTO){
        StateFindResponseDTO stateFindResponseDTO = stateServiceAntigo.create(requestDTO);
        URI location = createURI(stateFindResponseDTO.getId());

        return ResponseEntity.created(location).body(stateFindResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateFindResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody StateCreateRequestDTO requestDTO){
        StateFindResponseDTO stateFindResponseDTO = stateServiceAntigo.update(id, requestDTO);
        URI location = createURI();

        return ResponseEntity.ok().location(location).body(stateFindResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StateFindResponseDTO> delete(@PathVariable UUID id){
        stateServiceAntigo.delete(id);

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
