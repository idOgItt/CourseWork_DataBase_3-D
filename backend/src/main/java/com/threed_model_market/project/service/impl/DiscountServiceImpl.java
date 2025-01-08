package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.DiscountDto;
import com.threed_model_market.project.exception_handler.exceptions.Discount.DiscountNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.DiscountType.DiscountTypeNotFoundException;
import com.threed_model_market.project.model.Discount;
import com.threed_model_market.project.model.DiscountType;
import com.threed_model_market.project.repository.DiscountRepository;
import com.threed_model_market.project.repository.DiscountTypeRepository;
import com.threed_model_market.project.service.DiscountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final DiscountTypeRepository discountTypeRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository, DiscountTypeRepository discountTypeRepository) {
        this.discountRepository = discountRepository;
        this.discountTypeRepository = discountTypeRepository;
    }

    @Override
    public Discount createDiscount(DiscountDto discountDto) {
        DiscountType discountType = discountTypeRepository.findByName(discountDto.getDiscountTypeName())
                .orElseThrow(() -> new DiscountTypeNotFoundException("DiscountType not found"));

        Discount discount = new Discount();
        discount.setCode(discountDto.getCode());
        discount.setDiscountAmount(discountDto.getDiscountAmount());
        discount.setDiscountType(discountType);
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
    public Discount getDiscountById(Long id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFoundException("Discount not found"));
    }

    @Override
    public Discount getDiscountByCode(String code) {
        return discountRepository.findByCode(code)
                .orElseThrow(() -> new DiscountNotFoundException("Discount not found with code: " + code));
    }

    @Override
    public void removeDiscount(Long id) {
        if (!discountRepository.existsById(id)) {
            throw new DiscountNotFoundException("Discount not found with ID: " + id);
        }
        discountRepository.deleteById(id);
    }
}
