package com.hyejin.account_book.domain.presentation.dto

import jakarta.validation.constraints.NotEmpty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class UpdateTransactionRecordRequest(
    @field:NotEmpty
    val categoryId: Long,

    @field:NotEmpty
    val amount: Int,

    val memo: String?,

    @field:NotEmpty
    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    val transactionDate: LocalDate,

    @field:NotEmpty
    val userId: Long,
)