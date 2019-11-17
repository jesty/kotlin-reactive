package com.kotlincon.mvcdemo

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class FeedSocketController {

    //val channel = Channel<Int>(Channel.CONFLATED)
    //val channel = BroadcastChannel<Int>()
    val channel = ConflatedBroadcastChannel<Int>()

    @InternalCoroutinesApi
    @EventListener(ApplicationReadyEvent::class)
    fun init() = runBlocking {
        println("Staring feed...")
        (1..Int.MAX_VALUE).forEach {
            delay(1000)
            channel.send(it)
        }
        channel.close()
    }

    @FlowPreview
    @MessageMapping("feed")
    suspend fun getRunningFlow(): Flow<Int> = flow {
        channel.consumeEach {
            emit(it)
        }
    }


}