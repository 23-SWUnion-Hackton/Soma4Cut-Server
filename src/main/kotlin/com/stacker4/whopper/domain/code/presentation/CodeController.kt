package com.stacker4.whopper.domain.code.presentation

import com.stacker4.whopper.domain.code.presentation.data.response.QueryAllCodeResponse
import com.stacker4.whopper.domain.code.service.QueryAllCodeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class CodeController(
    private val queryAllCodeService: QueryAllCodeService
) {
    @GetMapping("/code")
    fun queryCodes(): ResponseEntity<List<QueryAllCodeResponse>> =
        queryAllCodeService.execute()
            .let { ResponseEntity.ok(it) }
}