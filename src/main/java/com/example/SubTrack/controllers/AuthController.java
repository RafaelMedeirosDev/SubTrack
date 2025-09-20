package com.example.SubTrack.controllers;

import com.example.SubTrack.shared.dtos.TokenDataDTO;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SubTrack.entities.User;
import com.example.SubTrack.repositories.UserRepository;
import com.example.SubTrack.services.CreateUserService;
import com.example.SubTrack.services.auth.LoginService;
import com.example.SubTrack.shared.dtos.CreateUserDTO;
import com.example.SubTrack.shared.dtos.LoginResponseDto;
import com.example.SubTrack.shared.dtos.LoginUserDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private CreateUserService createUserService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Validated @RequestBody LoginUserDTO body) throws Exception {
        LoginResponseDto response = this.loginService.execute(body);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Validated @RequestBody CreateUserDTO body) throws Exception{
        User response = this.createUserService.execute(body);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<TokenDataDTO> me(@RequestAttribute("tokenData") TokenDataDTO tokenData) throws Exception{
        return ResponseEntity.ok(tokenData);
    }
}
