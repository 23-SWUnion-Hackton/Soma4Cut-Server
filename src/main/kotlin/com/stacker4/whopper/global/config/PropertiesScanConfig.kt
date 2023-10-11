package com.stacker4.whopper.global.config

import com.stacker4.whopper.global.security.token.jwt.property.JwtProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackages = ["com.stacker4.whopper"],
    basePackageClasses = [JwtProperties::class]
)
class PropertiesScanConfig