package com.kotlincon.webappdemo.rsocket

import com.kotlincon.webappdemo.Contact
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class ContactFeedController {

    //val channel = Channel<Contact>(Channel.CONFLATED)
    val channel = BroadcastChannel<Contact>(Channel.BUFFERED)
    //val channel = ConflatedBroadcastChannel<Contact>()

    suspend fun publish(contact: Contact) = channel.send(contact)

    @FlowPreview
    @MessageMapping("contact-feed")
    suspend fun getContacts(): Flow<Contact> = flow {
        println("Starting consuming contacts on RSocket...")
        channel.consumeEach {
            emit(it)
        }
    }


}