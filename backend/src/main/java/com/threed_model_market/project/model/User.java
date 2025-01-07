package com.threed_model_market.project.model;

import com.threed_model_market.project.UserImage;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "passwordhash", nullable = false)
    private String passwordhash;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roleid", nullable = false)
    private Role role;

    @Column(name = "registrationdate", nullable = false)
    private Instant registrationdate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserImage userImage;

    protected User() {
    }

    public User(String email, String username, String passwordhash, Role role) {
        this.email = email;
        this.username = username;
        this.passwordhash = passwordhash;
        this.role = role;
        this.registrationdate = Instant.now();
    }
}
