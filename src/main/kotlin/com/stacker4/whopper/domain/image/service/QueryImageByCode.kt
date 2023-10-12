package com.stacker4.whopper.domain.image.service

import com.stacker4.whopper.domain.image.dto.response.QueryImageByCodeResponse
import com.stacker4.whopper.domain.image.exception.ImageNotFoundException
import com.stacker4.whopper.domain.image.repository.ImageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class], readOnly = true)
class QueryImageByCode(
    private val imageRepository: ImageRepository
) {
    fun execute(code: String): QueryImageByCodeResponse {
        val image = imageRepository.findByName("https://soma-4cut.s3.ap-northeast-2.amazonaws.com/$code") ?: throw ImageNotFoundException()
        return QueryImageByCodeResponse(
            image.name
        )
    }
}