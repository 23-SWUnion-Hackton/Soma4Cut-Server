package com.stacker4.whopper.domain.auth.service

import com.stacker4.whopper.common.security.SecurityUtil
import com.stacker4.whopper.domain.auth.presentation.data.request.SignUpRequest
import com.stacker4.whopper.domain.auth.presentation.data.response.SuccessSignUp
import com.stacker4.whopper.domain.user.User
import com.stacker4.whopper.domain.user.constant.Role
import com.stacker4.whopper.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(rollbackFor = [Exception::class])
class SignUpService(
    private val userRepository: UserRepository,
    private val securityUtil: SecurityUtil
) {
    fun execute(signUpRequest: SignUpRequest): SuccessSignUp {
        val user = User(
            id = UUID.randomUUID(),
            name = signUpRequest.name,
            password = securityUtil.passwordEncode(signUpRequest.password),
            role = Role.ROLE_USER
        )

        userRepository.save(user)

        return SuccessSignUp(
            message = "회원가입에 성공하였습니다.",
            status = 201
        )
    }
}