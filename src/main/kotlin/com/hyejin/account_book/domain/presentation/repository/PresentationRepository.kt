package com.hyejin.account_book.domain.presentation.repository

import com.hyejin.account_book.domain.entity.Category
import com.hyejin.account_book.domain.entity.TransactionRecord
import com.hyejin.account_book.domain.repository.CategoryRepository
import com.hyejin.account_book.domain.repository.TransactionRecordRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
class PresentationRepository(
    private val categoryRepository: CategoryRepository,
    private val transactionRecordRepository: TransactionRecordRepository,
) {
    fun getCategoriesByType(type: String): List<Category> {
        return categoryRepository.findByType(type)
    }

    fun getTransactions(userId: Long, start: LocalDate, end: LocalDate, categoryId: Long?): List<TransactionRecord> {
        if (categoryId == null) {
            return transactionRecordRepository.findByTransactionDateBetweenAndUserId(
                start, end, userId
            )
        }

        return transactionRecordRepository.findByTransactionDateBetweenAndCategoryIdAndUserId(
            start, end, categoryId, userId
        )
    }

    fun createTransaction(
        amount: Int,
        memo: String?,
        transactionDate: LocalDate,
        userId: Long,
        categoryId: Long
    ): TransactionRecord {
        val category = categoryRepository.findById(categoryId).orElseThrow()
        val transactionRecord = TransactionRecord(
            amount = amount,
            memo = memo,
            transactionDate = transactionDate,
            userId = userId,
            category = category
        )

        return transactionRecordRepository.save(transactionRecord)
    }

    fun updateTransaction(
        id: Long,
        amount: Int,
        memo: String?,
        transactionDate: LocalDate,
        categoryId: Long,
        userId: Long
    ): TransactionRecord {
        val category = categoryRepository.findById(categoryId).orElseThrow()
        val transactionRecord = transactionRecordRepository.findById(id).orElseThrow()

        if (transactionRecord.userId != userId) {
            throw IllegalAccessException("You can't update other user's transaction")
        }

        transactionRecord.amount = amount
        transactionRecord.memo = memo
        transactionRecord.transactionDate = transactionDate
        transactionRecord.category = category

        return transactionRecordRepository.save(transactionRecord)
    }

    fun deleteTransaction(id: Long, userId: Long): TransactionRecord {
        val transactionRecord = transactionRecordRepository.findById(id).orElseThrow()

        if (transactionRecord.userId != userId) {
            throw IllegalAccessException("You can't delete other user's transaction")
        }

        transactionRecord.deletedDateTime = LocalDateTime.now()
        return transactionRecordRepository.save(transactionRecord)
    }


}