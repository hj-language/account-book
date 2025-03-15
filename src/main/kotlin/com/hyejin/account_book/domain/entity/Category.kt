package com.hyejin.account_book.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "category")
class Category(
        val name: String,
        val type: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val transactionRecords: List<TransactionRecord> = emptyList()
}