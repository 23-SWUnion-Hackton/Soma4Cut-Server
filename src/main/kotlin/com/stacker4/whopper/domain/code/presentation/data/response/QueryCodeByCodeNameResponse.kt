package com.stacker4.whopper.domain.code.presentation.data.response

import java.time.LocalDateTime

class QueryCodeByCodeNameResponse(
    val code: String,
    val createdAt: LocalDateTime,
    val image: List<String>
)