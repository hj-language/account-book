import jakarta.persistence.*

@Entity
@Table(name = "category")
class Category(
        name: String,
        type: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val name: String = ""

    val type: String = ""

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val transactions: List<Transaction> = emptyList()
}