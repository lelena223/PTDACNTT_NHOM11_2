package com.example.btl_ttcsn.dto.response.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@NoArgsConstructor
@Setter
@Getter
public class LoginResponseDTO {

  private String tokenType = "Bearer";

  private String accessToken;

  private String refreshToken;



  private Collection<? extends GrantedAuthority> authorities;

  public LoginResponseDTO(String accessToken, String refreshToken, Collection<? extends GrantedAuthority> authorities) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.authorities = authorities;
  }

}
