package com.example.demo.socket;

import com.corundumstudio.socketio.SocketIOClient;

import com.corundumstudio.socketio.SocketIOServer;
import com.example.demo.model.ChatMessageEntity;
import com.example.demo.model.Message;
import com.example.demo.model.MessageType;
import com.example.demo.model.UserSocketEntity;
import com.example.demo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class SocketService {

    AuthService authService;
    ChatRoomService chatRoomService;

    ChatMessageService chatMessageService;
    UserSocketService userSocketService;
    private final UserSocketRepo userSocketRepo;

    @Autowired
    public SocketService(AuthService authService, ChatRoomService chatRoomService, ChatMessageService chatMessageService, UserSocketService userSocketService, UserSocketRepo userSocketRepo) {
        this.authService = authService;
        this.chatRoomService = chatRoomService;
        this.chatMessageService = chatMessageService;
        this.userSocketService = userSocketService;
        this.userSocketRepo = userSocketRepo;
    }


    public void sendMessage(SocketIOClient senderClient, SocketIOServer server, Message message) {
        UserSocketEntity userSocketInfo = userSocketRepo.findByUserId(message.getSendTo()).orElseThrow();
        SocketIOClient client = server.getClient(UUID.fromString(userSocketInfo.getSocketId()));
        if (client != null) {
            ChatMessageEntity chatmessage = new ChatMessageEntity();
            chatmessage.setRecipientId(message.getSendTo());
            chatmessage.setSenderId(senderClient.getHandshakeData().getHttpHeaders().get("Authorization"));
            chatmessage.setContent(message.getMessage());
            chatmessage.setCreateAt(new Date());
            chatMessageService.saveChatMessage(chatmessage);
            client.sendEvent("message", message.getMessage());
        } else {
            log.info("No client found with session ID: " + message.getSendTo());
        }

       /* for (SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent(eventName,
                        new Message(MessageType.SERVER, message));
            }
        }*/
    }

    public void sendGroupMessage(String room, String eventName, SocketIOClient senderClient, String message) {
        for (SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent(eventName,
                        new Message(MessageType.SERVER, message));
            }
        }
    }

    public void onUserConnectedToSocket(SocketIOClient client) {
        String userId = client.getHandshakeData().getHttpHeaders().get("Authorization");
        userSocketService.saveSocketToken(userId, client.getSessionId().toString());

    }

    public void onUserDisConnectedToSocket(SocketIOClient client) {
        userSocketService.deleteSocketToken(client.getSessionId().toString());
    }

}