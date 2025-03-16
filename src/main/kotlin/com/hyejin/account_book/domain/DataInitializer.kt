package com.hyejin.account_book.domain

import com.hyejin.account_book.domain.entity.Category
import com.hyejin.account_book.domain.entity.TransactionRecord
import com.hyejin.account_book.domain.entity.User
import com.hyejin.account_book.domain.repository.CategoryRepository
import com.hyejin.account_book.domain.repository.TransactionRecordRepository
import com.hyejin.account_book.domain.repository.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Profile(value = ["default"])
class DataInitializer(
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository,
    private val transactionRecordRepository: TransactionRecordRepository,
) {
    @PostConstruct
    fun initializeData() {
        println("스프링이 실행되었습니다. 테스트 데이터를 초기화합니다.")

        val categories = mutableListOf<Category>(
            Category("월급", "입금"),
            Category("예금이자", "입금"),
            Category("식비", "출금"),
            Category("통신비", "출금"),
            Category("교통비", "출금"),
        )
        this.categoryRepository.saveAll(categories)

        val users = mutableListOf<User>(
            User("테스트유저1", "abcd", "password"),
            User("테스트유저2", "efgh", "password"),
        )
        this.userRepository.saveAll(users)

        val transactions = mutableListOf<TransactionRecord>(
            TransactionRecord(1000000, "", LocalDate.now().minusDays(1), users[0].id!!, categories[0]),
            TransactionRecord(500, "예금통장", LocalDate.now(), users[0].id!!, categories[1]),
            TransactionRecord(100, "주거래통장", LocalDate.now(), users[0].id!!, categories[1]),
            TransactionRecord(30000, "쿠팡", LocalDate.now(), users[0].id!!, categories[2]),
            TransactionRecord(100000, "SKT", LocalDate.now(), users[0].id!!, categories[3]),
            TransactionRecord(100000, "후불교통대금", LocalDate.now().plusDays(1), users[0].id!!, categories[4]),
            TransactionRecord(15000, "택시", LocalDate.now(), users[0].id!!, categories[4]),

            TransactionRecord(500000, "", LocalDate.now().minusDays(1), users[1].id!!, categories[0]),
            TransactionRecord(1000, "파킹통장", LocalDate.now(), users[1].id!!, categories[1]),
            TransactionRecord(100, "주거래통장", LocalDate.now(), users[1].id!!, categories[1]),
            TransactionRecord(50000, "마켓컬리", LocalDate.now(), users[1].id!!, categories[2]),
            TransactionRecord(30000, "LG U+", LocalDate.now(), users[1].id!!, categories[3]),
            TransactionRecord(70000, "후불교통대금", LocalDate.now(), users[1].id!!, categories[4]),
        )
        this.transactionRecordRepository.saveAll(transactions)
    }
}