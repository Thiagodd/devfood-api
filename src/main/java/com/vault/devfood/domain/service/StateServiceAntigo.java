package com.vault.devfood.domain.service;

import com.vault.devfood.domain.exception.EntityInUseException;
import com.vault.devfood.domain.mapper.StateMapper;
import com.vault.devfood.domain.model.State;
import com.vault.devfood.domain.model.dtos.StateCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.StateFindResponseDTO;
import com.vault.devfood.domain.repository.StateRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StateServiceAntigo {

    private static final String MSG_STATE_NOT_FOUND = "State with id '%s' not found. Please verify the provided entity name and ensure it exists in the system";
    private static final String MSG_STATE_IN_USE = "State  with id '%s' in use. Check the entity name provided and ensure that no other entities depend on it.";

    private final StateRepository stateRepository;
    private final StateMapper stateMapper;

    public StateServiceAntigo(StateRepository stateRepository, StateMapper stateMapper) {
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
    }

    public Page<StateFindResponseDTO> findAll(Pageable pageable){
        return stateRepository.findAll(pageable)
            .map(stateMapper::toDto);
    }

    public StateFindResponseDTO findById(final UUID id){
        return stateMapper.toDto(findStateById(id));
    }

    public StateFindResponseDTO create(@Valid final StateCreateRequestDTO requestDTO){
        State stateToSave = stateMapper.toEntity(requestDTO);
        return stateMapper.toDto(stateRepository.save(stateToSave));
    }

    public StateFindResponseDTO update(final UUID id, final @Valid StateCreateRequestDTO requestDTO){
        State stateToUpdate = findStateById(id);
        stateMapper.partialUpdate(requestDTO, stateToUpdate);
        return stateMapper.toDto(stateRepository.save(stateToUpdate));
    }

    public void delete(UUID id){
        try{
            stateRepository.deleteById(id);
            stateRepository.flush();;
        }catch (EmptyResultDataAccessException exception){
            throw new EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, id));
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format(MSG_STATE_IN_USE, id));
        }
    }

    private State findStateById(UUID id){
        return stateRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_STATE_NOT_FOUND, id)));
    }

}
