package com.vault.devfood.domain.mapper;

import com.vault.devfood.domain.model.Restaurant;
import com.vault.devfood.domain.model.dtos.RestaurantCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.RestaurantFindResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {

    RestaurantFindResponseDTO toDto(Restaurant entity);

    List<RestaurantFindResponseDTO> toDto(List<Restaurant> entity);

    Restaurant toEntity(RestaurantCreateRequestDTO dto);

    Restaurant toEntity(RestaurantFindResponseDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Restaurant partialUpdate(RestaurantCreateRequestDTO dto, @MappingTarget Restaurant entity);


}