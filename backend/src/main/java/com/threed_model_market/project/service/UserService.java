package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.RoleDto;
import com.threed_model_market.project.dto.UserDto;
import com.threed_model_market.project.exception_handler.exceptions.User.UserAlreadyExistsException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserInvalidMailFormatException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserInvalidPasswordException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundMailException;
import com.threed_model_market.project.model.Role;
import com.threed_model_market.project.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    ResponseEntity<String> registerUser(UserDto userDto) throws UserAlreadyExistsException, UserInvalidMailFormatException;

    String authenticateUser(UserDto userDto) throws UserInvalidPasswordException, UserNotFoundMailException, UserInvalidMailFormatException;

    User saveUser(User user);

    @SuppressWarnings("unused")
    User saveUserAndFlush(User user);

    User updateUser(Long id, UserDto dto) throws UserInvalidMailFormatException;

    void removeUserById(Long id);

    void changeUserPassword(Long id, String newPassword);

    void changeUserEmail(Long id, String newEmail) throws UserInvalidMailFormatException, UserAlreadyExistsException;

    void changeUserName(Long id, String newName);

    List<User> getAllUser();

    User getUserByEmail(String email) throws UserNotFoundMailException, UserInvalidMailFormatException;

    User getUserById(Long id);

    boolean isEmailAlreadyExist(String email) throws UserInvalidMailFormatException;

    User setUserRole(Long id, RoleDto dto);

    void removeRoleFromUser(Long userId);

    List<Role> getRolesByUserId(Long userId);
}
