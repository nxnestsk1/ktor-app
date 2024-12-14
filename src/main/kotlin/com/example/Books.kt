// Book.kt
@kotlinx.serialization.Serializable
data class Books(
    val id: Int,
    val title: String,
    val author: String,
    val genre: String
)
