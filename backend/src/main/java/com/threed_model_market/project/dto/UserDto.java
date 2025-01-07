package com.threed_model_market.project.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private String username;
    private String password;

    @Email(message = "Email must be valid")
    private String email;

    private RoleDto role;

    public UserDto(String email, String password, RoleDto role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
