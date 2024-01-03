package com.example.btl_ttcsn.service.serviceImpl;

import com.example.btl_ttcsn.dto.request.user.LoginRequestDTO;
import com.example.btl_ttcsn.dto.response.user.LoginResponseDTO;

import com.example.btl_ttcsn.exception.UnauthorizedException;
import com.example.btl_ttcsn.security.UserDetailImpl;
import com.example.btl_ttcsn.security.jwt.JwtTokenProvider;
import com.example.btl_ttcsn.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        LoginResponseDTO loginResponseDTO=new LoginResponseDTO();
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                    request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailImpl userDetailImp = (UserDetailImpl) authentication.getPrincipal();
            String accessToken = jwtTokenProvider.generateTokenByUsername(userDetailImp.getUsername());
            String refreshToken = jwtTokenProvider.generateRefreshTokenByUsername(userDetailImp.getUsername());

            loginResponseDTO.setAccessToken(accessToken);
            loginResponseDTO.setRefreshToken(refreshToken);
            loginResponseDTO.setAuthorities(userDetailImp.getAuthorities());
        }  catch (InternalAuthenticationServiceException e) {
            throw new UnauthorizedException("username or password is incorrect!");
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("username or password is incorrect!");
        }


        return loginResponseDTO;
    }
}
