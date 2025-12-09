package com.example.taskproject.serviceImpl;

import com.example.taskproject.entity.Task;
import com.example.taskproject.entity.User;
import com.example.taskproject.payload.TaskDto;
import com.example.taskproject.repository.TaskRepository;
import com.example.taskproject.repository.UserRepository;
import com.example.taskproject.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskDto saveTask(Long userid, TaskDto taskDto) {
        User user  = userRepository.findById(userid).get();
        // TaskDto and Task both are pojo classes having same properties, modelmapper converts one object to another
        Task task = modelMapper.map(taskDto, Task.class);
        task.setUser(user);
        // After Setting the user only we are storing task in db
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask,TaskDto.class);
    }

    @Override
    public List<TaskDto> getAllTasks(Long userid) {
        return List.of();
    }
}
