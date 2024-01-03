package com.example.btl_ttcsn.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateResponseDTO {
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String email;
    private String phone;
}
