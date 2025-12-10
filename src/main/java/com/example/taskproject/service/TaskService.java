package com.example.taskproject.service;

import com.example.taskproject.payload.TaskDto;
import java.lang.Long;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TaskService {

    public TaskDto saveTask(Long userid, TaskDto taskDto);

    public List<TaskDto> getAllTasks(Long userid);

    public TaskDto getTask(Long userid,Long taskid);

    public String deleteTask(Long userid,Long taskid);
}
