package com.kotlincon.webappdemo.rsocket

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import javax.annotation.PostConstruct

@Controller
class FeedSocketController {

    //val channel = Channel<Int>(Channel.CONFLATED)
    val channel = BroadcastChannel<Int>(Channel.BUFFERED)
    //val channel = ConflatedBroadcastChannel<Int>()

    @InternalCoroutinesApi
    @PostConstruct
    fun init() = GlobalScope.launch {
        println("Staring feed on RSocket...")
        (1..Int.MAX_VALUE).forEach {
            delay(1000)
            channel.send(it)
        }
        channel.close()
    }

    @FlowPreview
    @MessageMapping("feed")
    suspend fun getRunningFlow(): Flow<Int> = flow {
        println("Starting consuming on RSocket...")
        channel.consumeEach {
            emit(it)
        }
    }


}