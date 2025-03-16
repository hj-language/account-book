package com.hyejin.account_book.domain.presentation.service

import com.hyejin.account_book.domain.entity.Category
import com.hyejin.account_book.domain.presentation.repository.PresentationRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PresentationServiceTest {
    @InjectMocks
    lateinit var presentationService: PresentationService

    @Mock
    lateinit var presentationRepository: PresentationRepository

    @Test
    fun testGetCategoriesByType() {
        // given
        val type = "입금"
        val categories = listOf(
            Category("월급", type),
            Category("이자", type)
        )

        Mockito.`when`(presentationRepository.getCategoriesByType(type)).thenReturn(categories)

        // when
        val categoryDTOs = presentationService.getCategoriesByType(type)

        // then
        assertThat(categoryDTOs).hasSize(2)
        for (categoryDTO in categoryDTOs) {
            assertThat(categoryDTO.type).isEqualTo(type)
        }
    }
}