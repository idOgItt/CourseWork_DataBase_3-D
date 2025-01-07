package com.threed_model_market.project.service.impl;

import com.threed_model_market.project.dto.PermissionDto;
import com.threed_model_market.project.exception_handler.exceptions.Permission.PermissionNotFoundException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundMailException;
import com.threed_model_market.project.model.Permission;
import com.threed_model_market.project.model.Role;
import com.threed_model_market.project.model.User;
import com.threed_model_market.project.repository.PermissionRepository;
import com.threed_model_market.project.repository.UserRepository;
import com.threed_model_market.project.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    private final UserRepository userRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository, UserRepository userRepository) {
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Permission addPermission(PermissionDto permissionDto) {
        Permission permission = new Permission(permissionDto.getPermissionName(), permissionDto.getDescription());
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public void removePermission(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException("Permission not found"));
        permissionRepository.delete(permission);
    }

    @Override
    public Permission getPermissionByName(String permissionName) {
        return permissionRepository.findByPermissionName(permissionName)
                .orElseThrow(() -> new PermissionNotFoundException("Permission not found"));
    }

    @Override
    public boolean checkPermission(Long userId, String permissionName) throws UserNotFoundMailException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundMailException("User not found"));

        Role userRole = user.getRole();

        return userRole.getPermissions().stream()
                .anyMatch(permission -> permission.getPermissionName().equals(permissionName));
    }

}
