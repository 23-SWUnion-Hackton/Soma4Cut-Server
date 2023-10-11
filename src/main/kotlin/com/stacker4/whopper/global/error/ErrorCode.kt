package com.stacker4.whopper.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {
    // server
    INTERNAL_SERVER_ERROR("서버 오류", 500),

    // auth
    INVALID_ROLE("검증되지 않은 권한입니다.", 401)
}