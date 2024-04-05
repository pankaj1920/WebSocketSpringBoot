package com.example.demo.service;

import com.example.demo.model.ChatRoomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomRepo chatRoomRepo;

    @Autowired
    public ChatRoomService(ChatRoomRepo chatRoomRepo) {
        this.chatRoomRepo = chatRoomRepo;
    }

    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExist) {

        return chatRoomRepo.findAllBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoomEntity::getChatId)
                .or(() -> {
                    if (createNewRoomIfNotExist) {
                        var chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    private String createChatId(String senderId, String recipientId) {
        var chatId = String.format("senderId_%s_to_recipientId_%s", senderId, recipientId);
        ChatRoomEntity senderRecipient = new ChatRoomEntity();
        senderRecipient.setChatId(chatId);
        senderRecipient.setRecipientId(recipientId);
        senderRecipient.setSenderId(senderId);

        ChatRoomEntity recipientSender = new ChatRoomEntity();
        recipientSender.setChatId(chatId);
        recipientSender.setRecipientId(senderId);
        recipientSender.setSenderId(recipientId);
        chatRoomRepo.save(senderRecipient);
        chatRoomRepo.save(recipientSender);

        return chatId;
    }
}
