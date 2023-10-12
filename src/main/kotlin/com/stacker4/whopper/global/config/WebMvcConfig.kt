package com.stacker4.whopper.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "HEAD", "DELETE", "OPTIONS")
            .allowedOrigins(
                "http://localhost:3000",
                "https://soma-4cut.s3.ap-northeast-2.amazonaws.com",
                "https://e6ed-2001-2d8-6ad9-881-c03b-b67b-d840-2988.ngrok-free.app"
            )
            .allowCredentials(true)
    }
}