package com.threed_model_market.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permission {

    @SuppressWarnings("SpellCheckingInspection")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permissionid")
    private Integer id;

    @SuppressWarnings("SpellCheckingInspection")
    @Column(name = "permissionname")
    private String permissionName;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnoreProperties("permissions")
    private List<Role> roles = new ArrayList<>();

    protected Permission() {
    }

    public Permission(String permissionName, String description) {
        this.permissionName = permissionName;
        this.description = description;
    }

}
