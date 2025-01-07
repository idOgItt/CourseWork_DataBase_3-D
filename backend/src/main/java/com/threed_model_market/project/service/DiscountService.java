package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.DiscountDto;
import com.threed_model_market.project.model.Discount;

import java.util.List;

public interface DiscountService {
    Discount createDiscount(DiscountDto discountDto);

    List<Discount> getAllDiscounts();

    Discount getDiscountById(Integer id);

    void removeDiscount(Integer id);
}
