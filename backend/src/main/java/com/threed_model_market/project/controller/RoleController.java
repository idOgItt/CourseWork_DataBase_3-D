package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.RoleDto;
import com.threed_model_market.project.model.Role;
import com.threed_model_market.project.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@RestController
@PreAuthorize("hasAuthority('VIEW_ROLES')")
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasAuthority('MANAGE_ROLES') or hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDto roleDto) {
        Role role = roleService.addRole(roleDto);
        return ResponseEntity.ok(role);
    }

    @PreAuthorize("hasAuthority('MANAGE_ROLES') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{roleId}/permissions")
    public ResponseEntity<Role> addPermissionToRole(@PathVariable Long roleId, @RequestParam String permissionName) throws RoleNotFoundException {
        Role role = roleService.addPermissionToRole(roleId, permissionName);
        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PreAuthorize("hasAuthority('MANAGE_ROLES') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{roleId}")
    public ResponseEntity<?> removeRole(@PathVariable Long roleId) {
        roleService.removeRole(roleId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('MANAGE_ROLES') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<Void> removePermissionFromRole(
            @PathVariable Long roleId,
            @PathVariable Long permissionId) {

        roleService.removePermissionFromRole(roleId, permissionId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{roleName}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String roleName) throws RoleNotFoundException {
        Role role = roleService.getRoleByName(roleName);
        return ResponseEntity.ok(role);
    }
}
