package com.stacker4.whopper.domain.user.presentation

import com.stacker4.whopper.domain.user.presentation.data.response.QueryAllCodeResponse
import com.stacker4.whopper.domain.image.dto.response.UploadImageResponse
import com.stacker4.whopper.domain.user.service.QueryAllCodeService
import com.stacker4.whopper.domain.image.service.UploadImageService
import com.stacker4.whopper.domain.image.service.UploadImagesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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
    private val queryAllCodeService: QueryAllCodeService
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

    @GetMapping("/code")
    fun queryCodes(): ResponseEntity<List<QueryAllCodeResponse>> =
        queryAllCodeService.execute()
            .let { ResponseEntity.ok(it) }

}