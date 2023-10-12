package com.stacker4.whopper.domain.image.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class RemoveBgRequest(
    val image_url: String,
    val size: String,
    val type: String,
    val type_level: String,
    val format: String,
    val roi: String,
    val crop: Boolean,
    val crop_margin: String,
    val scale: String,
    val position: String,
    val channels: String,
    val add_shadow: Boolean,
    val semitransparency: Boolean,
    val bg_color: String,
    val bg_image_url: String
)