package com.vault.devfood.domain.repository;

import com.vault.devfood.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID>{
}