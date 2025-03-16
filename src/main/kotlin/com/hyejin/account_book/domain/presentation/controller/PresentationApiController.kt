package com.hyejin.account_book.domain.presentation.controller

import com.hyejin.account_book.domain.presentation.dto.CategoryDTO
import com.hyejin.account_book.domain.presentation.service.PresentationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PresentationApiController(
    private val presentationService: PresentationService,
) {
    @GetMapping("/v1/categories")
    fun getCategoriesByType(@RequestParam categoryType: String): List<CategoryDTO> {
        return presentationService.getCategoriesByType(categoryType)
    }
}