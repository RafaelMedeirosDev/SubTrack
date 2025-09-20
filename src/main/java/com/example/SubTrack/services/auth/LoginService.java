package com.example.SubTrack.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SubTrack.entities.User;
import com.example.SubTrack.repositories.UserRepository;
import com.example.SubTrack.shared.UserDetailsImpl;
import com.example.SubTrack.shared.dtos.LoginResponseDto;
import com.example.SubTrack.shared.dtos.LoginUserDTO;


@Service
public class LoginService {
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UserRepository userRepository;

    public LoginResponseDto execute(LoginUserDTO data) throws Exception {

        User userFormDb = this.userRepository.findByEmail(data.email())
                .orElseThrow(() -> new Exception("Usuário não encontrado com o email: " + data.email()));

        String token = this.jwtTokenService.generateToken(new UserDetailsImpl(userFormDb));
        return new LoginResponseDto(token);
    }

}   
