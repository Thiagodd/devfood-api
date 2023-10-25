package com.vault.devfood.domain.model.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PermissionResponseDTO {

    private UUID id;
    private String name;
    private String description;
}
