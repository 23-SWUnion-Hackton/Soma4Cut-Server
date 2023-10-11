package com.stacker4.whopper.thirdparty.aws.s3

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "cloud.aws.credentials")
class S3Properties(
    val accessKey: String,
    val secretKey: String
)