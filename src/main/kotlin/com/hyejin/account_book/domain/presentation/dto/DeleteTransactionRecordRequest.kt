package com.hyejin.account_book.domain.presentation.dto

import jakarta.validation.constraints.NotEmpty

class DeleteTransactionRecordRequest(
    @field:NotEmpty
    val userId: Long,
)