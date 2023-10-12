package com.stacker4.whopper.domain.user.service

import com.stacker4.whopper.domain.code.constant.Space
import com.stacker4.whopper.domain.code.exception.CodeNotFoundException
import com.stacker4.whopper.domain.code.repository.CodeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class UploadCodeAtSpaceService(
    private val codeRepository: CodeRepository
) {
    fun execute(code: String) {
        val codeEntity = codeRepository.findByName(code) ?: throw CodeNotFoundException()
        codeRepository.save(codeEntity.copy(space = Space.SPACE))
    }
}