package com.example.backend.service;

import com.example.backend.model.dto.LoginDto;
import com.example.backend.model.dto.RegisterDto;
import com.example.backend.model.entity.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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

    public User makeUser(RegisterDto registerDto){

        String username= registerDto.getUsername();
        String password1= registerDto.getPassword1();
        String password2=registerDto.getPassword2();

        if(userRepository.existsUserByUsername(username)){
            throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
        }
        if(password1!=password2){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        User newUser=User.builder()
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword1()))
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
