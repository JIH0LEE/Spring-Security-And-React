package com.example.backend.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.backend.model.entity.User;
import com.example.backend.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTCheckFilter extends OncePerRequestFilter {

    private final UserService userService;

//    public JWTCheckFilter(AuthenticationManager authenticationManager, UserService userService) {
//        super(authenticationManager);
//        this.userService = userService;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(bearer == null || !bearer.startsWith("Bearer ")){
            chain.doFilter(request, response);
            return;
        }
        String token = bearer.substring("Bearer ".length());
        VerifyResult result = JWTUtil.verify(token);
        if(result.isSuccess()){
            User user = (User) userService.loadUserByUsername(result.getUsername());
            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), null, user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(userToken);
            chain.doFilter(request, response);
        }else{
            throw new TokenExpiredException("Token is not valid");
        }
    }

}
