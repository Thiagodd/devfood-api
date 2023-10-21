package com.vault.devfood.api.controller;

import com.vault.devfood.domain.model.Cuisine;
import com.vault.devfood.domain.model.dtos.CuisineCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.CuisineResponseDTO;
import com.vault.devfood.domain.service.CuisineService;
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
@RequestMapping("/api/v1/cuisines")
public class CuisineController {

    private final CuisineService cuisineService;

    public CuisineController(CuisineService cuisineService) {
        this.cuisineService = cuisineService;
    }

    @GetMapping
    public ResponseEntity<Page<CuisineResponseDTO>> findAll(
        @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable){
        Page<CuisineResponseDTO> page = cuisineService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuisine> findById(@PathVariable UUID id){
        var cuisineResponseDTO = cuisineService.findCuisineById(id);

        return ResponseEntity.ok(cuisineResponseDTO);
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<CuisineResponseDTO> findByIdWithDTO(@PathVariable UUID id){
        var cuisineResponseDTO = cuisineService.findById(id);

        return ResponseEntity.ok(cuisineResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<CuisineResponseDTO> create(@Valid @RequestBody CuisineCreateRequestDTO requestDTO){
        CuisineResponseDTO cuisineResponseDTO = cuisineService.create(requestDTO);
        URI location = createURI(cuisineResponseDTO.getId());

        return ResponseEntity.created(location).body(cuisineResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuisineResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody CuisineCreateRequestDTO requestDTO){
        CuisineResponseDTO cuisineResponseDTO = cuisineService.update(id, requestDTO);
        URI location = createURI();

        return ResponseEntity.ok().location(location).body(cuisineResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        cuisineService.delete(id);

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
