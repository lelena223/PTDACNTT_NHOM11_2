package com.example.btl_ttcsn.service;

import com.example.btl_ttcsn.dto.common.UserDetailDTO;
import com.example.btl_ttcsn.dto.request.user.UserCreateRequestDTO;
import com.example.btl_ttcsn.dto.request.user.UserUpdateRequestDTO;
import com.example.btl_ttcsn.dto.response.user.UserCreateResponseDTO;
import com.example.btl_ttcsn.dto.response.user.UserUpdateResponseDTO;

import java.util.List;

public interface UserService {
    UserCreateResponseDTO create(UserCreateRequestDTO userCreateRequestDTO);
    UserUpdateResponseDTO update(UserUpdateRequestDTO userUpdateRequestDTO);
    void remove(Long id);
    String forgotPassword(String email);
    UserCreateResponseDTO getCurruntUser();
    UserDetailDTO getUserById(Long id);
    String updatePassword(String password);
    void Confirm(String email,String password);
    List<UserDetailDTO> getAll();
}
