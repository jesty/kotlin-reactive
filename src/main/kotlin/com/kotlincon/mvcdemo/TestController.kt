package com.kotlincon.mvcdemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class TestController {

    @GetMapping("/delay")
    fun delayed(delay: Long?): String {
        val timeMillis = delay ?: 1000
        Thread.sleep(timeMillis)
        return "Delay of $timeMillis"
    }

}