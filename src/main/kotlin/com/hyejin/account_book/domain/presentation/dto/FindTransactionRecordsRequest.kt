package com.hyejin.account_book.domain.presentation.dto

import jakarta.validation.constraints.NotEmpty

data class FindTransactionRecordsRequest(
    @field:NotEmpty
    val userId: Long,

    val categoryId: Long?,

    @field:NotEmpty
    val year: Int,

    @field:NotEmpty
    val month: Int,
)