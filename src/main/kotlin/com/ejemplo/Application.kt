package com.ejemplo

import com.ejemplo.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    //conexión a la base de datos MySQL
    Database.connect("jdbc:mysql://localhost:3306/prueba_ktor", driver = "com.mysql.cj.jdbc.Driver", user = "root", password = "admin")

    configureSerialization()
    configureRouting()
}
