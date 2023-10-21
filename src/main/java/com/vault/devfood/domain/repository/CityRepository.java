package com.vault.devfood.domain.repository;

import com.vault.devfood.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {

}
