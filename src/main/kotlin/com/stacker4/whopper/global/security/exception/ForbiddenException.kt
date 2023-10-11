package com.stacker4.whopper.global.security.exception

import com.stacker4.whopper.global.error.ErrorCode
import com.stacker4.whopper.global.error.exception.Soma4CutException

class ForbiddenException : Soma4CutException(ErrorCode.FORBIDDEN)