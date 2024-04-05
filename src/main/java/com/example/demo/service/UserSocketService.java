package com.example.demo.service;

import com.example.demo.model.ChatRoomEntity;
import com.example.demo.model.UserSocketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSocketService {

    private final UserSocketRepo userSocketRepo;


    @Autowired
    public UserSocketService(UserSocketRepo userSocketRepo) {
        this.userSocketRepo = userSocketRepo;
    }

    public void saveSocketToken(String userId, String socketId) {
        boolean userExist = userSocketRepo.findBySocketId(socketId).isPresent();
        if (!userExist){
            UserSocketEntity userInfo = new UserSocketEntity();
            userInfo.setSocketId(socketId);
            userInfo.setUserId(userId);
            userSocketRepo.save(userInfo);
        }

    }


    public void deleteSocketToken(String socketId) {
        UserSocketEntity userInfo = userSocketRepo.findBySocketId(socketId).orElseThrow();
        userSocketRepo.delete(userInfo);
    }
}
