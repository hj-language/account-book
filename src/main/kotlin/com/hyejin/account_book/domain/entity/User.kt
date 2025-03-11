import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
        name: String,
        loginId: String,
        password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val name: String = ""

    val loginId: String = ""

    val password: String = ""

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val transactions: List<Transaction> = emptyList()
}