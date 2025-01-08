package com.threed_model_market.project.model.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_user_statistics")
public class VUserStatistic {

    @Id
    @Column(name = "\"ID\"")
    private Long id;

    @Column(name = "userid")
    private Long userId;

    @Size(max = 50)
    @Column(name = "username", length = 50)
    private String username;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "\"TotalOrders\"")
    private Long totalOrders;

    @Column(name = "\"TotalSpent\"")
    private BigDecimal totalSpent;

    @Column(name = "\"TotalReviews\"")
    private Long totalReviews;
}
