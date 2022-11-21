package com.depp3.login.app.auth.service;

import com.depp3.login.app.auth.data.dto.AuthLogin;
import com.depp3.login.app.auth.data.dto.AuthRegister;
import com.depp3.login.app.auth.data.dto.ResponseAuth;
import com.depp3.login.app.auth.data.impl.UserDetailsServiceImpl;
import com.depp3.login.app.user.data.dto.UserDTO;
import com.depp3.login.app.auth.data.impl.UserDetailsImpl;
import com.depp3.login.app.auth.data.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserDetailsServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;
    private final JwtUtils jwtUtils;

    public AuthService(UserDetailsServiceImpl userService, PasswordEncoder passwordEncoder,
                       AuthenticationManager manager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.manager = manager;
        this.jwtUtils = jwtUtils;
    }

    public UserDTO register(AuthRegister dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userService.register(dto);
    }

    public ResponseAuth login(AuthLogin dto) {
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtils.generateToken(auth);

        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new ResponseAuth(
                jwt,
                user.getUsername(),
                user.getEmail(),
                roles
        );
    }
}
