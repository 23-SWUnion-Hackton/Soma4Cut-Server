package com.stacker4.whopper.domain.code.repository

import com.stacker4.whopper.domain.code.Code
import com.stacker4.whopper.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface CodeRepository : JpaRepository<Code, Long> {
    fun findAllByUser(user: User): List<Code>
    fun findByName(name: String): Code?
}