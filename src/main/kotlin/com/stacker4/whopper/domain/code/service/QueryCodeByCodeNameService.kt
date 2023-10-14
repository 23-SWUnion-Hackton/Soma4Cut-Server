package com.stacker4.whopper.domain.code.service

import com.stacker4.whopper.domain.code.exception.CodeNotFoundException
import com.stacker4.whopper.domain.code.presentation.data.request.QueryCodeByCodeRequest
import com.stacker4.whopper.domain.code.presentation.data.response.QueryCodeByCodeNameResponse
import com.stacker4.whopper.domain.code.repository.CodeRepository
import com.stacker4.whopper.domain.image.repository.ImageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryCodeByCodeNameService(
    private val codeRepository: CodeRepository,
    private val imageRepository: ImageRepository
) {
    fun execute(queryCodeByCodeRequest: QueryCodeByCodeRequest): QueryCodeByCodeNameResponse {
        val codeEntity = codeRepository.findAllByName(queryCodeByCodeRequest.code) ?: throw CodeNotFoundException()
        val response =  codeEntity.map {
            val image = imageRepository.findAllByCodeName(it.name)

            QueryCodeByCodeNameResponse(
                code = it.name,
                createdAt = it.createdAt,
                image = image.map { image ->
                    image.name
                }
            )
        }

        return response[0]
    }
}