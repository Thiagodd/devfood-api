package com.vault.devfood.domain.mapper;

import com.vault.devfood.domain.model.Permission;
import com.vault.devfood.domain.model.dtos.PermissionRequestDTO;
import com.vault.devfood.domain.model.dtos.PermissionResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PermissionMapper {

    PermissionResponseDTO toDto(Permission entity);

    List<PermissionResponseDTO> toDto(List<Permission> entity);

    Permission toEntity(PermissionRequestDTO dto);

    Permission toEntity(PermissionResponseDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Permission partialUpdate(PermissionRequestDTO dto, @MappingTarget Permission entity);
}