package dev.sandeep.EcomUserAuthService.service;

import dev.sandeep.EcomUserAuthService.dto.LoginRequestDTO;
import dev.sandeep.EcomUserAuthService.dto.SignupRequestDTO;
import dev.sandeep.EcomUserAuthService.dto.UserResponseDTO;
import dev.sandeep.EcomUserAuthService.entity.Role;
import dev.sandeep.EcomUserAuthService.entity.User;
import dev.sandeep.EcomUserAuthService.exception.InvalidCredentialException;
import dev.sandeep.EcomUserAuthService.exception.RoleNotFoundException;
import dev.sandeep.EcomUserAuthService.exception.UserNotFoundException;
import dev.sandeep.EcomUserAuthService.repository.RoleRepository;
import dev.sandeep.EcomUserAuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserResponseDTO signup(SignupRequestDTO signupRequestDTO) throws RoleNotFoundException {
        Role role = roleRepository.findById(signupRequestDTO.getRoleId()).orElseThrow(
                () -> new RoleNotFoundException("Role not found")
        );

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setName(signupRequestDTO.getName());
        user.setEmailId(signupRequestDTO.getEmail());
        user.setPassword(encoder.encode(signupRequestDTO.getPassword()));
        user.setRoles(List.of(role));

        return UserResponseDTO.from(userRepository.save(user));
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User savedUser = userRepository.findByEmailId(loginRequestDTO.getEmail()).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        if(bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(), savedUser.getPassword())){
            String userData = savedUser.getEmailId() + savedUser.getPassword() + LocalDateTime.now();
            String token = bCryptPasswordEncoder.encode(userData);
            savedUser.setToken(token);
        } else {
            throw new InvalidCredentialException();
        }
        savedUser = userRepository.save(savedUser);
        return UserResponseDTO.from(savedUser);
    }

    @Override
    public boolean validateToken(String token) {
        User savedUser = userRepository.findByToken(token).orElseThrow(
                () -> new InvalidCredentialException("Token is not valid")
        );
        return true;
    }

    @Override
    public boolean logout(String token) {
        User savedUser = userRepository.findByToken(token).orElseThrow(
                () -> new InvalidCredentialException("Token is not valid")
        );
        savedUser.setToken(null);
        userRepository.save(savedUser);
        return true;
    }


}
