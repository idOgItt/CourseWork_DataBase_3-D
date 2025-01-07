package com.threed_model_market.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private Long id;

    @Column(name = "rolename")
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties("roles")
    private List<User> users = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "rolepermissions",
            joinColumns = @JoinColumn(name = "roleid", referencedColumnName = "roleid"),
            inverseJoinColumns = @JoinColumn(name = "permissionid", referencedColumnName = "permissionid")
    )
    @JsonIgnoreProperties({"roles", "id"})
    private List<Permission> permissions = new ArrayList<>();

    protected Role() {
    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
        permission.getRoles().add(this);
    }

    public void removePermission(Permission permission) {
        this.permissions.remove(permission);
        permission.getRoles().remove(this);
    }
}
