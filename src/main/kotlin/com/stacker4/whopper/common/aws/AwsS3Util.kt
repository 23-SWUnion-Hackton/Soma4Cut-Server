package com.stacker4.whopper.common.aws

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.*
import com.stacker4.whopper.thirdparty.aws.s3.config.S3Properties
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.io.IOException
import java.util.stream.Collectors
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request
import software.amazon.awssdk.services.s3.model.S3Exception
import software.amazon.awssdk.services.s3.model.S3Object
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable
import java.lang.RuntimeException


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

    fun queryImageUrl(fileName: String): String =
        amazonS3.getUrl(s3Properties.bucket, fileName).toString()
//
////    fun queryAllImage(): List<String> =
////        amazonS3.getObject(s3Properties.bucket)
//
//    fun getAllKeys(bucket: Bucket, prefix: String?): List<String>? {
//        try {
//            S3Client(bucket).use { s3Client ->
//                val keys: MutableList<String> = ArrayList()
//                val listReq: ListObjectsV2Request =
//                    ListObjectsV2Request.builder()
//                        .bucket(bucket.name)
//                        .maxKeys(1000)
//                        .prefix(prefix)
//                        .build()
//                val listRes: ListObjectsV2Iterable = s3Client.listObjectsV2Paginator(listReq)
//                listRes.stream()
//                    .forEach { r ->
//                        keys.addAll(
//                            r.contents()
//                                .stream()
//                                .map(S3Object::key)
//                                .collect(Collectors.toList())
//                        )
//                    }
//                return keys
//            }
//        } catch (e: S3Exception) {
//            throw RuntimeException(ERROR.FILE_COPY_FAIL)
//        }
//    }
}