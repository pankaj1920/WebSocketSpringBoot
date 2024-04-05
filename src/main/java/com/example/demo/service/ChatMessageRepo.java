package com.example.demo.service;

import com.example.demo.model.ChatMessageEntity;
import com.example.demo.model.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepo extends JpaRepository<ChatMessageEntity, Integer> {

    List<ChatMessageEntity> findAllByChatId(String chatId);

}

