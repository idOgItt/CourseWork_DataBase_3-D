package com.threed_model_market.project.repository;

import com.threed_model_market.project.enums.PrivilegeEnum;
import com.threed_model_market.project.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByType(PrivilegeEnum type);
}