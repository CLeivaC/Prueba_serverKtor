package com.ejemplo.plugins

import com.ejemplo.routes.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.module() {
    install(ContentNegotiation) {
        // Configura la serialización JSON aquí
        json()
    }

    configureRouting()
}

fun Application.configureRouting() {

    routing {
        customerRoutes()
        listOrdersRoute()
        getOrderRoute()
        totalizeOrderRoute()
    }
}
