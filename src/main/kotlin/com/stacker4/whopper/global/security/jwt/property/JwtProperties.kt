package com.stacker4.whopper.global.security.jwt.property

import io.jsonwebtoken.security.Keys
import java.nio.charset.StandardCharsets
import java.security.Key

class JwtProperties(
    accessSecret: String,
    refreshSecret: String,
    val accessExpiration: Int,
    val refreshExpiration: Int
) {

    val accessSecret: Key = Keys.hmacShaKeyFor(accessSecret.toByteArray(StandardCharsets.UTF_8))
    val refreshSecret: Key = Keys.hmacShaKeyFor(refreshSecret.toByteArray(StandardCharsets.UTF_8))

    companion object{
        const val accessType = "access"
        const val refreshType = "refresh"
        const val tokenPrefix = "Bearer "
        const val tokenHeader = "Authorization"
        const val roleType = "ROLE"
    }
}