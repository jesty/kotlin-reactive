package com.github.jesty.reactivekotlin

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.Duration

@Component
class TopSecretService {

    @Value("\${secret.delay:500}")
    private var delay: Long = 500

    fun doSecretThings(contact: Contact): Mono<Contact> {
        return Mono
                .just(contact)
                .map { it.copy(name = "${it.name}...") }
                .doOnNext { println("Sthhh...") }
                .delayElement(Duration.ofMillis(delay))
    }

}