package com.threed_model_market.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.threed_model_market.project.enums.RoleEnum;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private RoleEnum type;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties("roles")
    private List<User> users = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"
            )
    )
    @JsonIgnoreProperties({"roles", "id"}) // This ignore the fields in the Privilege entity.
    private List<Privilege> privileges = new ArrayList<>();

    public Role() {
    }

    public Role(RoleEnum type) {
        this.type = type;
    }

    public Role(RoleEnum type, List<User> users, List<Privilege> privileges) {
        this.type = type;
        this.users = users;
        this.privileges = privileges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getType() {
        return type;
    }

    public void setType(RoleEnum type) {
        this.type = type;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        for (Privilege privilege : privileges) {
            setPrivilege(privilege);
        }
    }

    public void removePrivileges(List<Privilege> privileges) {
        for (Privilege privilege : privileges) {
            removePrivilege(privilege);
        }
    }

    /**
     * Add role and also to child entity privilege.
     *
     * @param privilege
     */
    public void setPrivilege(Privilege privilege) {
        this.privileges.add(privilege);
        privilege.getRoles().add(this);
    }

    /**
     * Remove role and also to child entity privilege.
     *
     * @param privilege
     */
    public void removePrivilege(Privilege privilege) {
        this.privileges.remove(privilege);
        privilege.getRoles().remove(this);
    }
}