package com.example.demo.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.demo.model.Message;
import com.example.demo.utils.Print;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SocketModule {


    private final SocketIOServer server;
    private final SocketService socketService;

    public SocketModule(SocketIOServer server, SocketService socketService) {
        this.server = server;
        this.socketService = socketService;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", Message.class, onChatReceived());
    }


    private DataListener<Message> onChatReceived() {
        return (senderClient, data, ackSender) -> {

            log.info("onChatReceived Cleint " + senderClient);
            log.info(" onChatReceived data.getMessage " + data.getMessage());
            log.info("onChatReceived data.getRoom " + data.getSendTo());

            log.info("onChatReceived ackSender " + ackSender);
            log.info("onChatReceived getHandshakeData = " + senderClient.getHandshakeData());
            log.info("onChatReceived getHandshakeData getAuthToken= " + senderClient.getHandshakeData().getAuthToken());
            log.info("onChatReceived getHandshakeData getUrl= " + senderClient.getHandshakeData().getUrl());
            log.info("onChatReceived getHandshakeData getAddress= " + senderClient.getHandshakeData().getAddress());
            log.info("onChatReceived getHandshakeData getLocal= " + senderClient.getHandshakeData().getLocal());
            log.info("onChatReceived getHandshakeData getUrlParams= " + senderClient.getHandshakeData().getUrlParams());
            log.info("onChatReceived getHandshakeData getTime= " + senderClient.getHandshakeData().getTime());
            log.info("onChatReceived getHandshakeData Authorization = " + senderClient.getHandshakeData().getHttpHeaders().get("Authorization"));
            log.info("onChatReceived getSessionId = " + senderClient.getSessionId());
            log.info("onChatReceived getNamespace = " + senderClient.getNamespace());
            log.info("onChatReceived getRemoteAddress = " + senderClient.getRemoteAddress());
            log.info("onChatReceived getEngineIOVersion = " + senderClient.getEngineIOVersion());
            log.info("onChatReceived getTransport = " + senderClient.getTransport());

            log.info("onChatReceived  Socket ID " + senderClient.getSessionId().toString() + "  Connected to socket ");


            log.info(data.toString());
            log.info(senderClient.getSessionId().toString());

            socketService.sendMessage(senderClient, server, data);
        };
    }


    private ConnectListener onConnected() {
        return (client) -> {

            log.info("Cleint " + client);
            log.info("onConnected getHandshakeData = " + client.getHandshakeData());
            log.info("onConnected getHandshakeData getAuthToken= " + client.getHandshakeData().getAuthToken());
            log.info("onConnected getHandshakeData getUrl= " + client.getHandshakeData().getUrl());
            log.info("onConnected getHandshakeData getAddress= " + client.getHandshakeData().getAddress());
            log.info("onConnected getHandshakeData getLocal= " + client.getHandshakeData().getLocal());
            log.info("onConnected getHandshakeData getUrlParams= " + client.getHandshakeData().getUrlParams());
            log.info("onConnected getHandshakeData getTime= " + client.getHandshakeData().getTime());
            log.info("onConnected getHandshakeData Authorization = " + client.getHandshakeData().getHttpHeaders().get("Authorization"));
            log.info("onConnected getSessionId = " + client.getSessionId());
            log.info("onConnected getNamespace = " + client.getNamespace());
            log.info("onConnected getRemoteAddress = " + client.getRemoteAddress());
            log.info("onConnected getEngineIOVersion = " + client.getEngineIOVersion());
            log.info("onConnected getTransport = " + client.getTransport());
            String room = client.getHandshakeData().getSingleUrlParam("room");
            client.joinRoom(room);
            log.info("onConnected  Socket ID " + client.getSessionId().toString() + "  Connected to socket ");

            socketService.onUserConnectedToSocket(client);
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.info("Cleint " + client);
            log.info("onDisconnected getHandshakeData = " + client.getHandshakeData());
            log.info("onDisconnected getHandshakeData getAuthToken= " + client.getHandshakeData().getAuthToken());
            log.info("onDisconnected getHandshakeData getUrl= " + client.getHandshakeData().getUrl());
            log.info("onDisconnected getHandshakeData getAddress= " + client.getHandshakeData().getAddress());
            log.info("onDisconnected getHandshakeData getLocal= " + client.getHandshakeData().getLocal());
            log.info("onDisconnected getHandshakeData getUrlParams= " + client.getHandshakeData().getUrlParams());
            log.info("onDisconnected getHandshakeData getTime= " + client.getHandshakeData().getTime());
            log.info("onDisconnected getHandshakeData getTime= " + client.getHandshakeData().getHttpHeaders().get("Authorization"));
            log.info("onDisconnected getSessionId = " + client.getSessionId());
            log.info("onDisconnected getNamespace = " + client.getNamespace());
            log.info("onDisconnected getRemoteAddress = " + client.getRemoteAddress());
            log.info("onDisconnected getEngineIOVersion = " + client.getEngineIOVersion());
            log.info("onDisconnected getTransport = " + client.getTransport());
            log.info("onDisconnected  Socket ID " + client.getSessionId().toString());

            socketService.onUserDisConnectedToSocket(client);
        };
    }

}