package com.example.demo.service;

import com.example.demo.model.ChatRoomEntity;
import com.example.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepo extends JpaRepository<ChatRoomEntity, Integer> {

    Optional<ChatRoomEntity> findAllBySenderIdAndRecipientId(String senderId,String recipientId);

}

