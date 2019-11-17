package com.kotlincon.mvcdemo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class TestController {

    @GetMapping("/tic")
    suspend fun tic(): Flow<Int> = flow {
        (1..Int.MAX_VALUE).forEach {
            emit(it)
            delay(1000)
        }
    }.onCompletion {
        println("Finish!!!")
    }

    @GetMapping("/delay")
    suspend fun delayed(delay: Long?): String {
        val timeMillis = delay ?: 1000
        delay(timeMillis)
        return "Delay of $timeMillis"
    }

}