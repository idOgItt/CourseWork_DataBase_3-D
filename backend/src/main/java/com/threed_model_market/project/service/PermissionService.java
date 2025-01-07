package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.PermissionDto;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundMailException;
import com.threed_model_market.project.model.Permission;

import java.util.List;

public interface PermissionService {
    Permission addPermission(PermissionDto permissionDto);

    List<Permission> getAllPermissions();

    void removePermission(Long permissionId);

    Permission getPermissionByName(String permissionName);

    boolean checkPermission(Long userId, String permissionName) throws UserNotFoundMailException;
}
