package dev.sandeep.EcomUserAuthService.service;

import dev.sandeep.EcomUserAuthService.dto.RoleRequestDTO;
import dev.sandeep.EcomUserAuthService.dto.RoleResponseDTO;

public interface RoleService {
    RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO);
}
