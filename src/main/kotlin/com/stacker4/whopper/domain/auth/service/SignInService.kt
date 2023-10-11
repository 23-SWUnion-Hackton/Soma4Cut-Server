package com.stacker4.whopper.domain.auth.service

import com.stacker4.whopper.common.SecurityUtil
import com.stacker4.whopper.domain.auth.presentation.data.request.SignInRequest
import com.stacker4.whopper.domain.auth.presentation.data.response.TokenResponse
import com.stacker4.whopper.domain.user.exception.PasswordNotMatchException
import com.stacker4.whopper.domain.user.exception.UserNotFoundException
import com.stacker4.whopper.domain.user.repository.UserRepository
import com.stacker4.whopper.global.security.token.jwt.JwtTokenGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class SignInService(
    private val userRepository: UserRepository,
    private val securityUtil: SecurityUtil,
    private val tokenGenerator: JwtTokenGenerator
) {
    fun execute(signInRequest: SignInRequest): TokenResponse {
        val user = userRepository.findByName(signInRequest.name) ?: throw UserNotFoundException()

        if (!securityUtil.isPasswordMatch(signInRequest.password, user.password))
            throw PasswordNotMatchException()

        return tokenGenerator.receiveToken(user.id, user.role)
    }
}