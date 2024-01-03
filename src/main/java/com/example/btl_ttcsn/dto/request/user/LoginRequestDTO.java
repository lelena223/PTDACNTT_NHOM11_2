package com.example.btl_ttcsn.dto.request.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequestDTO {

  private String username;
  private String password;

}
