package com.stacker4.whopper.global.security.token.jwt

import com.stacker4.whopper.global.security.principal.UserDetailsService
import com.stacker4.whopper.global.security.token.jwt.property.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenParser(
    private val jwtProperties: JwtProperties,
    private val userDetailsService: UserDetailsService
) {
    fun parseAccessToken(request: HttpServletRequest): String? =
        request.getHeader(JwtProperties.tokenHeader)
            .let { it ?: return null }
            .let {
                if(it.startsWith(JwtProperties.tokenPrefix))
                    it.replace(JwtProperties.tokenPrefix, "")
                else null
            }

    fun parseRefreshToken(refreshToken: String): String? =
        if(refreshToken.startsWith(JwtProperties.tokenPrefix))
            refreshToken.replace(JwtProperties.tokenPrefix, "")
        else null

    fun authentication(accessToken: String): Authentication =
        userDetailsService.loadUserByUsername(getTokenBody(accessToken, jwtProperties.accessSecret).subject)
            .let { UsernamePasswordAuthenticationToken(it, "", it.authorities) }

    fun isRefreshTokenExpired(refreshToken: String): Boolean {
        runCatching {
            getTokenBody(refreshToken, jwtProperties.refreshSecret).subject
        }.onFailure {
            return true
        }
        return false
    }

    private fun getTokenBody(token: String, secret: Key): Claims =
        Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
            .body
}