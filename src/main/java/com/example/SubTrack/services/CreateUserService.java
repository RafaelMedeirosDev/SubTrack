package com.example.SubTrack.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SubTrack.entities.User;
import com.example.SubTrack.repositories.UserRepository;
import com.example.SubTrack.shared.dtos.CreateUserDTO;

@Service
public class CreateUserService {
    @Autowired
    private UserRepository userRepository;

    public User execute(CreateUserDTO data){

        Optional<User> user = userRepository.findByEmail(data.email());
        if(user.isPresent()){
            throw new RuntimeException("Email já cadastrado");
        }

        String hashedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), hashedPassword);

        return this.userRepository.save(newUser);
    }
}
