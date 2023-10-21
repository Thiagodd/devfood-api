package com.vault.devfood.domain.service;

import com.vault.devfood.domain.exception.EntityInUseException;
import com.vault.devfood.domain.mapper.CuisineMapper;
import com.vault.devfood.domain.model.Cuisine;
import com.vault.devfood.domain.model.dtos.CuisineCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.CuisineResponseDTO;
import com.vault.devfood.domain.repository.CuisineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
public class CuisineService {

    private static final String MSG_CUISINE_NOT_FOUND = "Cuisine with id '%s' not found. Please verify the provided entity name and ensure it exists in the system";
    private static final String MSG_CUISINE_IN_USE = "Cuisine  with id '%s' in use. Check the entity name provided and ensure that no other entities depend on it.";

    private final CuisineRepository cuisineRepository;
    private final CuisineMapper cuisineMapper;

    public CuisineService(
        CuisineRepository cuisineRepository,
        CuisineMapper mapper
    ) {
        this.cuisineRepository = cuisineRepository;
        this.cuisineMapper = mapper;
    }

    public Page<CuisineResponseDTO> findAll(Pageable pageable) {
        return cuisineRepository.findAll(pageable)
            .map(cuisineMapper::toDto);
    }

    public CuisineResponseDTO findById(final UUID id) {
        return cuisineMapper.toDto(findCuisineById(id));
    }

    public CuisineResponseDTO create(final @Validated CuisineCreateRequestDTO requestDTO) {
        Cuisine newCuisine = cuisineMapper.toEntity(requestDTO);
        return cuisineMapper.toDto(cuisineRepository.save(newCuisine));
    }

    public CuisineResponseDTO update(final UUID id, final @Validated CuisineCreateRequestDTO requestDTO) {
        var cuisineToUpdate = findCuisineById(id);
        cuisineMapper.partialUpdate(requestDTO, cuisineToUpdate);
        return cuisineMapper.toDto(cuisineRepository.save(cuisineToUpdate));
    }

    public void delete(final UUID id) {
        try {
            cuisineRepository.deleteById(id);
            cuisineRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(String.format(MSG_CUISINE_NOT_FOUND, id));
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format(MSG_CUISINE_IN_USE, id));
        }
    }

    public Cuisine findCuisineById(UUID id) {

        return cuisineRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_CUISINE_NOT_FOUND, id)));
    }
}
