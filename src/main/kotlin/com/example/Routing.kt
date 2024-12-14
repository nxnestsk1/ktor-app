import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Route.booksRouting(booksRepository: BooksRepository) {

    get("/books") {
        val books = booksRepository.getAllBooks()
        call.respond(books)
    }

    // Endpoint para adicionar um novo livro
    post("/books") {
        val book = call.receive<Books>()
        booksRepository.addBook(book)
        call.respond(HttpStatusCode.Created, "O livro '${book.title}' foi adicionado com sucesso!")
    }
}
