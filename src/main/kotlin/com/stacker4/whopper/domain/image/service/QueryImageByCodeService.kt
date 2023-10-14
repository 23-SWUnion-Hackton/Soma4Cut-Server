package com.stacker4.whopper.domain.image.service

import com.stacker4.whopper.domain.code.repository.CodeRepository
import com.stacker4.whopper.domain.image.dto.response.QueryImageByCodeResponse
import com.stacker4.whopper.domain.image.repository.ImageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryImageByCodeService(
    private val imageRepository: ImageRepository,
    private val codeRepository: CodeRepository
) {
    fun execute(code: String): List<QueryImageByCodeResponse> {
        val code = codeRepository.findAllByName(code)

        return code.flatMap { code ->
            imageRepository.findAllByCode(code).map { image ->
                QueryImageByCodeResponse(image.name)
            }
        }
    }
}