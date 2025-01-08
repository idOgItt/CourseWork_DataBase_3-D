package com.threed_model_market.project.service.Views;

import com.threed_model_market.project.dto.Views.VLogsSummaryDto;

import java.time.Instant;
import java.util.List;

public interface VLogsSummaryService {
    List<VLogsSummaryDto> getAllLogs();
    VLogsSummaryDto getLogById(Long id);
    List<VLogsSummaryDto> getLogsByAction(String action);
    List<VLogsSummaryDto> getLogsByUsername(String username);
    List<VLogsSummaryDto> getLogsBetweenTimestamps(Instant start, Instant end);
}
