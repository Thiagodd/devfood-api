package com.vault.devfood.domain.repository;

import com.vault.devfood.domain.model.Cuisine;
import com.vault.devfood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    List<Restaurant> findAllByCuisineId(UUID id);
}