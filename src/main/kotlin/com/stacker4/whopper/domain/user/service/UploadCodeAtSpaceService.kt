package com.stacker4.whopper.domain.user.service

import com.stacker4.whopper.domain.code.constant.Space
import com.stacker4.whopper.domain.code.exception.CodeNotFoundException
import com.stacker4.whopper.domain.code.repository.CodeRepository
import com.stacker4.whopper.domain.user.presentation.data.response.SuccessUploadCodeAtSpaceResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class UploadCodeAtSpaceService(
    private val codeRepository: CodeRepository
) {
    fun execute(code: String): List<SuccessUploadCodeAtSpaceResponse> {
//        val codeEntity = codeRepository.findByName(code) ?: throw CodeNotFoundException()
        return codeRepository.findAllByName(code).map {
            codeRepository.save(it.copy(space = Space.SPACE))
            SuccessUploadCodeAtSpaceResponse(
                message = "소마 스페이스에 이미지 업로드 성공",
                status = 201
            )
        }
    }
}