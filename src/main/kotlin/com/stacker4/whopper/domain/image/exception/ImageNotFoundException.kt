package com.stacker4.whopper.domain.image.exception

import com.stacker4.whopper.global.error.ErrorCode
import com.stacker4.whopper.global.error.exception.Soma4CutException

class ImageNotFoundException : Soma4CutException(ErrorCode.IMAGE_NOT_FOUND)