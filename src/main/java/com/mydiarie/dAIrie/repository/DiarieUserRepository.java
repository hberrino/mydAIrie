package com.mydiarie.dAIrie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mydiarie.dAIrie.models.DiarieUser;

public interface DiarieUserRepository extends JpaRepository<DiarieUser ,Long>{

    Optional<DiarieUser> findByEmail(String email);

    boolean existsByEmail(String email);

}
