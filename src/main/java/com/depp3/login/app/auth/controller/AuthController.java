package com.depp3.login.app.auth.controller;

import com.depp3.login.app.auth.data.dto.AuthLogin;
import com.depp3.login.app.auth.data.dto.AuthRegister;
import com.depp3.login.app.auth.data.dto.ResponseAuth;
import com.depp3.login.app.auth.data.dto.ResponseMessage;
import com.depp3.login.app.auth.service.AuthService;
import com.depp3.login.app.user.data.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody AuthRegister dto) {
        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseAuth> login(@RequestBody AuthLogin dto) {
        return ResponseEntity.ok(service.login(dto));
    }
}
