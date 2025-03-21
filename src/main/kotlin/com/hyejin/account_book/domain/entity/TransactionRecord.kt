package com.hyejin.account_book.domain.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "transaction")
class TransactionRecord(
    var amount: Int,
    var memo: String?,
    var transactionDate: LocalDate = LocalDate.now(),
    val userId: Long,
    @ManyToOne @JoinColumn(name = "category_id") var category: Category
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var deletedDateTime: LocalDateTime? = null
}