package com.stacker4.whopper.domain.image.service

import com.stacker4.whopper.common.aws.AwsS3Util
import com.stacker4.whopper.domain.image.dto.request.RemoveBgRequest
import com.stacker4.whopper.domain.image.dto.response.UploadImageResponse
import com.stacker4.whopper.domain.image.exception.FailedConvertImage
import com.stacker4.whopper.domain.image.exception.NotValidExtensionException
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream

@Service
@Transactional(rollbackFor = [Exception::class])
class UploadImageService(
    private val awsS3Util: AwsS3Util
) {
    companion object {
        const val apiUrl = "https://api.remove.bg/v1.0/removebg"
        const val apiKey = "Z43jY6rcURUzYk8euKqRcKAu"
        const val header = "X-Api-Key"
        const val size = "auto"
    }

    fun execute(image: MultipartFile): UploadImageResponse {
        val restTemplate = RestTemplate()
        val allowedExtensions = listOf("jpeg", "jpg", "png")
        val fileExtension = image.originalFilename?.substringAfterLast(".","")?.lowercase()

        if (fileExtension !in allowedExtensions)
            throw NotValidExtensionException()

        val fileName = RandomStringUtils.random(8, true, true)

        awsS3Util.uploadImage(image, fileName)

        val headers = HttpHeaders()
        headers.set(header, apiKey)
        headers.contentType = MediaType.APPLICATION_JSON

        val requestBody = RemoveBgRequest(
            image_url = "https://soma-4cut.s3.ap-northeast-2.amazonaws.com/$fileName",
            size = size,
            type = "auto",
            type_level = "1",
            format = "auto",
            roi = "0% 0% 100% 100%",
            crop = false,
            crop_margin = "0",
            scale = "original",
            position = "original",
            channels = "rgba",
            add_shadow = false,
            semitransparency = true,
            bg_color = "",
            bg_image_url = ""
        )

        val requestEntity = HttpEntity(requestBody, headers)
        val responseEntity = restTemplate.postForObject(apiUrl, requestEntity, ByteArray::class.java) ?: throw FailedConvertImage()
        awsS3Util.deleteImage(fileName)
        awsS3Util.uploadImage(byteArrayToMultipartFile(responseEntity, fileName), fileName)

        return UploadImageResponse(fileName)
    }

    private fun byteArrayToMultipartFile(byteArray: ByteArray, fileName: String): MultipartFile {
        val multipartFile = object : MultipartFile {
            override fun getName(): String = "file"

            override fun getOriginalFilename(): String? = fileName

            override fun getContentType(): String? = null

            override fun isEmpty(): Boolean = false

            override fun getSize(): Long = byteArray.size.toLong()

            override fun getBytes(): ByteArray = byteArray

            override fun getInputStream(): ByteArrayInputStream = ByteArrayInputStream(byteArray)

            override fun transferTo(dest: java.io.File) {
                java.nio.file.Files.write(dest.toPath(), byteArray)
            }
        }
        return multipartFile
    }
}
