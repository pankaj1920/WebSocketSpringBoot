package com.example.demo.model;

import lombok.Data;

@Data
public class CreateAccountRequest {

    private String name;
    private String email;
    private String password;
}

