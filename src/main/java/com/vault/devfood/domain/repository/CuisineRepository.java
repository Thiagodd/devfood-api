package com.vault.devfood.domain.repository;

import com.vault.devfood.domain.model.Cuisine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, UUID> {

    Optional<Cuisine> findByName(final String name);

    @Query(value = "SELECT p FROM Cuisine p ")
    Page<Cuisine> findAllCuisines(final Pageable pageable);

    @Query(value = "SELECT p FROM Cuisine p WHERE p.name LIKE %:keyword%")
    Page<Cuisine> findAllCuisines(@Param("keyword") String keyword, Pageable pageable);
}
