package com.threed_model_market.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.threed_model_market.project.model.Role;
import com.threed_model_market.project.enums.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByType(RoleEnum type);
}