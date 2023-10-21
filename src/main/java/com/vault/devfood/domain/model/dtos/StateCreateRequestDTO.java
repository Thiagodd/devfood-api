package com.vault.devfood.domain.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StateCreateRequestDTO {

    @NotBlank(message = "{required.validation}")
    @Size(min = 4, max = 30, message = "{size.validation}")
    private String name;
}
