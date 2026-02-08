package com.example.as4.service.impl;


import com.example.as4.model.dto.UserDTO;
import com.example.as4.model.entity.User;
import com.example.as4.repository.UserRepository;
import com.example.as4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.as4.model.entity.User.builder;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDTO create(UserDTO dto) {
        User user = builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .email(dto.getEmail())
                .build();

        user = userRepository.save(user);
        return mapToDTO(user);
    }
    @Override
    public UserDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setFirstName(dto.getFirstName());
        existingUser.setLastName(dto.getLastName());
        existingUser.setAge(dto.getAge());
        existingUser.setEmail(dto.getEmail());

        User updatedUser = userRepository.save(existingUser);
        return mapToDTO(updatedUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .build();
    }
}