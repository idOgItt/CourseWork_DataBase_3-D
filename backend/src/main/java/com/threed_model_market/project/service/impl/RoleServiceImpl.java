package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.RoleDto;
import com.threed_model_market.project.dto.PermissionDto;
import com.threed_model_market.project.exception_handler.exceptions.Permission.PermissionNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.Role.RoleNotFoundException;
import com.threed_model_market.project.model.Permission;
import com.threed_model_market.project.model.Role;
import com.threed_model_market.project.repository.PermissionRepository;
import com.threed_model_market.project.repository.RoleRepository;
import com.threed_model_market.project.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Role addRole(RoleDto roleDto) {
        Role role = new Role(roleDto.getRoleName());

        if (roleDto.getPermissions() != null && !roleDto.getPermissions().isEmpty()) {
            for (PermissionDto permissionDto : roleDto.getPermissions()) {
                Optional<Permission> permission = permissionRepository.findByPermissionName(permissionDto.getPermissionName());
                permission.ifPresent(role::addPermission);
            }
        }

        return roleRepository.save(role);
    }

    @Override
    public Role addPermissionToRole(Long roleId, String permissionName) throws RoleNotFoundException {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        Permission permission = permissionRepository.findByPermissionName(permissionName)
                .orElseThrow(() -> new PermissionNotFoundException("Permission not found"));

        role.addPermission(permission);

        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void removeRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role getRoleByName(String roleName) throws RoleNotFoundException {
        return roleRepository.findByRolename(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
    }

    @Override
    public void removePermissionFromRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException("Permission not found"));

        role.removePermission(permission);

        roleRepository.save(role);
    }

}
