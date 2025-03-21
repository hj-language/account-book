package com.hyejin.account_book.domain.presentation.controller

import com.hyejin.account_book.domain.presentation.dto.*
import com.hyejin.account_book.domain.presentation.service.PresentationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PresentationApiController(
    private val presentationService: PresentationService,
) {
    @GetMapping("/v1/categories")
    fun getCategoriesByType(@RequestParam categoryType: String): List<CategoryDTO> {
        return presentationService.getCategoriesByType(categoryType)
    }

    @GetMapping("/v1/transactions")
    fun getTransactions(@ModelAttribute request: FindTransactionRecordsRequest): List<TransactionRecordDTO> {
        return presentationService.getTransactions(request)
    }

    @PostMapping("/v1/transactions")
    fun createTransaction(@RequestBody request: CreateTransactionRecordRequest): TransactionRecordDTO {
        return presentationService.createTransaction(request)
    }


    @PutMapping("/v1/transactions/{id}")
    fun createTransaction(
        @PathVariable id: Long,
        @RequestBody request: UpdateTransactionRecordRequest
    ): TransactionRecordDTO {
        return presentationService.updateTransaction(id, request)
    }

    @DeleteMapping("/v1/transactions/{id}")
    fun deleteTransaction(
        @PathVariable id: Long,
        @RequestBody request: DeleteTransactionRecordRequest
    ): TransactionRecordDTO {
        return presentationService.deleteTransaction(id, request)
    }
}