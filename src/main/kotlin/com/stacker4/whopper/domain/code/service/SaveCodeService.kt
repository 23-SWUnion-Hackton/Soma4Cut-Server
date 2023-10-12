package com.stacker4.whopper.domain.code.service

import com.stacker4.whopper.common.security.SecurityUtil
import com.stacker4.whopper.domain.code.Code
import com.stacker4.whopper.domain.code.constant.Space
import com.stacker4.whopper.domain.code.presentation.data.response.SuccessSaveCodeResponse
import com.stacker4.whopper.domain.code.repository.CodeRepository
import com.stacker4.whopper.domain.user.exception.UserNotFoundException
import com.stacker4.whopper.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(rollbackFor = [Exception::class])
class SaveCodeService(
    private val codeRepository: CodeRepository,
    private val securityUtil: SecurityUtil,
    private val userRepository: UserRepository
) {
    fun execute(code: String): SuccessSaveCodeResponse {
        val user = userRepository.findByIdOrNull(securityUtil.getCurrentUserId()) ?: throw UserNotFoundException()
        val codeEntity = Code(
            id = 0,
            name = code,
            createdAt = LocalDateTime.now(),
            space = Space.NOT_SPACE,
            user = user
        )
        codeRepository.save(codeEntity)

        return SuccessSaveCodeResponse(
            message = "코드 저장을 성공하였습니다.",
            status = 201
        )
    }
}