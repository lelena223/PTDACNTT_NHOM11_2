package com.example.btl_ttcsn.config;

import com.example.btl_ttcsn.security.jwt.JwtAuthenticationEntryPoint;
import com.example.btl_ttcsn.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetail;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetail).passwordEncoder(passwordEncoder());
    }
    @Bean
    public JwtAuthenticationFilter authTokenFilter() {
        return new JwtAuthenticationFilter();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .authorizeRequests()
//                .antMatchers("/project/*").authenticated()
                .anyRequest().permitAll()
                .and()
                //UsernamePasswordAuthenticationFilter : bộ lọc mặc định của Spring Boot
                .addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class)//Giai thich
                .csrf().disable()//1 loại tấn công bảo mật sử dụng yêu cầu từ trang web khác
                .cors().disable();//Chính sách bảo mật trong tình duyệt web kiểm soat chia sẻ tài nguyên giữua các nguôn
    }
}
