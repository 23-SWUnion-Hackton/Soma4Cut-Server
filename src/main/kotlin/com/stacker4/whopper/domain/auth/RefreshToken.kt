package com.stacker4.whopper.domain.auth

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.concurrent.TimeUnit

@RedisHash(value = "refreshToken")
class RefreshToken(

    @Id
    val refreshToken: String,

    val userId: Long,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Int

)