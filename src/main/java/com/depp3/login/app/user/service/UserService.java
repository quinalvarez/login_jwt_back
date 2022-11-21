package com.depp3.login.app.user.service;


import com.depp3.login.app.role.data.enums.ERole;
import com.depp3.login.app.role.service.RoleService;
import com.depp3.login.app.user.data.dto.UserDTO;
import com.depp3.login.app.user.data.mapper.UserMapper;
import com.depp3.login.app.user.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final RoleService roleService;
    private final UserMapper mapper;

    public UserService(UserRepository repository, RoleService roleService) {
        this.repository = repository;
        this.roleService = roleService;
        this.mapper = new UserMapper();
    }

    public List<UserDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO save(UserDTO dto) {

        if(repository.existsByUsernameOrEmail(dto.getUsername(), dto.getEmail())) {
            throw new RuntimeException("El usuario ya existe");
        }

        dto.setRoles(Collections.singleton(roleService.getByName(ERole.ROLE_USER)));
        dto.setActive(true);

        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public UserDTO findByUsername(String username) {
        if (!repository.existsByUsernameOrEmail(username, username)) {
            throw new RuntimeException("El usuario no existe");
        }

        return mapper.toDTO(repository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new RuntimeException("El usuario no existe")));
    }
}
