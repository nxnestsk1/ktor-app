import io.ktor.application.*
import io.ktor.http.HttpStatusCode
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlin.test.assertEquals

// Definindo a classe de modelo `Book`
@Serializable
data class Book(val id: Int, val title: String, val author: String, val genre: String)

fun Application.module() {
    // Configura o servidor Ktor
    install(ContentNegotiation) {
        json() // Configura o Ktor para usar JSON como formato de resposta
    }

    routing {
        get("/") {
            call.respondText("Welcome to the Bookstore API!")
        }

        // Endpoint para listar livros
        get("/books") {
            val books = listOf(
                Book(1, "Kotlin in Action", "Dmitry Jemerov", "Programming"),
                Book(2, "Clean Code", "Robert C. Martin", "Programming")
            )
            call.respond(books)
        }

        // Endpoint para adicionar um livro
        post("/books") {
            val book = call.receive<Book>() // Recebe o livro em formato JSON
            call.respond(HttpStatusCode.Created, "Book '${book.title}' added successfully!")
        }
    }
}

// Testes de API utilizando o Ktor Test Server
class ApplicationTest {

    @Test
    fun testWelcomeEndpoint() = testApplication {
        // Testa o endpoint raiz
        client.get("/") {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Welcome to the Bookstore API!", response.bodyAsText())
        }
    }

    @Test
    fun testGetBooks() = testApplication {
        // Testa o endpoint /books
        client.get("/books") {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(
                """
                [{"id":1,"title":"Kotlin in Action","author":"Dmitry Jemerov","genre":"Programming"},{"id":2,"title":"Clean Code","author":"Robert C. Martin","genre":"Programming"}]
                """.trimIndent(),
                response.bodyAsText()
            )
        }
    }

    @Test
    fun testPostBook() = testApplication {
        // Testa o endpoint para adicionar um livro
        val newBook = Book(3, "Effective Kotlin", "Marcin Moskala", "Programming")
        val response = client.post("/books") {
            setBody("""{"id":3,"title":"Effective Kotlin","author":"Marcin Moskala","genre":"Programming"}""")
            contentType(io.ktor.http.ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.Created, response.status())
        assertEquals("Book 'Effective Kotlin' added successfully!", response.bodyAsText())
    }
}
