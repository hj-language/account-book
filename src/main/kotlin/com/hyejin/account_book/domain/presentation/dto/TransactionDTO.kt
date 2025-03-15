package com.hyejin.account_book.domain.presentation.dto

import Category
import Transaction
import java.time.LocalDate

data class TransactionDTO(
        val amount: Int,
        val memo: String,
        val transactionDate: LocalDate,
        val category: Category
) {
    constructor(transaction: Transaction) : this(
            amount = transaction.amount,
            memo = transaction.memo,
            transactionDate = transaction.transactionDate,
            category = transaction.category
    )
}