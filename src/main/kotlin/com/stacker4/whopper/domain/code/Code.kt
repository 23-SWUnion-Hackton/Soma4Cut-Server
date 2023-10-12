package com.stacker4.whopper.domain.code

import com.stacker4.whopper.domain.code.constant.Space
import com.stacker4.whopper.domain.image.Image
import com.stacker4.whopper.domain.user.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Code(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val space: Space,

    @ManyToOne(fetch = FetchType.LAZY)
    val user: User
)