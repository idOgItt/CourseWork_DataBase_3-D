package com.threed_model_market.project.controller.Views;

import com.threed_model_market.project.dto.Views.VUserStatisticDto;
import com.threed_model_market.project.service.Views.VUserStatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-statistics")
public class VUserStatisticController {

    private final VUserStatisticService vUserStatisticService;

    public VUserStatisticController(VUserStatisticService vUserStatisticService) {
        this.vUserStatisticService = vUserStatisticService;
    }

    @PreAuthorize("hasAuthority('VIEW_USER_STATISTICS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<VUserStatisticDto>> getAllUserStatistics() {
        List<VUserStatisticDto> statistics = vUserStatisticService.getAllUserStatistics();
        return ResponseEntity.ok(statistics);
    }

    @PreAuthorize("hasAuthority('VIEW_USER_STATISTICS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<VUserStatisticDto> getUserStatisticById(@PathVariable Long id) {
        VUserStatisticDto statistic = vUserStatisticService.getUserStatisticById(id);
        return ResponseEntity.ok(statistic);
    }
}
