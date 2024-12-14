import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.request.*

// Dados temporários (em memória)
val books = mutableListOf(
    Books(1, "Kotlin in Action", "Dmitry Jemerov", "Programming"),
    Books(2, "Clean Code", "Robert C. Martin", "Programming")
)

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json() // Configura o Ktor para usar JSON como formato de resposta
        }

        routing {
            get("/") {
                call.respondText("Welcome to the Bookstore API!")
            }

            // Endpoint para listar livros
            get("/books") {
                call.respond(books) // Retorna a lista de livros
            }

            // Endpoint para adicionar um livro
            post("/books") {
                val book = call.receive<Books>() // Recebe o JSON e desserializa para um objeto Book
                books.add(book) // Adiciona o livro à lista em memória
                call.respondText("Book '${book.title}' added successfully!")
            }
        }
    }.start(wait = true)
}
