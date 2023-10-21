package com.vault.devfood.domain.service;

import com.vault.devfood.domain.exception.EntityInUseException;
import com.vault.devfood.domain.mapper.PaymentMethodMapper;
import com.vault.devfood.domain.model.PaymentMethod;
import com.vault.devfood.domain.model.dtos.PaymentMethodCreateRequestDTO;
import com.vault.devfood.domain.model.dtos.PaymentMethodFindResponseDTO;
import com.vault.devfood.domain.repository.PaymentMethodRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentMethodService {

    private static final String MSG_PAYMENT_METHOD_NOT_FOUND = "PaymentMethod with id '%s' not found. Please verify the provided entity name and ensure it exists in the system";
    private static final String MSG_PAYMENT_METHOD_IN_USE = "PaymentMethod  with id '%s' in use. Check the entity name provided and ensure that no other entities depend on it.";

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository, PaymentMethodMapper paymentMethodMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentMethodMapper = paymentMethodMapper;
    }

    public Page<PaymentMethodFindResponseDTO> findAll(Pageable pageable){
        return paymentMethodRepository.findAll(pageable)
            .map(paymentMethodMapper::toDto);
    }

    public PaymentMethodFindResponseDTO findById(final UUID id){
        return paymentMethodMapper.toDto(findPaymentMethodById(id));
    }

    public PaymentMethodFindResponseDTO create(@Valid final PaymentMethodCreateRequestDTO requestDTO){
        PaymentMethod paymentMethodToSave = paymentMethodMapper.toEntity(requestDTO);
        return paymentMethodMapper.toDto(paymentMethodRepository.save(paymentMethodToSave));
    }

    public PaymentMethodFindResponseDTO update(final UUID id, final @Valid PaymentMethodCreateRequestDTO requestDTO){
        PaymentMethod paymentMethodToUpdate = findPaymentMethodById(id);
        paymentMethodMapper.partialUpdate(requestDTO, paymentMethodToUpdate);
        return paymentMethodMapper.toDto(paymentMethodRepository.save(paymentMethodToUpdate));
    }

    public void delete(UUID id){
        try{
            paymentMethodRepository.deleteById(id);
            paymentMethodRepository.flush();;
        }catch (EmptyResultDataAccessException exception){
            throw new EntityNotFoundException(String.format(MSG_PAYMENT_METHOD_NOT_FOUND, id));
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format(MSG_PAYMENT_METHOD_IN_USE, id));
        }
    }

    private PaymentMethod findPaymentMethodById(UUID id){
        return paymentMethodRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_PAYMENT_METHOD_NOT_FOUND, id)));
    }

}
