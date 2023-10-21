package com.vault.devfood.api.controller;

import com.vault.devfood.domain.model.dtos.PaymentMethodCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.PaymentMethodFindResponseDTO;
import com.vault.devfood.domain.service.PaymentMethodService;
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
@RequestMapping("/api/v1/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public ResponseEntity<Page<PaymentMethodFindResponseDTO>> findAll(
        @PageableDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<PaymentMethodFindResponseDTO> page = paymentMethodService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodFindResponseDTO> findById(@PathVariable UUID id){
        PaymentMethodFindResponseDTO paymentMethodFindResponseDTO = paymentMethodService.findById(id);
        return ResponseEntity.ok(paymentMethodFindResponseDTO);
    }

    @PostMapping
    public ResponseEntity<PaymentMethodFindResponseDTO> create(@Valid @RequestBody PaymentMethodCreateRequestDTO requestDTO){
        PaymentMethodFindResponseDTO paymentMethodFindResponseDTO = paymentMethodService.create(requestDTO);
        URI location = createURI(paymentMethodFindResponseDTO.getId());

        return ResponseEntity.created(location).body(paymentMethodFindResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodFindResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody PaymentMethodCreateRequestDTO requestDTO){
        PaymentMethodFindResponseDTO paymentMethodFindResponseDTO = paymentMethodService.update(id, requestDTO);
        URI location = createURI();

        return ResponseEntity.ok().location(location).body(paymentMethodFindResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentMethodFindResponseDTO> delete(@PathVariable UUID id){
        paymentMethodService.delete(id);

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
