package com.example.taskproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskproject.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByEmail(String email);
}
