package com.stacker4.whopper.domain.code.repository

import com.stacker4.whopper.domain.code.Code
import org.springframework.data.jpa.repository.JpaRepository

interface CodeRepository : JpaRepository<Code, Long> {
}