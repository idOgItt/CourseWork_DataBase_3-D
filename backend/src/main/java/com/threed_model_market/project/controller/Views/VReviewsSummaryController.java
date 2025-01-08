package com.threed_model_market.project.controller.Views;

import com.threed_model_market.project.dto.Views.VReviewsSummaryDto;
import com.threed_model_market.project.model.Views.VReviewsSummary;
import com.threed_model_market.project.service.Views.VReviewsSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews-summary")
public class VReviewsSummaryController {

    private final VReviewsSummaryService vReviewsSummaryService;

    public VReviewsSummaryController(VReviewsSummaryService vReviewsSummaryService) {
        this.vReviewsSummaryService = vReviewsSummaryService;
    }

    @PreAuthorize("hasAuthority('VIEW_REVIEWS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<VReviewsSummaryDto>> getAllReviews() {
        List<VReviewsSummary> reviewsSummary = vReviewsSummaryService.getAllReviews();
        List<VReviewsSummaryDto> dtoList = reviewsSummary.stream()
                .map(review -> new VReviewsSummaryDto(
                        review.getId(),
                        review.getReviewId(),
                        review.getReviewer(),
                        review.getModelName(),
                        review.getReviewText(),
                        review.getRating(),
                        review.getReviewDate()
                ))
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @PreAuthorize("hasAuthority('VIEW_REVIEWS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<VReviewsSummaryDto> getReviewById(@PathVariable Long id) {
        VReviewsSummary reviewSummary = vReviewsSummaryService.getReviewById(id);
        VReviewsSummaryDto dto = new VReviewsSummaryDto(
                reviewSummary.getId(),
                reviewSummary.getReviewId(),
                reviewSummary.getReviewer(),
                reviewSummary.getModelName(),
                reviewSummary.getReviewText(),
                reviewSummary.getRating(),
                reviewSummary.getReviewDate()
        );
        return ResponseEntity.ok(dto);
    }
}
