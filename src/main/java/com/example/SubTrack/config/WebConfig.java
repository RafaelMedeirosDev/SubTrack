package com.example.SubTrack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.SubTrack.config.interceptors.TokenIntercptor;
import com.example.SubTrack.shared.constants.Constants;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenIntercptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns(Constants.ENDPOINTS_WITH_AUTHENTICATION);
    }
}
