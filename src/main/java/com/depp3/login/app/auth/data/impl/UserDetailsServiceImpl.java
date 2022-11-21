package com.depp3.login.app.auth.data.impl;

import com.depp3.login.app.auth.data.dto.AuthRegister;
import com.depp3.login.app.role.data.enums.ERole;
import com.depp3.login.app.role.service.RoleService;
import com.depp3.login.app.user.data.dto.UserDTO;
import com.depp3.login.app.user.data.entity.User;
import com.depp3.login.app.user.data.mapper.UserMapper;
import com.depp3.login.app.user.data.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;
    private final RoleService service;
    private final UserMapper mapper;

    public UserDetailsServiceImpl(UserRepository repository, RoleService service) {
        this.repository = repository;
        this.service = service;
        this.mapper = new UserMapper();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));

        return UserDetailsImpl.build(user);
    }

    public UserDTO register(AuthRegister dto) {

        if(repository.existsByUsernameOrEmail(dto.getUsername(), dto.getEmail())) {
            throw new RuntimeException("El usuario ya existe");
        }

        dto.setRoles(Collections.singleton(service.getByName(ERole.ROLE_USER)));
        dto.setActive(true);

        return mapper.toDTO(repository.save(mapper.authToEntity(dto)));
    }
}
