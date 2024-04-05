package com.example.demo.service;

import com.example.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepo extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

}

