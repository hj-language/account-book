package com.hyejin.account_book.domain.repository

import Transaction
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findByTransactionDateBetweenAndUserId(start: LocalDate, end: LocalDate, userId: Long): List<Transaction>
    fun findByTransactionDateBetweenAndCategoryIdAndUserId(start: LocalDate, end: LocalDate, categoryId: Long, userId: Long): List<Transaction>
}