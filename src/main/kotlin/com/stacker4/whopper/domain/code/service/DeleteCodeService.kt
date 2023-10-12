package com.stacker4.whopper.domain.code.service

import com.stacker4.whopper.domain.code.exception.CodeNotFoundException
import com.stacker4.whopper.domain.code.repository.CodeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class DeleteCodeService(
    private val codeRepository: CodeRepository
) {
    fun execute(code: String) {
        val codeEntity = codeRepository.findByName(code) ?: throw CodeNotFoundException()
        codeRepository.delete(codeEntity)
    }
}