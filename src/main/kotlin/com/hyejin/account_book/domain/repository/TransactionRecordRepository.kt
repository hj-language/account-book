package com.hyejin.account_book.domain.repository

import TransactionRecord
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface TransactionRecordRepository : JpaRepository<TransactionRecord, Long> {
    fun findByTransactionDateBetweenAndUserId(start: LocalDate, end: LocalDate, userId: Long): List<TransactionRecord>
    fun findByTransactionDateBetweenAndCategoryIdAndUserId(start: LocalDate, end: LocalDate, categoryId: Long, userId: Long): List<TransactionRecord>
}