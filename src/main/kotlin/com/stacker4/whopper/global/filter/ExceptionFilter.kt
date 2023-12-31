package com.stacker4.whopper.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.stacker4.whopper.global.error.ErrorCode
import com.stacker4.whopper.global.error.ErrorResponse
import com.stacker4.whopper.global.error.exception.Soma4CutException
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionFilter : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching {
            filterChain.doFilter(request, response)
        }.onFailure { e ->
            log.error(e.message)
            when (e) {
                is Soma4CutException -> sendError(response, e.errorCode)
                is Exception -> sendError(response, ErrorCode.INTERNAL_SERVER_ERROR)
            }
        }
    }

    private fun sendError(response: HttpServletResponse, errorCode: ErrorCode) {
        val errorResponse = ErrorResponse(errorCode.message, errorCode.status)
        val responseString = ObjectMapper().writeValueAsString(errorResponse)
        response.status = errorCode.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "utf-8"
        response.writer.write(responseString)
    }
}