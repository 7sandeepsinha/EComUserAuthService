package dev.sandeep.EcomUserAuthService.dto;

import dev.sandeep.EcomUserAuthService.entity.Role;
import dev.sandeep.EcomUserAuthService.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class UserResponseDTO {
    private String name;
    private String email;
    private List<RoleResponseDTO> roles;

    public static UserResponseDTO from(User user){
        if(user == null)
            return null;
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.name = user.getName();
        userResponseDTO.email = user.getEmailId();
        userResponseDTO.roles = new ArrayList<>();
        //convert it to lambda stream
        for(Role role : user.getRoles()){
            RoleResponseDTO responseDTO = new RoleResponseDTO();
            responseDTO.setDesc(role.getDescription());
            responseDTO.setRole(role.getRoleName());
            userResponseDTO.roles.add(responseDTO);
        }
        return userResponseDTO;
    }

    //demo example
    public static User from(UserResponseDTO userResponseDTO){
        return null;
    }
}