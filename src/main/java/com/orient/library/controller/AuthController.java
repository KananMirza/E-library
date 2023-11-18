package com.orient.library.controller;

import com.orient.library.auth.TokenManager;
import com.orient.library.dto.request.LoginRequest;
import com.orient.library.enums.Message;
import com.orient.library.response.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenManager tokenManager;
    private final AuthenticationManager authenticationManager;
    private final ResponseApi responseApi;

    @PostMapping("/login")
    public ResponseEntity<ResponseApi> login(@RequestBody LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(), Message.SUCCESS.value(), tokenManager.generateToken(loginRequest.getEmail())));
    }

    private ResponseApi responseApi(Integer status,String message,Object object){
        responseApi.setMessage(message);
        responseApi.setStatus(status);
        responseApi.setBody(object);
        return responseApi;
    }
}
