package com.threed_model_market.project;

import com.threed_model_market.project.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "userimages", schema = "public")
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userimages_id_gen")
    @SequenceGenerator(name = "userimages_id_gen", sequenceName = "userimages_imageid_seq", allocationSize = 1)
    @Column(name = "imageid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userid")
    private User userid;

    @Size(max = 255)
    @NotNull
    @Column(name = "filename", nullable = false)
    private String filename;

    @NotNull
    @Column(name = "filedata", nullable = false)
    private byte[] filedata;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "uploaddate")
    private Instant uploaddate;

}