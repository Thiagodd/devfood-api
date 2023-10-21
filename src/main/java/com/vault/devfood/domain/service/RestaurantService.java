package com.vault.devfood.domain.service;

import com.vault.devfood.domain.exception.EntityInUseException;
import com.vault.devfood.domain.exception.ViolationBusinessRulesException;
import com.vault.devfood.domain.mapper.CuisineMapper;
import com.vault.devfood.domain.mapper.RestaurantMapper;
import com.vault.devfood.domain.model.Restaurant;
import com.vault.devfood.domain.model.dtos.CuisineResponseDTO;
import com.vault.devfood.domain.model.dtos.RestaurantCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.RestaurantFindResponseDTO;
import com.vault.devfood.domain.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RestaurantService {

    private static final String MSG_RESTAURANT_NOT_FOUND = "Restaurant with id '%s' not found. Please verify the provided entity name and ensure it exists in the system";
    private static final String MSG_RESTAURANT_IN_USE = "Restaurant with id '%s' in use. Check the entity name provided and ensure that no other entities depend on it.";


    private final RestaurantRepository restaurantRepository;
    private final CuisineService cuisineService;
    private final RestaurantMapper restaurantMapper;
    private final CuisineMapper cuisineMapper;

    public RestaurantService(RestaurantRepository restaurantRepository,
        RestaurantMapper restaurantMapper,
        CuisineService cuisineService,
        CuisineMapper cuisineMapper
    ) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
        this.cuisineService = cuisineService;
        this.cuisineMapper = cuisineMapper;
    }

    public Page<RestaurantFindResponseDTO> findAll(Pageable pageable){
        return restaurantRepository.findAll(pageable)
            .map(restaurantMapper::toDto);
    }

    public RestaurantFindResponseDTO findById(UUID id){
        return restaurantMapper.toDto(findRestaurantById(id));
    }

    public RestaurantFindResponseDTO create(@Valid RestaurantCreateRequestDTO requestDTO){
        try{
            Restaurant restaurantToSave = restaurantMapper.toEntity(requestDTO);
            CuisineResponseDTO cuisineResponseDTO = cuisineService.findById(requestDTO.getCuisineId());
            restaurantToSave.setCuisine(cuisineMapper.toEntity(cuisineResponseDTO));
            return restaurantMapper.toDto(restaurantRepository.save(restaurantToSave));
        }catch (EntityNotFoundException exception){
            throw new ViolationBusinessRulesException(exception.getMessage());
        }
    }

    public RestaurantFindResponseDTO update(final UUID id, final @Valid RestaurantCreateRequestDTO requestDTO) {
        Restaurant restaurantToUpdate = findRestaurantById(id);
        restaurantMapper.partialUpdate(requestDTO, restaurantToUpdate);

        if (requestDTO.getCuisineId() != null){
            CuisineResponseDTO restaurantFindResponseDTO = cuisineService.findById(requestDTO.getCuisineId());
            restaurantToUpdate.setCuisine(cuisineMapper.toEntity(restaurantFindResponseDTO));
        }

        return restaurantMapper.toDto(restaurantRepository.save(restaurantToUpdate));
    }

    public void delete(UUID id){
        try{
            restaurantRepository.deleteById(id);
            restaurantRepository.flush();
        }catch (EmptyResultDataAccessException exception){
            throw new EntityNotFoundException(String.format(MSG_RESTAURANT_NOT_FOUND, id));
        }catch (DataIntegrityViolationException exception){
            throw new EntityInUseException(String.format(MSG_RESTAURANT_IN_USE, id));
        }
    }


    private Restaurant findRestaurantById(UUID id) {
        return restaurantRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_RESTAURANT_NOT_FOUND, id)));
    }
}
