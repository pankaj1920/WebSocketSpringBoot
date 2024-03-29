package com.example.demo.controller;

import com.example.demo.model.GreetingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class SampleController {

    @GetMapping("/hello")
    public ResponseEntity<String> forgotPassword(@RequestBody GreetingRequest request) {

        return new ResponseEntity<>("Hello how are you " + request.getName(), HttpStatus.OK);
    }

}
