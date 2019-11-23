package com.kotlincon.mvcdemo

import org.springframework.beans.factory.annotation.Value


class TopSecretService {

    @Value("secret.delay")
    private var delay: Long = 500

    fun doSecretThings(contact: Contact) {
        println("Sthhh...")
        Thread.sleep(delay)
    }

}