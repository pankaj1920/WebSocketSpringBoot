package com.example.demo.service;


import com.example.demo.model.CreateAccountRequest;
import com.example.demo.utils.ApiResponse;

public interface AuthServiceListener {

    ApiResponse createAccount(CreateAccountRequest createAccountRequest);

}
