package com.threed_model_market.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoleDto {
    private String roleName;
    private List<PermissionDto> permissions;
}
