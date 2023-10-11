package com.stacker4.whopper.common

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*

@Component
class SecurityUtil(
    private val passwordEncoder: PasswordEncoder
) {
    fun passwordEncode(password: String): String =
        passwordEncoder.encode(password)

    fun isPasswordMatch(rawPassword: String, encodedPassword: String): Boolean =
        passwordEncoder.matches(rawPassword, encodedPassword)

    fun getCurrentUserId(): UUID =
        UUID.fromString(SecurityContextHolder.getContext().authentication.name)
}