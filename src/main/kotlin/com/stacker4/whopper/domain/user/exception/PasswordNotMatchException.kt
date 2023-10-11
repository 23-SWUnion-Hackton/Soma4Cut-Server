package com.stacker4.whopper.domain.user.exception

import com.stacker4.whopper.global.error.ErrorCode
import com.stacker4.whopper.global.error.exception.Soma4CutException

class PasswordNotMatchException :  Soma4CutException(ErrorCode.PASSWORD_NOT_MATCH)