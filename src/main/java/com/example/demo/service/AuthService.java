package com.example.demo.service;


import com.example.demo.model.CreateAccountRequest;
import com.example.demo.model.UserEntity;
import com.example.demo.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService implements AuthServiceListener {

    private final AuthRepo authRepo;
    private final ApiResponse apiResponse;

    @Autowired
    public AuthService(AuthRepo authRepo, ApiResponse apiResponse) {
        this.authRepo = authRepo;
        this.apiResponse = apiResponse;
    }


    @Override
    public ApiResponse createAccount(CreateAccountRequest request) {
        Optional<UserEntity> userInfo = this.authRepo.findByEmail(request.getEmail());
        if (userInfo.isPresent()) {

            apiResponse.setErrorResponse("User Already Exist", HttpStatus.IM_USED.value());
            return apiResponse;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(request.getPassword());
        userEntity.setName(request.getName());


        this.authRepo.save(userEntity);
        apiResponse.setMessageResponse("Register Success", HttpStatus.OK.value());
        return apiResponse;
    }
}
