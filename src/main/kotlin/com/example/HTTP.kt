package com.example

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.features.ContentNegotiation

fun Application.configureHTTP() {
    // Instala a configuração de Content Negotiation
    install(ContentNegotiation) {
        json() // Configura o Ktor para usar JSON como formato de resposta
    }

    routing {
        // Rota para obter uma lista de itens
        get("/items") {
            val items = listOf("Item 1", "Item 2", "Item 3") // Exemplo de lista de itens
            call.respond(HttpStatusCode.OK, items)
        }

        // Rota para obter um item específico
        get("/items/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing ID")
            call.respond(HttpStatusCode.OK, "Item $id")
        }

        // Rota para adicionar um novo item
        post("/items") {
            val item = call.receive<String>() // Recebe um item como String
            call.respond(HttpStatusCode.Created, "Item '$item' added successfully!")
        }

        // Rota para deletar um item
        delete("/items/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing ID")
            call.respond(HttpStatusCode.OK, "Item $id deleted successfully!")
        }
    }
}