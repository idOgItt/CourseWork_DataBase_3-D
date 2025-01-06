package com.threed_model_market.project.service;

import com.threed_model_market.project.dto.RoleDto;
import com.threed_model_market.project.dto.UserDto;
import com.threed_model_market.project.exception_handler.exceptions.User.UserAlreadyExists;
import com.threed_model_market.project.exception_handler.exceptions.User.UserInvalidPasswordException;
import com.threed_model_market.project.exception_handler.exceptions.User.UserNotFoundMailException;
import com.threed_model_market.project.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    ResponseEntity<String> registerUser(UserDto userDto) throws UserAlreadyExists;

    String authenticateUser(UserDto userDto) throws UserInvalidPasswordException, UserNotFoundMailException;

    User saveUser(User user);

    User saveUserAndFlush(User user);

    User updateUser(Long id, UserDto dto);

    void removeUserById(Long id);

    List<User> getAllUser();

    User getUserByEmail(String email) throws UserNotFoundMailException;

    User getUserById(Long id);

    boolean isEmailAlreadyExist(String email);

    User addUserRole(Long id, RoleDto dto);
}
