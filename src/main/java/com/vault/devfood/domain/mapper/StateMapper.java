package com.vault.devfood.domain.mapper;

import com.vault.devfood.domain.model.State;
import com.vault.devfood.domain.model.dtos.StateCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.StateFindResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StateMapper {

    StateFindResponseDTO toDto(State entity);

    List<StateFindResponseDTO> toDto(List<State> entity);

    State toEntity(StateCreateRequestDTO dto);

    State toEntity(StateFindResponseDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    State partialUpdate(StateCreateRequestDTO dto, @MappingTarget State entity);
}