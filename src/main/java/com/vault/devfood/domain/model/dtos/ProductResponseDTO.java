package com.vault.devfood.domain.model.dtos;

import com.vault.devfood.domain.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link Product}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO implements Serializable {

    @NotNull(message = "{required.validation}")
    UUID id;

    @NotBlank(message = "{required.validation}")
    @Size(min = 4, max = 30, message = "{size.validation}")
    String name;

    String description;

    @PositiveOrZero(message = "{required.validation}")
    BigDecimal price;

    @NotNull(message = "{required.validation}")
    Boolean active;
}