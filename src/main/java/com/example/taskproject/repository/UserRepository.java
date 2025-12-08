package com.example.taskproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskproject.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


}
