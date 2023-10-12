package com.stacker4.whopper.domain.code.exception

import com.stacker4.whopper.global.error.ErrorCode
import com.stacker4.whopper.global.error.exception.Soma4CutException

class CodeNotFoundException : Soma4CutException(ErrorCode.CODE_NOT_FOUND)