package com.vault.devfood.api.controller;

import com.vault.devfood.domain.model.dtos.RestaurantCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.RestaurantFindResponseDTO;
import com.vault.devfood.domain.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.vault.devfood.api.util.UriUtils.createURI;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantFindResponseDTO>> findAll(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        var page = restaurantService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantFindResponseDTO> findById(@PathVariable UUID id) {
        var restaurantDTO = restaurantService.findById(id);
        return ResponseEntity.ok(restaurantDTO);
    }

    @PostMapping
    public ResponseEntity<RestaurantFindResponseDTO> create(@Valid @RequestBody RestaurantCreateRequestDTO requestDTO){
        var restaurantDTO = restaurantService.create(requestDTO);
        var location = createURI(restaurantDTO.getId());
        return ResponseEntity.created(location).body(restaurantDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantFindResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody RestaurantCreateRequestDTO requestDTO){
        var restaurantDTO = restaurantService.update(id, requestDTO);
        var location = createURI();
        return ResponseEntity.ok().location(location).body(restaurantDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
