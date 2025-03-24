package com.rserver.miniblog.auth.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${image.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String resolvedPath = System.getProperty("user.home") + "/Desktop/uploads/";

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///" + resolvedPath.replace("\\", "/"));
    }

}
