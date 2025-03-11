import com.hyejin.account_book.domain.entity.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "transaction")
class Transaction(
        amount: Int,
        memo: String,
        transactionDate: LocalDate,
        user: User,
        category: Category
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val amount: Int = 0

    val memo: String = ""

    val transactionDate: LocalDate? = null

    var deletedDateTime: LocalDateTime? = null

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User? = null

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category? = null
}