package com.hyejin.account_book.domain.repository

import com.hyejin.account_book.domain.entity.Category
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryRepositoryTest(
    @Autowired val categoryRepository: CategoryRepository
) {

    @BeforeAll
    fun beforeAll() {
        val categories = listOf(
            Category("카페", "출금"),
            Category("월급", "입금"),
            Category("이자", "입금")
        )
        categoryRepository.saveAll(categories)
    }

    @Test
    @DisplayName("findByName 테스트")
    fun testFindByName() {
        val category = categoryRepository.findByName("카페")
        assertThat(category).get().extracting("name").isEqualTo("카페")
    }

    @Test
    @DisplayName("findByType 테스트")
    fun testFindByType() {
        val categories = categoryRepository.findByType("입금")
        assertThat(categories).hasSize(2)
    }
}