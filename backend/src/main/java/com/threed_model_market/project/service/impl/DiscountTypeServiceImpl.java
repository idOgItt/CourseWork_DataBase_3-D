package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.DiscountTypeDto;
import com.threed_model_market.project.exception_handler.exceptions.DiscountType.DiscountTypeNotFoundException;
import com.threed_model_market.project.model.DiscountType;
import com.threed_model_market.project.repository.DiscountTypeRepository;
import com.threed_model_market.project.service.DiscountTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountTypeServiceImpl implements DiscountTypeService {

    private final DiscountTypeRepository discountTypeRepository;

    public DiscountTypeServiceImpl(DiscountTypeRepository discountTypeRepository) {
        this.discountTypeRepository = discountTypeRepository;
    }

    @Override
    public DiscountType createDiscountType(DiscountTypeDto discountTypeDto) {
        DiscountType discountType = new DiscountType();
        discountType.setName(discountTypeDto.getName());
        discountType.setDescription(discountTypeDto.getDescription());
        return discountTypeRepository.save(discountType);
    }

    @Override
    public DiscountType getDiscountTypeById(Long id) {
        return discountTypeRepository.findById(id)
                .orElseThrow(() -> new DiscountTypeNotFoundException("DiscountType not found with ID: " + id));
    }

    @Override
    public DiscountType getDiscountTypeByName(String name) {
        return discountTypeRepository.findByName(name)
                .orElseThrow(() -> new DiscountTypeNotFoundException("DiscountType not found with name: " + name));
    }

    @Override
    public List<DiscountType> getAllDiscountTypes() {
        return discountTypeRepository.findAll();
    }

    @Override
    public void removeDiscountType(Long id) {
        if (!discountTypeRepository.existsById(id)) {
            throw new DiscountTypeNotFoundException("DiscountType not found with ID: " + id);
        }
        discountTypeRepository.deleteById(id);
    }
}
