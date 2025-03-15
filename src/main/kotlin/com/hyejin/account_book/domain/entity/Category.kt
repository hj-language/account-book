import jakarta.persistence.*

@Entity
@Table(name = "category")
class Category(
        val name: String,
        val type: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val transactions: List<Transaction> = emptyList()
}