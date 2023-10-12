package com.stacker4.whopper.domain.image.service

import com.stacker4.whopper.common.aws.AwsS3Util
import com.stacker4.whopper.domain.image.dto.response.UploadImageResponse
import com.stacker4.whopper.domain.image.exception.NotValidExtensionException
import com.stacker4.whopper.domain.image.util.ImageUtil
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional(rollbackFor = [Exception::class])
class UploadImageService(
    private val awsS3Util: AwsS3Util,
    private val imageUtil: ImageUtil
) {
    fun execute(image: MultipartFile): UploadImageResponse {
        val allowedExtensions = listOf("jpeg", "jpg", "png")
        val fileExtension = image.originalFilename?.substringAfterLast(".","")?.lowercase()

        if (fileExtension !in allowedExtensions)
            throw NotValidExtensionException()

        val fileName = RandomStringUtils.random(8, true, true)

        awsS3Util.uploadImage(image, fileName)

        return UploadImageResponse(fileName)
    }
}
