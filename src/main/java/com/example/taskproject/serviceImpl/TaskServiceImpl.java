package com.example.taskproject.serviceImpl;

import com.example.taskproject.entity.Task;
import com.example.taskproject.entity.User;
import com.example.taskproject.exception.TaskNotFound;
import com.example.taskproject.exception.TaskUserMismatch;
import com.example.taskproject.exception.UserNotFound;
import com.example.taskproject.payload.TaskDto;
import com.example.taskproject.repository.TaskRepository;
import com.example.taskproject.repository.UserRepository;
import com.example.taskproject.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        // if user found return User object else throw UserNotFound Exception
        User user  = userRepository.findById(userid).orElseThrow(() ->
                new UserNotFound(String.format("User with id %d not Found",userid)));
        // TaskDto and Task both are pojo classes having same properties, modelmapper converts one object to another
        Task task = modelMapper.map(taskDto, Task.class);
        task.setUser(user);
        // After Setting the user only we are storing task in db
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask,TaskDto.class);
    }

    @Override
    public List<TaskDto> getAllTasks(Long userid) {
        User user = userRepository.findById(userid).orElseThrow(() ->
            new UserNotFound(String.format("User with ID %d not found",userid)));
        List<Task> tasks = taskRepository.findAllByUserId(userid); // findAllBy<Entity><ColumnName>()
        return tasks.stream().map(
                task -> modelMapper.map(task, TaskDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public TaskDto getTask(Long userid,Long taskid){
        User user = userRepository.findById(userid).orElseThrow(
                () -> new UserNotFound(String.format("User with ID %d not found",userid)));
        Task task = taskRepository.findById(taskid).orElseThrow(
                () -> new TaskNotFound(String.format("Task with Id %d not found",taskid)));
        if(user.getId() != task.getUser().getId()){
         throw new TaskUserMismatch(String.format("Task Id %d doesn't belong to User Id %d",taskid,userid));
        }
        return modelMapper.map(task,TaskDto.class);
    }

    @Override
    public String deleteTask(Long userid,Long taskid){
        User user = userRepository.findById(userid).orElseThrow(
                () -> new UserNotFound(String.format("User with ID %d not found",userid)));
        Task task = taskRepository.findById(taskid).orElseThrow(
                () -> new TaskNotFound(String.format("Task with Id %d not found",taskid)));
        if(user.getId() != task.getUser().getId()){
            throw new TaskUserMismatch(String.format("Task Id %d doesn't belong to User Id %d",taskid,userid));
        }
        taskRepository.deleteById(taskid);
        return "Task Deleted successfully";
    }
}
