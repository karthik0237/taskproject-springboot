package com.example.taskproject.controller;


import com.example.taskproject.payload.TaskDto;
import com.example.taskproject.service.TaskService;
import java.lang.Long;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/{userid}/tasks")
    public ResponseEntity<TaskDto> saveTask(@PathVariable(name = "userid") Long userid,
                                            @RequestBody TaskDto taskDto){
        return new ResponseEntity<>(taskService.saveTask(userid,taskDto), HttpStatus.CREATED);
    }

    @GetMapping("/{userid}/tasks")
    public List<TaskDto> getAllTasks(@PathVariable(name = "userid") Long userid){

        return null;
    }
}
