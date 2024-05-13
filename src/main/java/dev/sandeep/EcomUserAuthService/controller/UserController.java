package dev.sandeep.EcomUserAuthService.controller;

import dev.sandeep.EcomUserAuthService.dto.LoginRequestDTO;
import dev.sandeep.EcomUserAuthService.dto.SignupRequestDTO;
import dev.sandeep.EcomUserAuthService.dto.UserResponseDTO;
import dev.sandeep.EcomUserAuthService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(userService.login(loginRequestDTO));
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorisation") String authToken){
        return ResponseEntity.ok(userService.logout(authToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO) throws RoleNotFoundException {
        return ResponseEntity.ok(userService.signup(signupRequestDTO));
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestHeader("Authorisation") String authToken){

        return ResponseEntity.ok(userService.validateToken(authToken));
    }
}
