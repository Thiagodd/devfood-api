package com.vault.devfood.domain.model;

import com.vault.devfood.domain.model.dtos.ProductRequestDTO;
import com.vault.devfood.domain.model.dtos.ProductResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntityAudit {

    private String name;

    private String description;

    private BigDecimal price;

    private Boolean active;


    public Boolean isActive() {
        return active;
    }

    private Product(ProductRequestDTO dto) {
        this.setName(dto.getName());
        this.setDescription(dto.getDescription());
        this.setPrice(dto.getPrice());
        this.setActive(dto.isActive());
    }

    private Product(String name, String description, BigDecimal price, Boolean active) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
    }

    public static Product newProduct(ProductRequestDTO dto) {
        return new Product(dto);
    }

    public static Product newProduct(String name, String description, BigDecimal price, Boolean active){
        return new Product(name, description, price, active);
    }

    public static ProductResponseDTO newProductResponseDTO(Product entity) {
        return new ProductResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice(),
            entity.isActive()
        );
    }


}
