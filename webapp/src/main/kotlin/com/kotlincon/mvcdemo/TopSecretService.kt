package com.kotlincon.mvcdemo

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TopSecretService {

    @Value("\${secret.delay:500}")
    private var delayMillis: Long = 500

    fun doSecretThings(contact: Contact): Contact {
        println("Sthhh...")
        Thread.sleep(delayMillis)
        return contact.copy(name = "${contact.surname}...")
    }

}