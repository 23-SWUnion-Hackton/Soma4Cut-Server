package com.stacker4.whopper.domain.image.service

import com.stacker4.whopper.common.aws.AwsS3Util
import com.stacker4.whopper.domain.image.exception.NotValidExtensionException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
@Transactional(rollbackFor = [Exception::class])
class UploadImageService(
    private val awsS3Util: AwsS3Util
) {
    fun execute(images: List<MultipartFile>): List<String> {
        val allowedExtensions = listOf("jpeg", "jpg", "png")

        return images.map {
            val fileExtension = it.originalFilename?.substringAfterLast(".","")?.lowercase()

            if (fileExtension !in allowedExtensions)
                throw NotValidExtensionException()

            val fileName = UUID.randomUUID().toString() + fileExtension

            awsS3Util.uploadImage(it, fileName)
        }
    }
}