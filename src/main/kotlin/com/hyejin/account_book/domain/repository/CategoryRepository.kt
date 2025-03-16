package com.hyejin.account_book.domain.repository

import com.hyejin.account_book.domain.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByName(name: String): Optional<Category>
    fun findByType(type: String): List<Category>
}