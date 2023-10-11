package com.stacker4.whopper.domain.user.repository

import com.stacker4.whopper.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
    fun findByName(name: String): User?
}