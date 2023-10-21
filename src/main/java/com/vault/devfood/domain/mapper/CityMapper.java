package com.vault.devfood.domain.mapper;

import com.vault.devfood.domain.model.City;
import com.vault.devfood.domain.model.dtos.CityCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.CityFindResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CityMapper {

    CityFindResponseDTO toDto(City entity);

    List<CityFindResponseDTO> toDto(List<City> entity);

    City toEntity(CityCreateRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    City partialUpdate(CityCreateRequestDTO dto, @MappingTarget City entity);
}