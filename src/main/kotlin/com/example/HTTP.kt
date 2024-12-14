import io.ktor.application.*
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.routing.*
import io.ktor.server.response.respond
import io.ktor.server.request.receive
import io.ktor.features.ContentNegotiation
import io.ktor.serialization.kotlinx.json.*

fun Application.module() {
    // Instala a configuração de Content Negotiation
    install(ContentNegotiation) {
        json() // Configura o Ktor para usar JSON como formato de resposta
    }

    // Configuração de Status Pages para tratamento de exceções
    install(StatusPages) {
        exception<Throwable> { cause ->
            // Retorna erro 500 para exceções não tratadas
            call.respond(HttpStatusCode.InternalServerError, "Internal Server Error: ${cause.localizedMessage}")
        }
        exception<NoSuchElementException> { cause ->
            // Retorna erro 404 para exceções do tipo NoSuchElementException
            call.respond(HttpStatusCode.NotFound, "Resource Not Found: ${cause.localizedMessage}")
        }
    }

    // Configuração de Roteamento
    routing {
        get("/") {
            call.respondText("Welcome to the Bookstore API!")
        }

        get("/error") {
            // Lançando uma exceção de exemplo para testar o tratamento de erro
            throw IllegalArgumentException("An error occurred in /error endpoint!")
        }

        // Rota para simular um livro
        get("/books") {
            call.respondText("List of books will go here.")
        }

        // Endpoint para adicionar um livro
        post("/books") {
            // Recebe um livro via POST e simula a criação
            val book = call.receive<Book>()
            call.respond(HttpStatusCode.Created, "Book '${book.title}' added successfully!")
        }
    }
}

// Definindo uma classe de modelo `Book`
data class Book(val id: Int, val title: String, val author: String, val genre: String)
