package com.kotlincommunityconf.rsocketclient

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.http.MediaType.APPLICATION_CBOR
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.connectTcpAndAwait
import org.springframework.messaging.rsocket.retrieveFlow
import org.springframework.stereotype.Component

@Component
class Client(private val builder: RSocketRequester.Builder) {

    @EventListener(ApplicationReadyEvent::class)
    fun init() = runBlocking {
        val requester = builder.dataMimeType(APPLICATION_CBOR).connectTcpAndAwait("localhost", 9898)
        requester
                .route("feed")
                .retrieveFlow<Int>()
                .onEach {
                    println("Receiving $it")
                }
                .collect()
    }


}