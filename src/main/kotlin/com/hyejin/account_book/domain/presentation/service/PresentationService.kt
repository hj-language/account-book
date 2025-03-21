package com.hyejin.account_book.domain.presentation.service

import com.hyejin.account_book.domain.presentation.dto.*
import com.hyejin.account_book.domain.presentation.repository.PresentationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.YearMonth

@Service
class PresentationService(
    private val presentationRepository: PresentationRepository,
) {
    @Transactional(readOnly = true)
    fun getCategoriesByType(type: String): List<CategoryDTO> {
        val categories = presentationRepository.getCategoriesByType(type)

        return categories.map {
            CategoryDTO(
                id = it.id, name = it.name, type = it.type
            )
        }
    }

    @Transactional(readOnly = true)
    fun getTransactions(request: FindTransactionRecordsRequest): List<TransactionRecordDTO> {
        val start = LocalDate.of(request.year, request.month, 1)
        val end = LocalDate.of(request.year, request.month, YearMonth.of(request.year, request.month).lengthOfMonth())

        val transactions = presentationRepository.getTransactions(
            userId = request.userId, start = start, end = end, categoryId = request.categoryId
        )

        return transactions.map {
            TransactionRecordDTO(
                id = it.id!!,
                amount = it.amount,
                memo = it.memo,
                transactionDate = it.transactionDate,
                category = CategoryDTO(
                    id = it.category.id, name = it.category.name, type = it.category.type
                )
            )
        }
    }

    fun createTransaction(request: CreateTransactionRecordRequest): TransactionRecordDTO {
        val transactionRecord = presentationRepository.createTransaction(
            request.amount, request.memo, request.transactionDate, request.userId, request.categoryId
        )

        return TransactionRecordDTO(
            id = transactionRecord.id!!,
            amount = transactionRecord.amount,
            memo = transactionRecord.memo,
            transactionDate = transactionRecord.transactionDate,
            category = CategoryDTO(
                id = transactionRecord.category.id,
                name = transactionRecord.category.name,
                type = transactionRecord.category.type
            )
        )
    }

    fun updateTransaction(id: Long, request: UpdateTransactionRecordRequest): TransactionRecordDTO {
        val transactionRecord = presentationRepository.updateTransaction(
            id, request.amount, request.memo, request.transactionDate, request.categoryId, request.userId
        )

        return TransactionRecordDTO(
            id = transactionRecord.id!!,
            amount = transactionRecord.amount,
            memo = transactionRecord.memo,
            transactionDate = transactionRecord.transactionDate,
            category = CategoryDTO(
                id = transactionRecord.category.id,
                name = transactionRecord.category.name,
                type = transactionRecord.category.type
            )
        )
    }

    fun deleteTransaction(id: Long, request: DeleteTransactionRecordRequest): TransactionRecordDTO {
        val transactionRecord = presentationRepository.deleteTransaction(id, request.userId)

        return TransactionRecordDTO(
            id = transactionRecord.id!!,
            amount = transactionRecord.amount,
            memo = transactionRecord.memo,
            transactionDate = transactionRecord.transactionDate,
            category = CategoryDTO(
                id = transactionRecord.category.id,
                name = transactionRecord.category.name,
                type = transactionRecord.category.type
            )
        )
    }

}