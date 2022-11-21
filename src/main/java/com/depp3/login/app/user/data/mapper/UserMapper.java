package com.depp3.login.app.user.data.mapper;

import com.depp3.login.app.auth.data.dto.AuthRegister;
import com.depp3.login.app.user.data.dto.UserDTO;
import com.depp3.login.app.user.data.entity.User;

public class UserMapper {

    public User toEntity(UserDTO dto) {
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setActive(dto.getActive());
        user.setRoles(dto.getRoles());

        return user;
    }

    public User authToEntity(AuthRegister dto) {
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setActive(dto.getActive());
        user.setRoles(dto.getRoles());

        return user;
    }

    public UserDTO toDTO(User entity) {
        UserDTO dto = new UserDTO();

        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setActive(entity.getActive());
        dto.setRoles(entity.getRoles());

        return dto;
    }
}
