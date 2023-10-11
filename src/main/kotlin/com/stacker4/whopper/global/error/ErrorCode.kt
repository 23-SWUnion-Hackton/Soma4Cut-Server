package com.stacker4.whopper.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {
    // server
    INTERNAL_SERVER_ERROR("서버 오류", 500),

    // auth
    INVALID_ROLE("검증되지 않은 권한입니다.", 401),
    FORBIDDEN("Forbidden", 403),

    // user
    USER_NOT_FOUND("존재하지 않는 유저입니다.", 404)
}