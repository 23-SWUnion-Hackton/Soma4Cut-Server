package com.stacker4.whopper.domain.user

import com.stacker4.whopper.domain.user.constant.Role
import org.hibernate.annotations.GenericGenerator
import java.util.UUID
import javax.persistence.*

@Entity
class User(

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val id: UUID,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column(nullable = false)
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role

)