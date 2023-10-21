package com.vault.devfood.domain.model.dtos;

import com.vault.devfood.domain.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Product}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO implements Serializable {

    @NotBlank(message = "{required.validation}")
    @Size(min = 4, max = 30, message = "{size.validation}")
    String name;

    String description;

    @PositiveOrZero(message = "{required.validation}")
    BigDecimal price;

    @NotNull(message = "{required.validation}")
    Boolean active;


    public Boolean isActive() {
        return active;
    }
}