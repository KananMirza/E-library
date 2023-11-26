package com.orient.library.controller;

import com.orient.library.auth.TokenManager;
import com.orient.library.dto.request.LoginRequest;
import com.orient.library.dto.request.UserRequestDto;
import com.orient.library.enums.Message;
import com.orient.library.response.ResponseApi;
import com.orient.library.service.AuthService;
import com.orient.library.util.Utility;
import jakarta.validation.Valid;
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
    private final AuthService authService;
    private final Utility utility;

    @PostMapping("/login")
    public ResponseEntity<ResponseApi> login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), Message.SUCCESS.value(), tokenManager.generateToken(loginRequest.getEmail())));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseApi> register(@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(utility.response(HttpStatus.CREATED.value()
                , authService.register(userRequestDto), null));
    }
}
