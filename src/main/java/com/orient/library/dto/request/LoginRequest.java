package com.orient.library.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
}
