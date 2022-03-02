package com.example.backend.config;

import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JWTCheckFilter checkFilter;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        JWTLoginFilter loginFilter = new JWTLoginFilter(authenticationManager(), userService);
//        JWTCheckFilter checkFilter = new JWTCheckFilter(authenticationManager(), userService);
        http.
                csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                //토큰을 검증
                .addFilterAt(checkFilter, BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/test").hasRole("USER");



    }
}