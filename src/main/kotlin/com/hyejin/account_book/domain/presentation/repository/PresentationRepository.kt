package com.hyejin.account_book.domain.presentation.repository

import com.hyejin.account_book.domain.entity.Category
import com.hyejin.account_book.domain.entity.TransactionRecord
import com.hyejin.account_book.domain.repository.CategoryRepository
import com.hyejin.account_book.domain.repository.TransactionRecordRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

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
}