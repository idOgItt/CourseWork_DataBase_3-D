package com.threed_model_market.project.service.impl.Views;

import com.threed_model_market.project.dto.Views.VLogsSummaryDto;
import com.threed_model_market.project.exception_handler.exceptions.Log.LogNotFoundException;
import com.threed_model_market.project.model.Views.VLogsSummary;
import com.threed_model_market.project.repository.Views.VLogsSummaryRepository;
import com.threed_model_market.project.service.Views.VLogsSummaryService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VLogsSummaryServiceImpl implements VLogsSummaryService {

    private final VLogsSummaryRepository vLogsSummaryRepository;

    public VLogsSummaryServiceImpl(VLogsSummaryRepository vLogsSummaryRepository) {
        this.vLogsSummaryRepository = vLogsSummaryRepository;
    }

    @Override
    public List<VLogsSummaryDto> getAllLogs() {
        List<VLogsSummary> logs = vLogsSummaryRepository.findAll();
        return logs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VLogsSummaryDto getLogById(Long id) {
        VLogsSummary log = vLogsSummaryRepository.findById(id)
                .orElseThrow(() -> new LogNotFoundException("Log not found with ID: " + id));
        return convertToDto(log);
    }

    @Override
    public List<VLogsSummaryDto> getLogsByAction(String action) {
        List<VLogsSummary> logs = vLogsSummaryRepository.findByAction(action);
        return logs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VLogsSummaryDto> getLogsByUsername(String username) {
        List<VLogsSummary> logs = vLogsSummaryRepository.findByUsername(username);
        return logs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VLogsSummaryDto> getLogsBetweenTimestamps(Instant start, Instant end) {
        List<VLogsSummary> logs = vLogsSummaryRepository.findByTimestampBetween(start, end);
        return logs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private VLogsSummaryDto convertToDto(VLogsSummary log) {
        return new VLogsSummaryDto(
                log.getId(),
                log.getLogId(),
                log.getUsername(),
                log.getAction(),
                log.getDescription(),
                log.getTimestamp()
        );
    }
}
