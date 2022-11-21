package com.depp3.login.app.role.service;

import com.depp3.login.app.role.data.entity.Role;
import com.depp3.login.app.role.data.enums.ERole;
import com.depp3.login.app.role.data.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getByName(ERole name) {
        return repository.findByName(name).orElseThrow(() -> new RuntimeException("El rol no existe"));
    }
}

