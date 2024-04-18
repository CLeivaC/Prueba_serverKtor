package com.ejemplo.data



import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import com.ejemplo.models.Customer
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table

object CustomerDao {
    fun getAllUsers(): List<Customer> {
        return transaction {
            CustomerTable.selectAll().map { row ->
                Customer(
                    id = row[CustomerTable.id],
                    first_name = row[CustomerTable.firstName],
                    last_name = row[CustomerTable.lastName],
                    email = row[CustomerTable.email]
                )
            }
        }
    }

    fun addUser(customer: Customer) {
        transaction {
            CustomerTable.insert {
                it[id] = customer.id
                it[firstName] = customer.first_name
                it[lastName] = customer.last_name
                it[email] = customer.email
            }
        }
    }

    fun deleteUser(id: String) {
        transaction {
            CustomerTable.deleteWhere { CustomerTable.id eq id }
        }
    }
}


object CustomerTable : Table("customers") {
    val id = varchar("id", length = 50)
    val firstName = varchar("first_name", length = 50)
    val lastName = varchar("last_name", length = 50)
    val email = varchar("email", length = 100)

    override val primaryKey = PrimaryKey(id)
}
