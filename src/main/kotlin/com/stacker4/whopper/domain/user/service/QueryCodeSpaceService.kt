package com.stacker4.whopper.domain.user.service

import com.stacker4.whopper.domain.code.constant.Space
import com.stacker4.whopper.domain.code.repository.CodeRepository
import com.stacker4.whopper.domain.image.repository.ImageRepository
import com.stacker4.whopper.domain.user.presentation.data.response.QueryCodeSpaceResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class], readOnly = true)
class QueryCodeSpaceService(
    private val codeRepository: CodeRepository,
    private val imageRepository: ImageRepository
) {
    fun execute(): List<QueryCodeSpaceResponse> {
        return codeRepository.findAllBySpace(Space.SPACE).flatMap { code ->
            imageRepository.findAllByCode(code).map {
                QueryCodeSpaceResponse(
                    name = it.name
                )
            }
        }
    }
}