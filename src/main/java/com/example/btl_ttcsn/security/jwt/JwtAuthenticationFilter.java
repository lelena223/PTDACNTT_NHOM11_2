package com.example.btl_ttcsn.security.jwt;



import com.example.btl_ttcsn.security.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final static Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);
  @Autowired
  UserDetailServiceImpl userDetailServiceImp;
  @Autowired
  private JwtTokenProvider jwtUtils;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      String token = extractToken(request);
      if (StringUtils.hasText(token) && jwtUtils.validation(token)) {
        String username = jwtUtils.getUserByToken(token);
        UserDetails userDetails = userDetailServiceImp.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                        userDetails.getPassword() , userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().
                buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      } else {
        SecurityContextHolder.clearContext();
      }
    } catch (Exception e) {
      log.error("Error : " + e.getMessage());
    }
    filterChain.doFilter(request , response);
  }

  //Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
  public String extractToken(HttpServletRequest request) {
    String headerToken = request.getHeader("Authorization");
    if (headerToken != null && headerToken.startsWith("Bearer ")) {
      return headerToken.substring(7);
    }
    return null;
  }

}
