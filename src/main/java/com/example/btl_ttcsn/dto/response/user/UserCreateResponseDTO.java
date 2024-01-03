package com.example.btl_ttcsn.dto.response.user;

import com.example.btl_ttcsn.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponseDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
}
