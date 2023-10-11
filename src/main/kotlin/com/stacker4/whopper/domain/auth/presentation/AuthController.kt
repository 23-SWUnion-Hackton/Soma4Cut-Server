package com.stacker4.whopper.domain.auth.presentation

import com.stacker4.whopper.domain.auth.presentation.data.request.SignUpRequest
import com.stacker4.whopper.domain.auth.service.SignInService
import com.stacker4.whopper.domain.auth.service.SignUpService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val signUpService: SignUpService,
    private val signInService: SignInService
) {
    @PostMapping
    fun signUp(signUpRequest: SignUpRequest): ResponseEntity<Void> =
        signUpService.execute(signUpRequest)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }
}