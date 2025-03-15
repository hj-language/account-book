package com.hyejin.account_book.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "user_account")
class User(
        val name: String,
        val loginId: String,
        val password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}