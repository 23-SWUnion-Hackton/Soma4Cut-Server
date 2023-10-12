package com.stacker4.whopper.domain.image.service

import com.stacker4.whopper.common.aws.AwsS3Util
import com.stacker4.whopper.common.security.SecurityUtil
import com.stacker4.whopper.domain.code.Code
import com.stacker4.whopper.domain.code.constant.Space
import com.stacker4.whopper.domain.code.exception.CodeNotFoundException
import com.stacker4.whopper.domain.code.repository.CodeRepository
import com.stacker4.whopper.domain.image.Image
import com.stacker4.whopper.domain.image.dto.response.UploadImageResponse
import com.stacker4.whopper.domain.image.exception.ImageNotFoundException
import com.stacker4.whopper.domain.image.exception.NotValidExtensionException
import com.stacker4.whopper.domain.image.repository.ImageRepository
import com.stacker4.whopper.domain.image.util.ImageUtil
import com.stacker4.whopper.domain.user.exception.UserNotFoundException
import com.stacker4.whopper.domain.user.repository.UserRepository
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Service
@Transactional(rollbackFor = [Exception::class])
class UploadImageService(
    private val awsS3Util: AwsS3Util,
    private val imageUtil: ImageUtil,
    private val imageRepository: ImageRepository,
    private val securityUtil: SecurityUtil,
    private val userRepository: UserRepository,
    private val codeRepository: CodeRepository
) {
    fun execute(image: MultipartFile): UploadImageResponse {
        val user = userRepository.findByIdOrNull(securityUtil.getCurrentUserId()) ?: throw UserNotFoundException()
        val allowedExtensions = listOf("jpeg", "jpg", "png")
        val fileExtension = image.originalFilename?.substringAfterLast(".","")?.lowercase()

        if (fileExtension !in allowedExtensions)
            throw NotValidExtensionException()

        val fileName = RandomStringUtils.random(8, true, true)
        val imgUrl = awsS3Util.uploadImage(image, fileName)

        codeRepository.save(Code(
            id = 0,
            name = fileName,
            createdAt = LocalDateTime.now(),
            space = Space.NOT_SPACE,
            user = user
        ))

        val code = codeRepository.findByName(fileName) ?: throw CodeNotFoundException()

        imageRepository.save(Image(
            id = 0,
            name = imgUrl,
            createdAt = LocalDateTime.now(),
            user = user,
            code = code
        ))

        return UploadImageResponse(fileName)
    }
}
