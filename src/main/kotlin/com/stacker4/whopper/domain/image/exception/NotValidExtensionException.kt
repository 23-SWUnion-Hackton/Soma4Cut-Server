package com.stacker4.whopper.domain.image.exception

import com.stacker4.whopper.global.error.ErrorCode
import com.stacker4.whopper.global.error.exception.Soma4CutException

class NotValidExtensionException : Soma4CutException(ErrorCode.NOT_VALID_EXTENSION)