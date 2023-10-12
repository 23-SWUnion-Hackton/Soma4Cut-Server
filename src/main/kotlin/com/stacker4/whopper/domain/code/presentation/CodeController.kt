package com.stacker4.whopper.domain.code.presentation

import com.stacker4.whopper.domain.code.presentation.data.request.QueryCodeByCodeRequest
import com.stacker4.whopper.domain.code.presentation.data.request.SaveCodeRequest
import com.stacker4.whopper.domain.code.presentation.data.response.QueryAllCodeResponse
import com.stacker4.whopper.domain.code.presentation.data.response.QueryCodeByCodeNameResponse
import com.stacker4.whopper.domain.code.presentation.data.response.SuccessSaveCodeResponse
import com.stacker4.whopper.domain.code.service.QueryAllCodeService
import com.stacker4.whopper.domain.code.service.QueryCodeByCodeNameService
import com.stacker4.whopper.domain.code.service.SaveCodeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/code")
class CodeController(
    private val queryAllCodeService: QueryAllCodeService,
    private val queryCodeByCodeNameService: QueryCodeByCodeNameService,
    private val saveCodeService: SaveCodeService
) {
    @GetMapping
    fun queryCodes(): ResponseEntity<List<QueryAllCodeResponse>> =
        queryAllCodeService.execute()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{code}")
    fun queryCodeByCodeName(@PathVariable("code") queryCodeByCodeRequest: QueryCodeByCodeRequest): ResponseEntity<QueryCodeByCodeNameResponse> =
        queryCodeByCodeNameService.execute(queryCodeByCodeRequest)
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun saveCode(@RequestBody saveCodeRequest: SaveCodeRequest): ResponseEntity<SuccessSaveCodeResponse> =
        saveCodeService.execute(saveCodeRequest)
            .let { ResponseEntity.ok(it) }
}