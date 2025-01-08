package com.threed_model_market.project.model.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.Instant;

/**
 * Logs for admin panel
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_logs_summary")
public class VLogsSummary {

    @Id
    @Column(name = "\"ID\"")
    private Long id;

    @Column(name = "logid")
    private Long logId;

    @Column(name = "\"Username\"", length = 50)
    private String username;

    @Column(name = "\"Action\"", length = 50)
    private String action;

    @Column(name = "description", length = 800)
    private String description;

    @Column(name = "timestamp")
    private Instant timestamp;
}
