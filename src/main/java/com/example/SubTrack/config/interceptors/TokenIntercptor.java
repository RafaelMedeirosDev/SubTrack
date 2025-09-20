package com.example.SubTrack.config.interceptors;

import com.example.SubTrack.services.auth.JwtTokenService;
import com.example.SubTrack.shared.dtos.TokenDataDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenIntercptor implements HandlerInterceptor {

    private final JwtTokenService tokenService;

    public TokenIntercptor(JwtTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || token.isBlank()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        try {
            if(!this.tokenService.validateToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            TokenDataDTO tokenData = new TokenDataDTO(this.tokenService.getSubjectFromToken(token));
            request.setAttribute("tokenData", tokenData);
            return true;
        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
