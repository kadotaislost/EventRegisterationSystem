package com.example.event_registration_system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/badges/**")
                .addResourceLocations("file:./badges/");
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:./uploads/");
    }
}