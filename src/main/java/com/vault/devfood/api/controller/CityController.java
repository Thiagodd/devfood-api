package com.vault.devfood.api.controller;

import com.vault.devfood.domain.model.dtos.CityCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.CityFindResponseDTO;
import com.vault.devfood.domain.service.CityService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static com.vault.devfood.api.util.UriUtils.createURI;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<Page<CityFindResponseDTO>> findAll(
        @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<CityFindResponseDTO> page = cityService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityFindResponseDTO> findById(@PathVariable UUID id) {
        CityFindResponseDTO CityFindResponseDTO = cityService.findById(id);

        return ResponseEntity.ok(CityFindResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<CityFindResponseDTO> create(@Valid @RequestBody CityCreateRequestDTO requestDTO) {
        CityFindResponseDTO cityFindResponseDTO = cityService.create(requestDTO);
        URI location = createURI(cityFindResponseDTO.getId());

        return ResponseEntity.created(location).body(cityFindResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityFindResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody CityCreateRequestDTO requestDTO) {
        CityFindResponseDTO CityFindResponseDTO = cityService.update(id, requestDTO);
        URI location = createURI();

        return ResponseEntity.ok().location(location).body(CityFindResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        cityService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
