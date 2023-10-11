package com.stacker4.whopper.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {
    // server
    INTERNAL_SERVER_ERROR("서버 오류", 500)
}