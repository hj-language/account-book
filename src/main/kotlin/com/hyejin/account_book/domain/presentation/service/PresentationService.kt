package com.hyejin.account_book.domain.presentation.service

import com.hyejin.account_book.domain.presentation.dto.CategoryDTO
import com.hyejin.account_book.domain.presentation.repository.PresentationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PresentationService(
    private val presentationRepository: PresentationRepository,
) {
    @Transactional(readOnly = true)
    fun getCategoriesByType(type: String): List<CategoryDTO> {
        val categories = presentationRepository.getCategoriesByType(type)

        return categories.map {
            CategoryDTO(
                id = it.id,
                name = it.name,
                type = it.type
            )
        }
    }
}