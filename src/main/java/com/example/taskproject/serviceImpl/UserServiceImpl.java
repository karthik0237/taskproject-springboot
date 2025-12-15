package com.example.taskproject.serviceImpl;

import com.example.taskproject.entity.User;
import com.example.taskproject.payload.UserDto;
import com.example.taskproject.repository.UserRepository;
import com.example.taskproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto){
        // userDto is not an entity of User
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword())); // encoding password before saving the user
        User user = userDtoToEntity(userDto);
        User savedUser = userRepository.save(user);
        return entityToUserDto(savedUser);
    }

    private User userDtoToEntity(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    private UserDto entityToUserDto(User savedUser){
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setPassword(savedUser.getPassword());
        userDto.setEmail(savedUser.getEmail());
        userDto.setName(savedUser.getName());
        return userDto;
    }
}
