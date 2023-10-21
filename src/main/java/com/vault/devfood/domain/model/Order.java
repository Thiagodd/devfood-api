package com.vault.devfood.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order extends BaseEntityAudit{

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @Column(name = "value_total")
    private BigDecimal totalValue;

    @Column(name = "cancellation_date")
    private LocalDateTime confirmationDate;

    @Column(name = "confirmation_date")
    private LocalDateTime cancellationDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;



}
