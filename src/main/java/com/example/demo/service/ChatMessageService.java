package com.example.demo.service;

import com.example.demo.model.ChatMessageEntity;
import com.example.demo.model.ChatRoomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageRepo chatMessageRepo;
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatMessageService(ChatMessageRepo chatMessageRepo, ChatRoomService chatRoomService) {
        this.chatMessageRepo = chatMessageRepo;
        this.chatRoomService = chatRoomService;
    }

    public ChatMessageEntity saveChatMessage(ChatMessageEntity chatMessage) {
        var chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow(); // You can create your custom exception
        chatMessage.setChatId(chatId);
        chatMessageRepo.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessageEntity> findChatMessage(String senderId,String recipientId){
        // here false because we are fetching the chat not creating the chat rooms
        var chatId = chatRoomService.getChatRoomId(senderId,recipientId,false).orElseThrow();
        return chatMessageRepo.findAllByChatId(chatId);
    }
}
