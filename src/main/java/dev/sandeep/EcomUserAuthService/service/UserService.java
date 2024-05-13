package dev.sandeep.EcomUserAuthService.service;

import dev.sandeep.EcomUserAuthService.dto.LoginRequestDTO;
import dev.sandeep.EcomUserAuthService.dto.SignupRequestDTO;
import dev.sandeep.EcomUserAuthService.dto.UserResponseDTO;
import dev.sandeep.EcomUserAuthService.entity.User;

import javax.management.relation.RoleNotFoundException;

public interface UserService {
    UserResponseDTO signup(SignupRequestDTO signupRequestDTO) throws RoleNotFoundException;
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
    boolean validateToken(String token);
    boolean logout(String token);
}
