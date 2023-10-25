package com.vault.devfood.domain.service;

import com.vault.devfood.domain.exception.EntityInUseException;
import com.vault.devfood.domain.exception.ViolationBusinessRulesException;
import com.vault.devfood.domain.mapper.CityMapper;
import com.vault.devfood.domain.mapper.StateMapper;
import com.vault.devfood.domain.model.City;
import com.vault.devfood.domain.model.dtos.CityCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.CityFindResponseDTO;
import com.vault.devfood.domain.model.dtos.StateFindResponseDTO;
import com.vault.devfood.domain.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CityService {

    private static final String MSG_CITY_NOT_FOUND = "City with id '%s' not found. Please verify the provided entity name and ensure it exists in the system";
    private static final String MSG_CITY_IN_USE = "City  with id '%s' in use. Check the entity name provided and ensure that no other entities depend on it.";


    private final CityRepository cityRepository;
    private final StateServiceAntigo stateServiceAntigo;
    private final CityMapper cityMapper;
    private final StateMapper stateMapper;

    public CityService(CityRepository cityRepository,
        StateServiceAntigo stateServiceAntigo,
        CityMapper cityMapper,
        StateMapper stateMapper
    ) {
        this.cityRepository = cityRepository;
        this.stateServiceAntigo = stateServiceAntigo;
        this.cityMapper = cityMapper;
        this.stateMapper = stateMapper;
    }

    public Page<CityFindResponseDTO> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable)
            .map(cityMapper::toDto);
    }

    public CityFindResponseDTO findById(UUID id) {
        return cityMapper.toDto(findCityById(id));
    }

    public CityFindResponseDTO create(@Valid final CityCreateRequestDTO requestDTO) {
        try{
            City cityToSave = cityMapper.toEntity(requestDTO);
            StateFindResponseDTO stateFindResponseDTO = stateServiceAntigo.findById(requestDTO.getStateId());
            cityToSave.setState(stateMapper.toEntity(stateFindResponseDTO));
            return cityMapper.toDto(cityRepository.save(cityToSave));
        }catch (EntityNotFoundException exception){
            throw new ViolationBusinessRulesException(exception.getMessage());
        }
    }

    public CityFindResponseDTO update(final UUID id, final @Valid CityCreateRequestDTO requestDTO) {
        City cityToUpdate = findCityById(id);
        cityMapper.partialUpdate(requestDTO, cityToUpdate);

        if (requestDTO.getStateId() != null){
            StateFindResponseDTO stateFindResponseDTO = stateServiceAntigo.findById(requestDTO.getStateId());
            cityToUpdate.setState(stateMapper.toEntity(stateFindResponseDTO));
        }

        return cityMapper.toDto(cityRepository.save(cityToUpdate));
    }

    public void delete(UUID id) {
        try {
            cityRepository.deleteById(id);
            cityRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(String.format(MSG_CITY_NOT_FOUND, id));
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format(MSG_CITY_IN_USE, id));
        }
    }

    private City findCityById(UUID id) {
        return cityRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_CITY_NOT_FOUND, id)));
    }
}
