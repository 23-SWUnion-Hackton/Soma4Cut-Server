package com.stacker4.whopper.domain.user.service

import com.stacker4.whopper.domain.user.presentation.data.response.QueryAllCodeResponse
import com.stacker4.whopper.domain.image.repository.ImageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryAllCodeService(
    private val imageRepository: ImageRepository
) {
    fun execute(): List<QueryAllCodeResponse> =
        imageRepository.findAll().map {
            QueryAllCodeResponse(
                code = it.name,
                createdAt = it.createdAt
            )
        }
}