package com.stacker4.whopper.global.error.exception

import com.stacker4.whopper.global.error.ErrorCode

class InternalServerError : Soma4CutException(ErrorCode.INTERNAL_SERVER_ERROR)