package com.github.jesty.reactivekotlin

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
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

}