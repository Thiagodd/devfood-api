package com.vault.devfood.domain.model.dtos;

import com.vault.devfood.domain.model.Cuisine;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantCreateRequestDTO {


    @NotBlank(message = "{required.validation}")
    @Size(min = 4, max = 30, message = "{size.validation}")
    private String name;

    private BigDecimal deliveryFee;

    private Boolean active;

    private Boolean open;

    private UUID cuisineId;
}
