package com.stacker4.whopper.domain.image.repository

import com.stacker4.whopper.domain.image.Image
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository : JpaRepository<Image, Long>{
}