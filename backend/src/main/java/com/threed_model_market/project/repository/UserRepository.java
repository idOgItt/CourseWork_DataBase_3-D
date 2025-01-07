package com.threed_model_market.project.repository;

import com.threed_model_market.project.model.Role;
import com.threed_model_market.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @SuppressWarnings("unused")
    List<User> findByRole(Role role);
}
