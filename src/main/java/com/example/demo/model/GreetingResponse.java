package com.example.demo.model;

import lombok.Data;

import java.time.Instant;

@Data
public class GreetingResponse {
    String greeting;

    public GreetingResponse() {
    }

    public GreetingResponse(String name) {
        this.withGreeting("Hello "+name+" @ "+ Instant.now());
    }

    public GreetingResponse withGreeting(String msg) {
        this.greeting = msg;
        return this;
    }
}
