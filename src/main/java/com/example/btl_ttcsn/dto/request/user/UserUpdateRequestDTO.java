package com.example.btl_ttcsn.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDTO {
    private String name;
    private int age;
    private String gender;
    private String address;
    private String email;
    private String phone;
}
