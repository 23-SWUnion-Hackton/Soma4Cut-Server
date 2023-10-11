package com.stacker4.whopper.domain.auth.presentation.data.request

import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

class SignUpRequest(
    @field:Size(min = 1, max = 10)
    val name: String,

    @field:Pattern(regexp = "^(?=.*[!@#\$%^&*()_+|~-]).{8,16}\$")
    val password: String
)