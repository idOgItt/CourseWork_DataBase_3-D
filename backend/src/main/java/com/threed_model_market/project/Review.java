package com.threed_model_market.project;

import com.threed_model_market.project.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "reviews", schema = "public")
public class Review {
    @Id
    @ColumnDefault("nextval('reviews_reviewid_seq'::regclass)")
    @Column(name = "reviewid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User userid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelid")
    private Model modelid;

    @Size(max = 300)
    @Column(name = "text", length = 300)
    private String text;

    @Column(name = "rating")
    private Integer rating;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "datecreated")
    private Instant datecreated;

}