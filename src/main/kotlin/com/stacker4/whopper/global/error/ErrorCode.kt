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
    USER_NOT_FOUND("존재하지 않는 유저입니다.", 404),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.", 400),

    // image
    NOT_VALID_EXTENSION("유효하지 않은 확장자입니다.", 400),
    FAILED_CONVERT_IMAGE("이미지 변환에 실패하였습니다.", 400),
    IMAGE_NOT_FOUND("존재하지 않는 이미지입니다.", 404),

    // code
    CODE_NOT_FOUND("존재하지 않는 코드입니다.", 404)
}