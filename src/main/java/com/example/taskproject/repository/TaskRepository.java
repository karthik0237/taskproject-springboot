package com.example.taskproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskproject.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findAllByUserId(Long userid);
}
