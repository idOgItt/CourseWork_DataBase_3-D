package com.threed_model_market.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString(exclude = "userImage")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
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
    private String passwordHash;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roleid", nullable = false)
    private Role role;

    @Column(name = "registrationdate", nullable = false, updatable = false)
    private Instant registrationDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private UserImage userImage;

    @Builder
    public User(String email, String username, String passwordHash, Role role) {
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.registrationDate = Instant.now();
    }
}
