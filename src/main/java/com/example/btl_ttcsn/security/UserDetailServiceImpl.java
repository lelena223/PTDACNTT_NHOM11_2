package com.example.btl_ttcsn.security;

import com.example.btl_ttcsn.entity.User;
import com.example.btl_ttcsn.exception.UnauthorizedException;
import com.example.btl_ttcsn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user==null){
            throw new UnauthorizedException("username or password is incorrect!");
        }
        return new UserDetailImpl(user);
    }
}
