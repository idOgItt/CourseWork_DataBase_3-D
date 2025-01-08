package com.threed_model_market.project.service.impl.Views;

import com.threed_model_market.project.exception_handler.exceptions.Review.ReviewNotFoundException;
import com.threed_model_market.project.model.Views.VReviewsSummary;
import com.threed_model_market.project.repository.Views.VReviewsSummaryRepository;
import com.threed_model_market.project.service.Views.VReviewsSummaryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VReviewsSummaryServiceImpl implements VReviewsSummaryService {

    private final VReviewsSummaryRepository vReviewsSummaryRepository;

    public VReviewsSummaryServiceImpl(VReviewsSummaryRepository vReviewsSummaryRepository) {
        this.vReviewsSummaryRepository = vReviewsSummaryRepository;
    }

    @Override
    public List<VReviewsSummary> getAllReviews() {
        return vReviewsSummaryRepository.findAll();
    }

    @Override
    public VReviewsSummary getReviewById(Long id) {
        return vReviewsSummaryRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with ID " + id));
    }
}
