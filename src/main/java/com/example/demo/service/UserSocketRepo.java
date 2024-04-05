package com.example.demo.service;

import com.example.demo.model.ChatRoomEntity;
import com.example.demo.model.UserSocketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSocketRepo extends JpaRepository<UserSocketEntity, Integer> {

    Optional<UserSocketEntity> findBySocketId(String socketId);
    Optional<UserSocketEntity> findByUserId(String userId);

}

