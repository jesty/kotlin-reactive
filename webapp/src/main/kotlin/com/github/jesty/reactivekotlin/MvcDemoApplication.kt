package com.github.jesty.reactivekotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.annotation.PostConstruct

@SpringBootApplication
class MvcDemoApplication

fun main(args: Array<String>) {
    runApplication<MvcDemoApplication>(*args)
}

