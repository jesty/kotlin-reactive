package com.kotlincon.mvcdemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import java.time.Duration.ofMillis

@RestController
@RequestMapping
class TestController {

    @GetMapping("/tic")
    fun tic() : Flux<Int> = (1..Int.MAX_VALUE)
            .toFlux()
            .delayElements(ofMillis(1000))


    @GetMapping("/delay")
    fun delayed(delay: Long?): Mono<String> {
        val timeMillis = delay ?: 1000
        return Mono.just("Delay of $timeMillis")
                .delayElement(ofMillis(timeMillis))
    }

}