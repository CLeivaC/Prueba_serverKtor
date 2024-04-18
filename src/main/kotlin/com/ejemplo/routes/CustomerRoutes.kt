package com.ejemplo.routes

import com.ejemplo.data.CustomerDao
import com.ejemplo.models.Customer
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString

fun Route.customerRoutes() {

        route("/customers") {
            get {
                // Obtener todos los clientes desde la base de datos
                val customers = CustomerDao.getAllUsers()
                // Convertir los clientes a JSON
                val jsonCustomers = kotlinx.serialization.json.Json.encodeToString(customers)
                // Responder con JSON
                call.respond(customers)
            }

            post {
                val customer = call.receive<Customer>()
                // Agregar el nuevo cliente a la base de datos
                CustomerDao.addUser(customer)
                call.respondText("Customer added successfully", status = HttpStatusCode.Created)
            }

            delete("/{id}") {
                val id = call.parameters["id"] ?: return@delete call.respondText("Missing id", status = HttpStatusCode.BadRequest)
                // Eliminar el cliente de la base de datos
                CustomerDao.deleteUser(id)
                call.respondText("Customer deleted successfully")
            }
        }



}