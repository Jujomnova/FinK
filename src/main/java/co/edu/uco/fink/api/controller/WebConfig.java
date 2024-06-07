package co.edu.uco.fink.api.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("api/v1/empleado")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST")
                .allowedHeaders("*");

        registry.addMapping("/api/v1/estado")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .allowedHeaders("*");

        registry.addMapping("api/v1/registroEstado")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST")
                .allowedHeaders("*");
    }
}
