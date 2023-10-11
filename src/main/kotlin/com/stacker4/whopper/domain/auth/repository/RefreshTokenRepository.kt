package com.stacker4.whopper.domain.auth.repository

import com.stacker4.whopper.domain.auth.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, String>