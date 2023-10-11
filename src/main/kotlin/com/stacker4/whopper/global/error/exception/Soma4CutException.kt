package com.stacker4.whopper.global.error.exception

import com.stacker4.whopper.global.error.ErrorCode

open class Soma4CutException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)