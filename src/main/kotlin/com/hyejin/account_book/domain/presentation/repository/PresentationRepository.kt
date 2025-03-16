package com.hyejin.account_book.domain.presentation.repository

import com.hyejin.account_book.domain.entity.Category
import com.hyejin.account_book.domain.repository.CategoryRepository
import org.springframework.stereotype.Repository

@Repository
class PresentationRepository(
    private val categoryRepository: CategoryRepository,
) {
    fun getCategoriesByType(type: String): List<Category> {
        return categoryRepository.findByType(type)
    }
}