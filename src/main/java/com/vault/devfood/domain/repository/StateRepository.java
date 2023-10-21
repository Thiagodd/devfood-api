package com.vault.devfood.domain.repository;

import com.vault.devfood.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StateRepository extends JpaRepository<State, UUID>{
}