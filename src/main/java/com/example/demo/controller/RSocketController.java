package com.example.demo.controller;

import com.example.demo.model.GreetingRequest;
import com.example.demo.model.GreetingResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class RSocketController {

    @MessageMapping("greet")
    GreetingResponse greet(GreetingRequest greetingRequest) {
        return new GreetingResponse(greetingRequest.getName());
    }
}
