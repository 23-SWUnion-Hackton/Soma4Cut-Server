package com.stacker4.whopper.domain.code.service

import com.stacker4.whopper.domain.code.presentation.data.response.QueryAllCodeResponse
import com.stacker4.whopper.domain.code.repository.CodeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryAllCodeService(
    private val codeRepository: CodeRepository
) {
    fun execute(): List<QueryAllCodeResponse> =
        codeRepository.findAll().map {
            QueryAllCodeResponse(
                code = it.name,
                createdAt = it.createdAt
            )
        }
}