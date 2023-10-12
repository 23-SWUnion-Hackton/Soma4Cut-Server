package com.stacker4.whopper.domain.image.repository

import com.stacker4.whopper.domain.code.Code
import com.stacker4.whopper.domain.image.Image
import com.stacker4.whopper.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository : JpaRepository<Image, Long> {
    fun findByName(name: String): Image?
    fun findAllByCode(code: Code): List<Image>
    fun findAllByCodeName(name: String): List<Image>
    fun findAllByUser(user: User): List<Image>
}