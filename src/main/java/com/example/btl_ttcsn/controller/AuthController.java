package com.example.btl_ttcsn.controller;

import com.example.btl_ttcsn.base.VsResponseUtil;
import com.example.btl_ttcsn.dto.request.user.LoginRequestDTO;
import com.example.btl_ttcsn.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        return VsResponseUtil.success(authService.login(request));
    }
}
