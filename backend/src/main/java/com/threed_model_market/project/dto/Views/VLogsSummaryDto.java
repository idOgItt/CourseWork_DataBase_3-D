package com.threed_model_market.project.dto.Views;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class VLogsSummaryDto {

    private Long id;
    private Long logId;
    private String username;
    private String action;
    private String description;
    private Instant timestamp;

    public VLogsSummaryDto(Long id, Long logId, String username, String action, String description, Instant timestamp) {
        this.id = id;
        this.logId = logId;
        this.username = username;
        this.action = action;
        this.description = description;
        this.timestamp = timestamp;
    }
}
