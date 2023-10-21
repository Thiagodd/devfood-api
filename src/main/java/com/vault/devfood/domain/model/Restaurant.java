package com.vault.devfood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "restaurant")
public class Restaurant extends BaseEntityAudit{

    private String name;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    private Boolean active;

    private Boolean open;

    public Boolean isActive() {
        return active;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cuisine_id", nullable = false)
    private Cuisine cuisine;

    public Boolean isOpen() {
        return open;
    }
}
