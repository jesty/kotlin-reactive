package com.kotlincon.mvcdemo

import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TopSecretService {

    @Value("\${secret.delay:500}")
    private var delayMillis: Long = 500

    suspend fun doSecretThings(contact: Contact): Contact {
        println("Sthhh...")
        delay(delayMillis)
        return contact.copy(name = "${contact.surname}...")
    }

}