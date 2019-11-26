package com.github.jesty.reactivekotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.PostConstruct

@RestController
@RequestMapping("/feed")
class FeedController {

    //val channel = Channel<Int>(Channel.CONFLATED)
    //val channel = BroadcastChannel<Int>()
    val channel = ConflatedBroadcastChannel<Int>()

    @InternalCoroutinesApi
    @PostConstruct
    fun init() = GlobalScope.launch {
        println("Staring feed on web...")
        (1..Int.MAX_VALUE).forEach {
            delay(1000)
            channel.send(it)
        }
        channel.close()
    }

    @GetMapping
    suspend fun getRunningFlow(): Flow<Int> = flow {
        println("Starting consuming on web...")
        channel.consumeEach {
            emit(it)
        }
    }


}