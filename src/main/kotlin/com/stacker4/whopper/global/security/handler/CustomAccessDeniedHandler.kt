package com.stacker4.whopper.global.security.handler

import com.stacker4.whopper.global.security.exception.InvalidRoleException
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAccessDeniedHandler : AccessDeniedHandler {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        log.info("========== Access Denied ==========")
        throw InvalidRoleException()
    }
}