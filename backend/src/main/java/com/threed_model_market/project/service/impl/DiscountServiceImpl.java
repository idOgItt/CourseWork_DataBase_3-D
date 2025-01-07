package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.DiscountDto;
import com.threed_model_market.project.exception_handler.exceptions.Discount.DiscountNotFoundException;
import com.threed_model_market.project.model.Discount;
import com.threed_model_market.project.repository.DiscountRepository;
import com.threed_model_market.project.service.DiscountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Discount createDiscount(DiscountDto discountDto) {
        Discount discount = new Discount();
        discount.setCode(discountDto.getCode());
        discount.setDiscountAmount(discountDto.getDiscountAmount());
        discount.setDiscountType(discountDto.getDiscountType());
        discount.setStartDate(discountDto.getStartDate());
        discount.setEndDate(discountDto.getEndDate());
        discount.setUsageLimit(discountDto.getUsageLimit());
        return discountRepository.save(discount);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount getDiscountById(Integer id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFoundException("Discount not found"));
    }

    @Override
    public void removeDiscount(Integer id) {
        discountRepository.deleteById(id);
    }
}
