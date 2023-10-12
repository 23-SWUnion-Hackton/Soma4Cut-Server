package com.stacker4.whopper.domain.image

import com.stacker4.whopper.domain.code.Code
import com.stacker4.whopper.domain.user.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Image(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id")
    val code: Code

)