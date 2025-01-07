package com.threed_model_market.project.contoller;

import com.threed_model_market.project.dto.PermissionDto;
import com.threed_model_market.project.model.Permission;
import com.threed_model_market.project.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('VIEW_PERMISSIONS')")
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS') or hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody @Valid PermissionDto permissionDto) {
        Permission permission = permissionService.addPermission(permissionDto);
        return ResponseEntity.ok(permission);
    }

    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(permissions);
    }

    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{permissionId}")
    public ResponseEntity<?> removePermission(@PathVariable Long permissionId) {
        permissionService.removePermission(permissionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{permissionName}")
    public ResponseEntity<Permission> getPermissionByName(@PathVariable String permissionName) {
        Permission permission = permissionService.getPermissionByName(permissionName);
        return ResponseEntity.ok(permission);
    }

    @GetMapping("/{userId}/check/{permissionName}")
    public ResponseEntity<Boolean> checkPermission(@PathVariable Long userId, @PathVariable String permissionName) {
        boolean hasPermission = permissionService.checkPermission(userId, permissionName);
        return ResponseEntity.ok(hasPermission);
    }
}
