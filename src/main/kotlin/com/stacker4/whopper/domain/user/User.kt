package com.stacker4.whopper.domain.user

import javax.persistence.*

@Entity
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(nullable = false, unique = true)
    val password: String

)