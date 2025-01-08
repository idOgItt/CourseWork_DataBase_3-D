package com.threed_model_market.project.controller.Views;

import com.threed_model_market.project.dto.Views.VLogsSummaryDto;
import com.threed_model_market.project.service.Views.VLogsSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;


@RestController
@RequestMapping("/api/logs-summary")
public class VLogsSummaryController {

    private final VLogsSummaryService vLogsSummaryService;

    public VLogsSummaryController(VLogsSummaryService vLogsSummaryService) {
        this.vLogsSummaryService = vLogsSummaryService;
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<VLogsSummaryDto>> getAllLogs() {
        List<VLogsSummaryDto> logs = vLogsSummaryService.getAllLogs();
        return ResponseEntity.ok(logs);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<VLogsSummaryDto> getLogById(@PathVariable Long id) {
        VLogsSummaryDto logDto = vLogsSummaryService.getLogById(id);
        return ResponseEntity.ok(logDto);
    }


    @GetMapping("/action/{action}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<VLogsSummaryDto>> getLogsByAction(@PathVariable String action) {
        List<VLogsSummaryDto> logs = vLogsSummaryService.getLogsByAction(action);
        return ResponseEntity.ok(logs);
    }


    @GetMapping("/user/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<VLogsSummaryDto>> getLogsByUsername(@PathVariable String username) {
        List<VLogsSummaryDto> logs = vLogsSummaryService.getLogsByUsername(username);
        return ResponseEntity.ok(logs);
    }


    @GetMapping("/between")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<VLogsSummaryDto>> getLogsBetweenTimestamps(
            @RequestParam Instant start,
            @RequestParam Instant end) {
        List<VLogsSummaryDto> logs = vLogsSummaryService.getLogsBetweenTimestamps(start, end);
        return ResponseEntity.ok(logs);
    }
}
