package com.threed_model_market.project.service.impl.Views;

import com.threed_model_market.project.dto.Views.VDiscountsAnalysisDto;
import com.threed_model_market.project.model.Views.VDiscountsAnalysis;
import com.threed_model_market.project.repository.Views.VDiscountsAnalysisRepository;
import com.threed_model_market.project.service.Views.VDiscountsAnalysisService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VDiscountsAnalysisServiceImpl implements VDiscountsAnalysisService {

    private final VDiscountsAnalysisRepository vDiscountsAnalysisRepository;

    public VDiscountsAnalysisServiceImpl(VDiscountsAnalysisRepository vDiscountsAnalysisRepository) {
        this.vDiscountsAnalysisRepository = vDiscountsAnalysisRepository;
    }

    @Override
    public List<VDiscountsAnalysisDto> getAllDiscountsAnalysis() {
        List<VDiscountsAnalysis> analysisList = vDiscountsAnalysisRepository.findAll();
        return analysisList.stream().map(analysis -> {
            VDiscountsAnalysisDto dto = new VDiscountsAnalysisDto();
            dto.setId(analysis.getId());
            dto.setDiscountCode(analysis.getDiscountCode());
            dto.setDiscountAmount(analysis.getDiscountAmount());
            dto.setDiscountType(analysis.getDiscountType());
            dto.setStartDate(analysis.getStartDate());
            dto.setEndDate(analysis.getEndDate());
            dto.setUsageLimit(analysis.getUsageLimit());
            dto.setOrdersUsingDiscount(analysis.getOrdersUsingDiscount());
            return dto;
        }).collect(Collectors.toList());
    }
}
