//package com.unicauca.smart_consumption_offert_ms.infrastructure.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")  // Aplica a todas las rutas
//                        .allowedOrigins("http://localhost:4200")  // Especifica el origen permitido
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos HTTP permitidos
//                        .allowedHeaders("*")  // Permite todos los encabezados
//                        .allowCredentials(true);  // Permite el uso de cookies o credenciales
//            }
//        };
//    }
//}


