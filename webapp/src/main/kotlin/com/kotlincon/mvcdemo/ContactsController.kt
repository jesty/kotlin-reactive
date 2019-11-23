package com.kotlincon.mvcdemo

import org.springframework.web.bind.annotation.*
import java.util.*
import javax.transaction.Transactional

@RestController
@RequestMapping("/contacts")
class ContactsController(private val contactRepository: ContactRepository,
                         private val topSecretService: TopSecretService) {

    init {
        contactRepository.save(Contact(name = "Davide", surname = "Cerbo"))
        contactRepository.save(Contact(name = "Valentina", surname = "Perazzo"))
        contactRepository.save(Contact(name = "Tizio", surname = "Caio"))
    }

    @GetMapping
    fun findAll(): MutableIterable<Contact> = contactRepository.findAll()

    @GetMapping("/{id}")
    fun findById(id: Int): Optional<Contact> = contactRepository.findById(id)

    @PostMapping
    fun createContact(@RequestBody contact: Contact): Contact {
        topSecretService.doSecretThings(contact)
        return contactRepository.save(contact)
    }


    @PostMapping("/batch")
    @Transactional
    fun createContact(@RequestBody contact: Array<Contact>) = contact.map {
        if (it.name.equals("davide", true)) throw RuntimeException("Upgrade now to a PREMIUM ACCOUNT to create more than 2 contact in batch mode!")
        contactRepository.save(it)
    }

}