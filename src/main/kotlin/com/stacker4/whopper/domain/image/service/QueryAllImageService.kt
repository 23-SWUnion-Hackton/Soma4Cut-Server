package com.stacker4.whopper.domain.image.service

import com.stacker4.whopper.domain.auth.presentation.data.response.QueryAllImageResponse
import com.stacker4.whopper.domain.image.repository.ImageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryAllImageService(
    private val imageRepository: ImageRepository
) {
    fun execute(): List<QueryAllImageResponse> =
        imageRepository.findAll().map {
            QueryAllImageResponse(it.name)
        }
}