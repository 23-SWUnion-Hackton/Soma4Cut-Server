package com.stacker4.whopper.domain.code

import com.stacker4.whopper.domain.image.Image
import com.stacker4.whopper.domain.user.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Code(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    val image: Image
)