package com.threed_model_market.project.dto;

import com.threed_model_market.project.enums.PrivilegeEnum;
import com.threed_model_market.project.enums.RoleEnum;

import java.util.Collections;
import java.util.List;

public class RoleDto {
    private RoleEnum roleEnum = RoleEnum.GUEST;
    private List<PrivilegeEnum> privilegeEnums = Collections.singletonList(PrivilegeEnum.READ);

    public RoleDto(RoleEnum roleEnum, List<PrivilegeEnum> privilegeEnums) {
        this.roleEnum = roleEnum;
        this.privilegeEnums = privilegeEnums;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public List<PrivilegeEnum> getPrivilegeEnums() {
        return privilegeEnums;
    }

    public void setPrivilegeEnums(List<PrivilegeEnum> privilegeEnums) {
        this.privilegeEnums = privilegeEnums;
    }
}
