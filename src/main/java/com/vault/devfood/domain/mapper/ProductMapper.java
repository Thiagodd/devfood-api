package com.vault.devfood.domain.mapper;

import com.vault.devfood.domain.model.Product;
import com.vault.devfood.domain.model.dtos.ProductRequestDTO;
import com.vault.devfood.domain.model.dtos.ProductResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {

    ProductResponseDTO toDto(Product entity);

    List<ProductResponseDTO> toDto(List<Product> entity);

    Product toEntity(ProductRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductRequestDTO dto, @MappingTarget Product entity);
}