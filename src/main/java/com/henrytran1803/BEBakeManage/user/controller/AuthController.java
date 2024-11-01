package com.henrytran1803.BEBakeManage.user.controller;
import com.henrytran1803.BEBakeManage.common.error.ErrorCode;
import com.henrytran1803.BEBakeManage.common.response.ApiResponse;
import com.henrytran1803.BEBakeManage.common.response.LoginResponse;
import com.henrytran1803.BEBakeManage.user.dto.LoginRequest;
import com.henrytran1803.BEBakeManage.user.dto.RegisterRequest;
import com.henrytran1803.BEBakeManage.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
            LoginResponse data = new LoginResponse(token);
            return ApiResponse.success(data);
        } catch (Exception e) {
            return new ApiResponse<>(false, ErrorCode.USER_NOT_FOUND.getMessage(),ErrorCode.USER_NOT_FOUND.getCode(),null);
        }
    }
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        authService.register(
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getEmail(),
                registerRequest.getDateOfBirth(),
                registerRequest.getPassword()
        );
    }
}
