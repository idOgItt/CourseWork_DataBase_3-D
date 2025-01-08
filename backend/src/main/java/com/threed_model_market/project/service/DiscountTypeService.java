package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.DiscountTypeDto;
import com.threed_model_market.project.model.DiscountType;

import java.util.List;

public interface DiscountTypeService {
    DiscountType createDiscountType(DiscountTypeDto discountTypeDto);

    DiscountType getDiscountTypeById(Long id);

    @SuppressWarnings("unused")
    DiscountType getDiscountTypeByName(String name);

    List<DiscountType> getAllDiscountTypes();

    void removeDiscountType(Long id);
}
