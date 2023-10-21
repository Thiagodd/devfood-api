package com.vault.devfood.domain.mapper;

import com.vault.devfood.domain.model.Cuisine;
import com.vault.devfood.domain.model.dtos.CuisineCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.CuisineResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CuisineMapper {

    CuisineResponseDTO toDto(Cuisine entity);

    List<CuisineResponseDTO> toDto(List<Cuisine> entity);

    Cuisine toEntity(CuisineCreateRequestDTO dto);

    Cuisine toEntity(CuisineResponseDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cuisine partialUpdate(CuisineCreateRequestDTO dto, @MappingTarget Cuisine entity);
}