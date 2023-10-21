package com.vault.devfood.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment_method")
public class PaymentMethod extends BaseEntityAudit{

    private String description;

    private PaymentMethod(String description) {
        this.description = description;
    }

    public PaymentMethod newPaymentMethod(){
        return null;
    }


}
