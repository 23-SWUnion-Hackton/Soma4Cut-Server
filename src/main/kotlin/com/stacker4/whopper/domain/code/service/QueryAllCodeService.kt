package com.stacker4.whopper.domain.code.service

import com.stacker4.whopper.common.security.SecurityUtil
import com.stacker4.whopper.domain.code.presentation.data.response.QueryAllCodeResponse
import com.stacker4.whopper.domain.code.repository.CodeRepository
import com.stacker4.whopper.domain.image.repository.ImageRepository
import com.stacker4.whopper.domain.user.exception.UserNotFoundException
import com.stacker4.whopper.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryAllCodeService(
    private val codeRepository: CodeRepository,
    private val securityUtil: SecurityUtil,
    private val userRepository: UserRepository,
    private val imageRepository: ImageRepository
) {
    fun execute(): List<QueryAllCodeResponse> {
        val user = userRepository.findByIdOrNull(securityUtil.getCurrentUserId()) ?: throw UserNotFoundException()

        return codeRepository.findAllByUser(user).map { code ->
            val imageNames = imageRepository.findAllByCode(code).map { it.name }
            QueryAllCodeResponse(
                code = code.name,
                createdAt = code.createdAt,
                image = imageNames
            )
        }
    }
}