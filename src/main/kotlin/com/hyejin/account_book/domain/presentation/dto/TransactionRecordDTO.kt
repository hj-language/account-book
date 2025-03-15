package com.hyejin.account_book.domain.presentation.dto

import com.hyejin.account_book.domain.entity.Category
import com.hyejin.account_book.domain.entity.TransactionRecord
import java.time.LocalDate

data class TransactionRecordDTO(
        val amount: Int,
        val memo: String,
        val transactionDate: LocalDate,
        val category: Category
) {
    constructor(transactionRecord: TransactionRecord) : this(
            amount = transactionRecord.amount,
            memo = transactionRecord.memo,
            transactionDate = transactionRecord.transactionDate,
            category = transactionRecord.category
    )
}