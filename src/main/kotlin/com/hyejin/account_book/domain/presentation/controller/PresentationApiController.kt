package com.hyejin.account_book.domain.presentation.controller

import com.hyejin.account_book.domain.presentation.dto.CategoryDTO
import com.hyejin.account_book.domain.presentation.dto.CreateTransactionRecordRequest
import com.hyejin.account_book.domain.presentation.dto.FindTransactionRecordsRequest
import com.hyejin.account_book.domain.presentation.dto.TransactionRecordDTO
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
}