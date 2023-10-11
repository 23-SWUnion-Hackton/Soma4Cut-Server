package com.stacker4.whopper.global.error.handler

import com.stacker4.whopper.global.error.ErrorResponse
import com.stacker4.whopper.global.error.exception.Soma4CutException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Soma4CutException::class)
    protected fun soma4CutExceptionHandler(e: Soma4CutException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.valueOf(e.errorCode.status)).body(ErrorResponse(e.errorCode.message, e.errorCode.status))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ErrorResponse(e.bindingResult.fieldError?.defaultMessage.toString(), HttpStatus.BAD_REQUEST.value()))
}