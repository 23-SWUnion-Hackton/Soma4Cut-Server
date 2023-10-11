package com.stacker4.whopper.common.aws

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.stacker4.whopper.thirdparty.aws.s3.config.S3Properties
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.io.IOException

@Component
class AwsS3Util(
    private val amazonS3: AmazonS3,
    private val s3Properties: S3Properties
) {

    fun uploadImage(multipartFile: MultipartFile, fileName: String): String =
        inputS3(multipartFile, fileName)
            .run { queryImageUrl(fileName = fileName) }

    fun deleteImage(fileName: String) {
        amazonS3.deleteObject(DeleteObjectRequest(s3Properties.bucket, fileName))
    }

    private fun inputS3(multipartFile: MultipartFile, fileName: String) {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentLength = multipartFile.size
        objectMetadata.contentType = multipartFile.contentType
        try {
            amazonS3.putObject(
                PutObjectRequest(s3Properties.bucket, fileName, multipartFile.inputStream, objectMetadata)
                    .withCannedAcl(
                        CannedAccessControlList.PublicRead
                    )
            )
        } catch (e: IOException) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.")
        }
    }

    private fun queryImageUrl(fileName: String): String =
        amazonS3.getUrl(s3Properties.bucket, fileName).toString()
}