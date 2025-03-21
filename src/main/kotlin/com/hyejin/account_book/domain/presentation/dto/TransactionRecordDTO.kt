package com.hyejin.account_book.domain.presentation.dto

import java.time.LocalDate

data class TransactionRecordDTO(
    val id: Long,
    val amount: Int,
    val memo: String?,
    val transactionDate: LocalDate,
    val category: CategoryDTO
)