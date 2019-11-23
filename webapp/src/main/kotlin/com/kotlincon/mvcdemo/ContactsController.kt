package com.kotlincon.mvcdemo

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/contacts")
class ContactsController(val contactRepository: ContactRepository, db: DatabaseClient) {

    init {
        println("Init database...")
        db.execute("drop table if exists contact").then()
                .then(db.execute("CREATE TABLE contact (\n" //
                        + "    id          SERIAL PRIMARY KEY,\n" //
                        + "    name        varchar(255) NULL,\n" //
                        + "    surname     varchar(255)  NULL\n" //
                        + ");")
                        .then())
                .then(contactRepository.save(Contact(name = "Davide", surname = "Cerbo")).then())
                .then(contactRepository.save(Contact(name = "Valentina", surname = "Perazzo")).then())
                .then(contactRepository.save(Contact(name = "Tizio", surname = "Caio")).then())
                .doOnSuccess { println("Database initialized!") }
                .subscribe()
    }

    @GetMapping
    fun findAll(): Flux<Contact> = contactRepository.findAll()

    @GetMapping("/{id}")
    fun findById(id: Long): Mono<Contact> = contactRepository.findById(id)

    @PostMapping
    fun createContact(@RequestBody contact: Contact): Mono<Contact> = contactRepository.save(contact)

    @PostMapping("/batch")
    @Transactional
    fun createContact(@RequestBody contact: Flux<Contact>): Flux<Contact> {
        return contact
                .flatMap {
                    if (it.name.equals("davide", true)) throw RuntimeException("Update to PREMIUM ACCOUNT to save a contact with name \"Davide\"!")
                    contactRepository.save(it)
                }
    }


}