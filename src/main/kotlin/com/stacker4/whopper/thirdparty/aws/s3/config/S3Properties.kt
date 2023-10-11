package com.stacker4.whopper.thirdparty.aws.s3.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("cloud.aws.s3")
class S3Properties(
    val bucket: String
)