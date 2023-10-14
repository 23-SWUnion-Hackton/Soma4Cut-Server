package com.stacker4.whopper.domain.code.service

import com.stacker4.whopper.common.security.SecurityUtil
import com.stacker4.whopper.domain.code.Code
import com.stacker4.whopper.domain.code.constant.Space
import com.stacker4.whopper.domain.code.exception.CodeNotFoundException
import com.stacker4.whopper.domain.code.presentation.data.request.SaveCodeRequest
import com.stacker4.whopper.domain.code.presentation.data.response.SuccessSaveCodeResponse
import com.stacker4.whopper.domain.code.repository.CodeRepository
import com.stacker4.whopper.domain.image.Image
import com.stacker4.whopper.domain.image.repository.ImageRepository
import com.stacker4.whopper.domain.user.exception.UserNotFoundException
import com.stacker4.whopper.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(rollbackFor = [Exception::class])
class SaveCodeService(
    private val codeRepository: CodeRepository,
    private val securityUtil: SecurityUtil,
    private val userRepository: UserRepository,
    private val imageRepository: ImageRepository
) {
    fun execute(saveCodeRequest: SaveCodeRequest): SuccessSaveCodeResponse {
        val user = userRepository.findByIdOrNull(securityUtil.getCurrentUserId()) ?: throw UserNotFoundException()
        val code = codeRepository.findAllByName(saveCodeRequest.code) ?: throw CodeNotFoundException()
        val codeEntity = codeRepository.save(Code(
            id = 0,
            name = saveCodeRequest.code,
            createdAt = LocalDateTime.now(),
            space = Space.NOT_SPACE,
            user = user
        ))

        code.map {
            imageRepository.findAllByCode(it).map { image ->
                imageRepository.save(Image(
                    id = 0,
                    name = image.name,
                    createdAt = LocalDateTime.now(),
                    user = user,
                    code = codeEntity
                ))
            }
        }
//        imageRepository.findAllByCode(code).map {
//            imageRepository.save(Image(
//                id = 0,
//                name = it.name,
//                createdAt = LocalDateTime.now(),
//                user = user,
//                code = codeEntity
//            ))
//        }

        return SuccessSaveCodeResponse(
            message = "코드 저장을 성공하였습니다.",
            status = 201
        )
    }
}