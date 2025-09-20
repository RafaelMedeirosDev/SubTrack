package com.example.SubTrack.config;

import com.example.SubTrack.entities.User;
import com.example.SubTrack.repositories.UserRepository;
import com.example.SubTrack.services.auth.JwtTokenService;
import com.example.SubTrack.shared.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.startsWith("/auth/login") || path.startsWith("/auth/register")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = this.recoveryToken(request);
        if(token != null){
            try{
                String subject = this.jwtTokenService.getSubjectFromToken(token);
            
                User user = userRepository.findByEmail(subject)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + subject));
            
                UserDetails credential = new UserDetailsImpl(user);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(credential, null, credential.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);    
        
            } catch (Exception e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        

        filterChain.doFilter(request, response);
    }
    

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}