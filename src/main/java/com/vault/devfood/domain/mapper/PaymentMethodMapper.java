package com.vault.devfood.domain.mapper;

import com.vault.devfood.domain.model.PaymentMethod;
import com.vault.devfood.domain.model.dtos.PaymentMethodCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.PaymentMethodFindResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PaymentMethodMapper {

    PaymentMethodFindResponseDTO toDto(PaymentMethod entity);

    List<PaymentMethodFindResponseDTO> toDto(List<PaymentMethod> entity);

    PaymentMethod toEntity(PaymentMethodCreateRequestDTO dto);

    PaymentMethod toEntity(PaymentMethodFindResponseDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PaymentMethod partialUpdate(PaymentMethodCreateRequestDTO dto, @MappingTarget PaymentMethod entity);
}