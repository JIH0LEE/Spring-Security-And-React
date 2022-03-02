package com.example.backend.service;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.backend.config.JWTUtil;
import com.example.backend.config.VerifyResult;
import com.example.backend.model.dto.LoginDto;
import com.example.backend.model.entity.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException(username));
    }



    public User getUser(LoginDto loginDto){
        return userRepository.getUserByUsername(loginDto.getUsername());
    }
    public User makeUser(LoginDto loginDto){
        User newUser=User.builder()
                .username(loginDto.getUsername())
                .password(loginDto.getPassword())
                .role("ROLE_USER")
                .enabled(true)
                .build();
        return userRepository.save(newUser);
    }
    public User getValidUser(LoginDto loginDto){
        String username= loginDto.getUsername();
        String password=loginDto.getPassword();
        User target;
        target=userRepository.findUserByUsername(username).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(password, target.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return target;

    }

    public User getUser(String username){
        return userRepository.getUserByUsername(username);
    }
}
