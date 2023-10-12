package com.stacker4.whopper.domain.code.service

import com.stacker4.whopper.domain.code.exception.CodeNotFoundException
import com.stacker4.whopper.domain.code.presentation.data.response.QueryCodeByCodeNameResponse
import com.stacker4.whopper.domain.code.repository.CodeRepository
import com.stacker4.whopper.domain.image.repository.ImageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class QueryCodeByCodeNameService(
    private val codeRepository: CodeRepository,
    private val imageRepository: ImageRepository
) {
    fun execute(code: String): QueryCodeByCodeNameResponse {
        val codeEntity = codeRepository.findByName(code) ?: throw CodeNotFoundException()
        val image = imageRepository.findAllByCode(codeEntity)

        return QueryCodeByCodeNameResponse(
            code = code,
            createdAt = codeEntity.createdAt,
            image = image.map { it.name }
        )
    }
}