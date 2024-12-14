// BooksRepository.kt
class BooksRepository {

    private val books = mutableListOf(
        Books(1, "Kotlin in Action", "Dmitry Jemerov", "Programming"),
        Books(2, "Clean Code", "Robert C. Martin", "Programming")
    )

    fun getAllBooks(): List<Books> {
        return books
    }

    fun addBook(book: Books) {
        books.add(book)
    }

    fun getBookById(id: Int): Books? {
        return books.find { it.id == id }
    }
}
