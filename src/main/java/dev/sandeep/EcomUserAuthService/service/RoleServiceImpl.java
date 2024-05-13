package dev.sandeep.EcomUserAuthService.service;

import dev.sandeep.EcomUserAuthService.dto.RoleRequestDTO;
import dev.sandeep.EcomUserAuthService.dto.RoleResponseDTO;
import dev.sandeep.EcomUserAuthService.entity.Role;
import dev.sandeep.EcomUserAuthService.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO) {
        Role role = new Role();
        role.setRoleName(roleRequestDTO.getRoleName());
        role.setDescription(roleRequestDTO.getDescription());
        return RoleResponseDTO.from(roleRepository.save(role));
    }
}
