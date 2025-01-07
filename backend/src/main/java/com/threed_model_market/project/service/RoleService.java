package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.RoleDto;
import com.threed_model_market.project.model.Role;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Service
public interface RoleService {

    Role addRole(RoleDto roleDto);

    Role addPermissionToRole(Long roleId, String permissionName) throws RoleNotFoundException;

    List<Role> getAllRoles();

    void removeRole(Long roleId);

    Role getRoleByName(String roleName) throws RoleNotFoundException;

    void removePermissionFromRole(Long roleId, Long permissionId);
}
