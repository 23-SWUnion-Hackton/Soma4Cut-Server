package com.stacker4.whopper.thirdparty.aws

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "cloud.aws.credentials")
class AwsProperties(
    val accessKey: String,
    val secretKey: String
)