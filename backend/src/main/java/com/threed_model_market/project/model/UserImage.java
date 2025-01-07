package com.threed_model_market.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(
        name = "userimages",
        uniqueConstraints = @UniqueConstraint(columnNames = {"userid", "filename"})
)
public class UserImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userimages_id_gen")
    @SequenceGenerator(name = "userimages_id_gen", sequenceName = "userimages_imageid_seq", allocationSize = 1)
    @Column(name = "imageid", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false, unique = true)
    private User user;

    @Size(max = 255)
    @NotNull
    @Column(name = "filename", nullable = false)
    private String filename;

    @NotNull
    @Column(name = "filedata", nullable = false)
    private byte[] filedata;

    @Column(name = "uploaddate", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant uploadDate;
}
