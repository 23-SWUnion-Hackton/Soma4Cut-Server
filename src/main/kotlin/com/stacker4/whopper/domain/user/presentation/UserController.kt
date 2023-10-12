package com.stacker4.whopper.domain.user.presentation

import com.stacker4.whopper.domain.image.dto.response.QueryAllImageResponse
import com.stacker4.whopper.domain.image.dto.response.QueryImageByCodeResponse
import com.stacker4.whopper.domain.image.dto.response.UploadImageResponse
import com.stacker4.whopper.domain.image.service.QueryAllImageService
import com.stacker4.whopper.domain.image.service.QueryImageByCodeService
import com.stacker4.whopper.domain.image.service.UploadImageService
import com.stacker4.whopper.domain.image.service.UploadImagesService
import com.stacker4.whopper.domain.user.presentation.data.response.QueryCodeSpaceResponse
import com.stacker4.whopper.domain.user.presentation.data.response.SuccessUploadCodeAtSpaceResponse
import com.stacker4.whopper.domain.user.service.QueryCodeSpaceService
import com.stacker4.whopper.domain.user.service.UploadCodeAtSpaceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/user")
class UserController(
    private val uploadImageService: UploadImageService,
    private val uploadImagesService: UploadImagesService,
    private val queryImageByCodeService: QueryImageByCodeService,
    private val queryAllImageService: QueryAllImageService,
    private val uploadCodeAtSpaceService: UploadCodeAtSpaceService,
    private val queryCodeSpaceService: QueryCodeSpaceService
) {
    @PostMapping("/image/complete")
    fun uploadImage(@RequestPart("image") image: MultipartFile): ResponseEntity<UploadImageResponse> =
        uploadImageService.execute(image)
            .let { ResponseEntity.ok(it) }

    @PostMapping("/image")
    fun uploadImages(@RequestPart("image1") image1: MultipartFile,
                     @RequestPart("image2") image2: MultipartFile,
                     @RequestPart("image3") image3: MultipartFile,
                     @RequestPart("image4") image4: MultipartFile): ResponseEntity<UploadImageResponse> =
        uploadImagesService.execute(listOf(image1, image2, image3, image4))
            .let { ResponseEntity.ok(it) }

    @GetMapping("/image/{code}")
    fun queryImageByCode(@PathVariable code: String): ResponseEntity<List<QueryImageByCodeResponse>> =
        queryImageByCodeService.execute(code)
            .let { ResponseEntity.ok(it) }

    @GetMapping("/image/all")
    fun queryAllImage(): ResponseEntity<List<QueryAllImageResponse>> =
        queryAllImageService.execute()
            .let { ResponseEntity.ok(it) }

    @PostMapping("/soma-space/{code}")
    fun uploadCodeAtSpace(@PathVariable code: String): ResponseEntity<SuccessUploadCodeAtSpaceResponse> =
        uploadCodeAtSpaceService.execute(code)
            .let { ResponseEntity.ok(it) }

    @GetMapping("/soma-space")
    fun queryCodeSpace(): ResponseEntity<List<QueryCodeSpaceResponse>> =
        queryCodeSpaceService.execute()
            .let { ResponseEntity.ok(it) }
}