package com.example.backend.controller;


import com.example.backend.model.dto.LoginDto;
import com.example.backend.model.entity.User;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/sign-up")
    private User signUp(@RequestBody LoginDto loginDto){
        System.out.println(loginDto);
        return userService.makeUser(loginDto);
    }
    @PostMapping("/sign-in")
    private User signIn(@RequestBody LoginDto loginDto, HttpServletRequest request,
                        HttpServletResponse response){
        System.out.println(response.getHeader("auth_token").getBytes(StandardCharsets.UTF_8).toString());
        return userService.getUser(loginDto);
    }

    @GetMapping("/test")
    private User test(Principal principal){
        return userService.getUser(principal.getName());
    }
}
