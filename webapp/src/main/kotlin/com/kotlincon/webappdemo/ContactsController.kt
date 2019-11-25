package com.kotlincon.webappdemo

import com.kotlincon.webappdemo.rsocket.ContactFeedController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.transaction.reactive.TransactionalOperator
import org.springframework.transaction.reactive.executeAndAwait
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contacts")
class ContactsController(private val contactRepository: ContactRepositoryFlow,
                         private val contactFeedController: ContactFeedController,
                         private val db: DatabaseClient,
                         private val operator: TransactionalOperator) {

    @EventListener(ApplicationReadyEvent::class)
    fun init() = runBlocking {
        println("Init database...")
        db.execute("drop table if exists contact").await()
        db.execute("CREATE TABLE contact (\n" //
                + "    id          SERIAL PRIMARY KEY,\n" //
                + "    name        varchar(255) NULL,\n" //
                + "    surname     varchar(255)  NULL\n" //
                + ");")
                .await()
        contactRepository.save(Contact(name = "Davide", surname = "Cerbo"))
        contactRepository.save(Contact(name = "Valentina", surname = "Perazzo"))
        contactRepository.save(Contact(name = "Tizio", surname = "Caio"))
        println("Database initialized!")
    }

    @GetMapping
    suspend fun findAll(): Flow<Contact> = contactRepository.findAll()

    @GetMapping("/{id}")
    suspend fun findById(id: Long): Contact? = contactRepository.findById(id)

    @PostMapping
    suspend fun createContact(@RequestBody contact: Contact): Contact {
        val saved = contactRepository.save(contact)
        contactFeedController.publish(saved)
        return saved
    }


    @PostMapping("/batch")
    suspend fun createContacts(@RequestBody contact: Flow<Contact>): Flow<Contact>? = operator.executeAndAwait {
        flow {
            contact.collect {
                if (it.name.equals("davide", true)) throw RuntimeException("Update to PREMIUM ACCOUNT to save a contact with name \"Davide\"!")
                emit(contactRepository.save(it))
            }
        }
    }


}