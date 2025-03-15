package com.hyejin.account_book.domain.repository

import com.hyejin.account_book.domain.entity.Category
import com.hyejin.account_book.domain.entity.TransactionRecord
import com.hyejin.account_book.domain.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import java.time.LocalDate

//@DataJpaTest
@SpringBootTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionRecordRepositoryTest(
        @Autowired val transactionRecordRepository: TransactionRecordRepository,
        @Autowired val userRepository: UserRepository,
        @Autowired val categoryRepository: CategoryRepository
) {
    @Autowired
    lateinit var context: ApplicationContext

    val DATA_SIZE = 10


    private fun createTransactionRecord(n: Int, userId: Long, category: Category): TransactionRecord {
        return TransactionRecord(
                amount = n * 1000,
                memo = "테스트 메모 ${n}",
                transactionDate = if (n % 2 == 0) LocalDate.now() else LocalDate.now().minusDays(1),
                userId = userId,
                category = category
        )
    }

    @BeforeAll
    fun beforeAll() {
        println(context)

        val user = User("testUser", "testId", "password")
        userRepository.save(user)

        val cafeCategory = Category("카페", "출금")
        val salaryCategory = Category("월급", "입금")
        categoryRepository.saveAll(listOf(cafeCategory, salaryCategory))

        println("----- 데이터 초기화 이전 조회 시작 -----")
        val beforeInitialize = transactionRecordRepository.findAll()
        assertThat(beforeInitialize).hasSize(0)
        println("----- 데이터 초기화 이전 조회 종료 -----")

        println("----- 테스트 데이터 초기화 시작 -----")
        val transactionRecords = mutableListOf<TransactionRecord>()
        for (i in 1..DATA_SIZE) {
            val transactionRecord = createTransactionRecord(i, user.id!!, if (i % 2 == 0) cafeCategory else salaryCategory)
            transactionRecords.add(transactionRecord)
        }
        transactionRecordRepository.saveAll(transactionRecords)
        println("----- 테스트 데이터 초기화 종료 -----")
    }

    @Test
    @DisplayName("findAll 테스트")
    fun testFindAll() {
        val transactionRecords = transactionRecordRepository.findAll()
        assertThat(transactionRecords).hasSize(DATA_SIZE)
    }

    @Test
    @DisplayName("findByTransactionDateBetweenAndCategoryIdAndUserId 테스트")
    fun findByTransactionDateBetweenAndCategoryIdAndUserId() {
        val user = userRepository.findByLoginId("testId")
        val cafeCategory = categoryRepository.findByName("카페")

        val transactionRecords = transactionRecordRepository.findByTransactionDateBetweenAndCategoryIdAndUserId(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1),
                cafeCategory.get().id!!,
                user.get().id!!,
        )

        assertThat(transactionRecords).hasSize(DATA_SIZE / 2)

        for (transactionRecord in transactionRecords) {
            assertThat(transactionRecord.category.name).isEqualTo("카페")
        }
    }

    @Test
    @DisplayName("findByTransactionDateBetweenAndUserId 테스트")
    fun findByTransactionDateBetweenAndUserId() {
        val user = userRepository.findByLoginId("testId")

        val transactionRecords = transactionRecordRepository.findByTransactionDateBetweenAndUserId(
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(1),
                user.get().id!!,
        )

        assertThat(transactionRecords).hasSize(DATA_SIZE / 2)
    }
}