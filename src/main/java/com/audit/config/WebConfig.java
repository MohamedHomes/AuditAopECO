package com.audit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAspectJAutoProxy
public class WebConfig implements WebMvcConfigurer {

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    // // registry.addMapping("/**") // Allow all endpoints
    // // .allowedOrigins("http://your-frontend-domain.com") // Specify allowed origins
    // // .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
    // // .allowedHeaders("*") // Allow all headers
    // // .allowCredentials(true); // Allow credentials
    //
    // registry.addMapping("/**")
    // .allowedOrigins("*") // Allow all origins for testing
    // .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
    // .allowedHeaders("*")
    // .allowCredentials(true);
    //
    // }
}