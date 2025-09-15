package com.example.SubTrack.shared.dtos;

public record CreateUserDTO(
        String name,
        String email,
        String password
) {
}
