package com.threed_model_market.project.controller;

import com.threed_model_market.project.dto.RoleDto;
import com.threed_model_market.project.dto.UserDto;
import com.threed_model_market.project.exception_handler.exceptions.User.UserAlreadyExistsException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserInvalidMailFormatException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserInvalidPasswordException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundMailException;
import com.threed_model_market.project.model.Role;
import com.threed_model_market.project.model.User;
import com.threed_model_market.project.service.UserService;
import com.threed_model_market.project.util.AccessValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AccessValidator accessValidator;

    public UserController(UserService userService, AccessValidator accessValidator) {
        this.userService = userService;
        this.accessValidator = accessValidator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto UserDto) throws UserAlreadyExistsException, UserInvalidMailFormatException {
        return ResponseEntity.ok(userService.registerUser(UserDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid UserDto UserDto) throws UserNotFoundMailException, UserInvalidPasswordException, UserInvalidMailFormatException {
        return ResponseEntity.ok(userService.authenticateUser(UserDto));
    }

    @PreAuthorize("hasAuthority('VIEW_USERS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token) throws UserNotFoundMailException, UserInvalidMailFormatException {
        User user = userService.getUserByEmail(email);
        accessValidator.validateUserAccess(token, user.getId());
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAuthority('VIEW_USERS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        accessValidator.validateUserAccess(token, id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PreAuthorize("hasAuthority('MANAGE_USERS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PreAuthorize("hasAuthority('MANAGE_USERS') or hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) throws UserInvalidMailFormatException {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @PreAuthorize("hasAuthority('MANAGE_USERS') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUser(@PathVariable Long id) {
        userService.removeUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('MANAGE_USERS') or hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{id}/role")
    public ResponseEntity<?> addUserRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(userService.setUserRole(id, roleDto));
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<String> isEmailAlreadyExist(@PathVariable String email) throws UserInvalidMailFormatException {
        boolean exists = userService.isEmailAlreadyExist(email);
        if (exists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }
        return ResponseEntity.ok("Email is available");
    }

    @PreAuthorize("hasAuthority('VIEW_USERS') or hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody String newPassword, @RequestHeader("Authorization") String token) {
        accessValidator.validateUserAccess(token, id);
        userService.changeUserPassword(id, newPassword);
        return ResponseEntity.ok("Password changed successfully");
    }

    @PreAuthorize("hasAuthority('MANAGE_USERS') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}/roles")
    public ResponseEntity<List<Role>> getRolesByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getRolesByUserId(id));
    }

    @PreAuthorize("hasAuthority('MANAGE_USERS') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{userId}/role")
    public ResponseEntity<?> removeRoleFromUser(@PathVariable Long userId) {
        userService.removeRoleFromUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('VIEW_USERS') or hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}/email")
    public ResponseEntity<?> changeUserEmail(@PathVariable Long id, @RequestBody String newEmail, @RequestHeader("Authorization") String token) throws UserNotFoundMailException, UserInvalidMailFormatException, UserAlreadyExistsException {
        accessValidator.validateUserAccess(token, id);
        userService.changeUserEmail(id, newEmail);
        return ResponseEntity.ok("Email changed successfully");
    }

    @PutMapping("/{id}/username")
    public ResponseEntity<?> changeUserName(@PathVariable Long id, @RequestBody String newName) throws UserNotFoundMailException {
        userService.changeUserName(id, newName);
        return ResponseEntity.ok("User name changed successfully");
    }
}
