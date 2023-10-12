package com.stacker4.whopper.domain.code.presentation.data.response

import java.time.LocalDateTime

data class QueryAllCodeResponse(
    val code: String,
    val createdAt: LocalDateTime
)