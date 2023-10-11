package com.stacker4.whopper.global.security.handler

import com.stacker4.whopper.global.error.ErrorCode
import com.stacker4.whopper.global.security.exception.ForbiddenException
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationEntryPointHandler : AuthenticationEntryPoint {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        log.info("========== AuthenticationEntryPoint ==========")
        throw ForbiddenException()
    }
}