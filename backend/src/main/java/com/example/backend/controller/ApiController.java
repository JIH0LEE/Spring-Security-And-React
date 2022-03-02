package com.example.backend.controller;


import com.example.backend.config.JWTUtil;
import com.example.backend.model.dto.LoginDto;
import com.example.backend.model.dto.LoginFailDto;
import com.example.backend.model.dto.RegisterDto;
import com.example.backend.model.entity.User;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;
    private final JWTUtil jwtUtil;
    @PostMapping("/sign-up")
    private Object signUp(@RequestBody RegisterDto registerDto){

        try{
            User user=userService.makeUser(registerDto);
            return new LoginFailDto(true,"로그인 성공",user);
        }catch(IllegalArgumentException e){
            return new LoginFailDto(false,e.getMessage(),null);
        }
        
    }
    @PostMapping("/sign-in")
    private Object signIn(@RequestBody LoginDto loginDto, HttpServletRequest request,
                        HttpServletResponse response){
        try {
            User user = userService.getValidUser(loginDto);
            response.setHeader("auth_token", JWTUtil.makeAuthToken(user));
            response.setHeader("refresh_token", JWTUtil.makeRefreshToken(user));
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            return new LoginFailDto(true,"로그인 성공",user);
        }catch(IllegalArgumentException e){
            return new LoginFailDto(false,e.getMessage(),null);
        }
    }

    @GetMapping("/test")
    private User test(Principal principal){
        return userService.getUser(principal.getName());
    }
}
