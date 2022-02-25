package com.example.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginFailDto {
    boolean success;
    String message;
}
