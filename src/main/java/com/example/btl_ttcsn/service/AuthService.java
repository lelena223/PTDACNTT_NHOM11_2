package com.example.btl_ttcsn.service;

import com.example.btl_ttcsn.dto.request.user.LoginRequestDTO;
import com.example.btl_ttcsn.dto.response.user.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO request);
}
