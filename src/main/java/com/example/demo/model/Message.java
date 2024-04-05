package com.example.demo.model;

import lombok.Data;

@Data
public class Message {
    private MessageType type;
    private String message;
    private String sendTo;

    public Message() {
    }

    public Message(MessageType type, String message) {
        this.type = type;
        this.message = message;
    }
}
