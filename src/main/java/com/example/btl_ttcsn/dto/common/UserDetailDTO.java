package com.example.btl_ttcsn.dto.common;

import com.example.btl_ttcsn.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String name;
    private String gender;
    private int age;
    private String address;
    private String role;
    private Project project;
}
