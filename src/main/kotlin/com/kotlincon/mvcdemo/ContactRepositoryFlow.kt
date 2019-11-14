package com.kotlincon.mvcdemo

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.core.*
import org.springframework.stereotype.Component

@Component
class ContactRepositoryFlow(val databaseClient: DatabaseClient) {

    suspend fun save(contact: Contact): Contact {
        databaseClient
                .insert()
                .into(Contact::class.java)
                .using(contact)
                .fetch()
                .awaitOneOrNull()
        return contact
    }

    fun findAll(): Flow<Contact> = databaseClient
            .select()
            .from(Contact::class.java)
            .fetch()
            .flow()

    suspend fun findById(id: Long) = databaseClient
            .execute("SELECT * FROM contact WHERE id = :id")
            .bind("id", id)
            .asType<Contact>()
            .fetch()
            .awaitOneOrNull()


}