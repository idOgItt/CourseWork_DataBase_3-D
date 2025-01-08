package com.threed_model_market.project.controller.Views;

import com.threed_model_market.project.dto.Views.VDiscountsAnalysisDto;
import com.threed_model_market.project.service.Views.VDiscountsAnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/discounts-analysis")
public class VDiscountsAnalysisController {

    private final VDiscountsAnalysisService vDiscountsAnalysisService;

    public VDiscountsAnalysisController(VDiscountsAnalysisService vDiscountsAnalysisService) {
        this.vDiscountsAnalysisService = vDiscountsAnalysisService;
    }

    @PreAuthorize("hasAuthority('VIEW_DISCOUNTS_ANALYSIS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<VDiscountsAnalysisDto>> getAllDiscountsAnalysis() {
        List<VDiscountsAnalysisDto> analysisList = vDiscountsAnalysisService.getAllDiscountsAnalysis();
        return ResponseEntity.ok(analysisList);
    }
}
