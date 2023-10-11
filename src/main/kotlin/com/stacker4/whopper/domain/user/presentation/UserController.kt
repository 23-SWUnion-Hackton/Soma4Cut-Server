package com.stacker4.whopper.domain.user.presentation

import com.stacker4.whopper.domain.image.service.UploadImageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/user")
class UserController(
    private val uploadImageService: UploadImageService
) {
    @PostMapping
    fun uploadImage(images: List<MultipartFile>): ResponseEntity<List<String>> =
        uploadImageService.execute(images)
            .let { ResponseEntity.ok(it) }
}