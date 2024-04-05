package com.example.demo.controller;


import com.example.demo.model.CreateAccountRequest;
import com.example.demo.service.AuthService;
import com.example.demo.utils.ApiResponse;
import com.example.demo.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth/")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("createUser")
    public ResponseEntity<ApiResponse> userRegister(@RequestBody CreateAccountRequest request) {
        ApiResponse baseApiResponse = this.authService.createAccount(request);
        return ResponseHandler.sendResponse(baseApiResponse);
    }

}
