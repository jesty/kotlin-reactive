package com.kotlincommunityconf.rsocketclient

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.connectTcpAndAwait
import org.springframework.messaging.rsocket.retrieveFlow
import org.springframework.stereotype.Component

@Component
class Client(private val builder: RSocketRequester.Builder) {

    @EventListener(ApplicationReadyEvent::class)
    fun init() = runBlocking {
        val requester = builder
                .connectTcpAndAwait("localhost", 9898)
        launch {
            requester
                    .route("feed")
                    .retrieveFlow<Int>()
                    .onEach {
                        println("Receiving $it")
                    }
                    .collect()
        }

        launch {
            requester
                    .route("contact-feed")
                    .retrieveFlow<Contact>()
                    .onEach {
                        println("Receiving contact $it")
                    }
                    .collect()
        }
    }


}