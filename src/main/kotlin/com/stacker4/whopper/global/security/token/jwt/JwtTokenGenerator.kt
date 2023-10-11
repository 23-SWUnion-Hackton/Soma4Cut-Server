package com.stacker4.whopper.global.security.token.jwt

import com.stacker4.whopper.domain.auth.RefreshToken
import com.stacker4.whopper.domain.auth.presentation.data.response.TokenResponse
import com.stacker4.whopper.domain.auth.repository.RefreshTokenRepository
import com.stacker4.whopper.domain.user.constant.Role
import com.stacker4.whopper.global.security.token.jwt.property.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class JwtTokenGenerator(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun receiveToken(userId: UUID, role: Role): TokenResponse {
        val refreshToken = generateRefreshToken(userId)
        refreshTokenRepository.save(RefreshToken(refreshToken, userId, jwtProperties.refreshExp))
        return TokenResponse(
            accessToken = generateAccessToken(userId, role),
            refreshToken = refreshToken,
            accessExpiredAt = getAccessTokenExpiredAt(),
            refreshExpiredAt = getRefreshTokenExpiredAt()
        )
    }

    private fun generateAccessToken(userId: UUID, role: Role): String =
        Jwts.builder()
            .signWith(jwtProperties.accessSecret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim("type", "access")
            .claim("authority", role)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp * 1000))
            .compact()


    private fun generateRefreshToken(userId: UUID): String =
        Jwts.builder()
            .signWith(jwtProperties.refreshSecret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim("type", "refresh")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.refreshExp * 1000))
            .compact()


    private fun getAccessTokenExpiredAt(): LocalDateTime =
        LocalDateTime.now().plusSeconds(jwtProperties.accessExp.toLong())

    private fun getRefreshTokenExpiredAt(): LocalDateTime =
        LocalDateTime.now().plusSeconds(jwtProperties.refreshExp.toLong())
}