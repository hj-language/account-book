package com.hyejin.account_book.domain.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "transaction")
class TransactionRecord(
    val amount: Int,
    val memo: String?,
    val transactionDate: LocalDate = LocalDate.now(),
    val userId: Long,
    @ManyToOne @JoinColumn(name = "category_id") val category: Category
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var deletedDateTime: LocalDateTime? = null
}