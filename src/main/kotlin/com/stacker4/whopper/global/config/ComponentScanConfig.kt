package com.stacker4.whopper.global.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
    basePackages = ["com.stacker4.whopper"]
)
class ComponentScanConfig