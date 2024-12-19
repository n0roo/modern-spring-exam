package dev.n0roo.toy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class SampleApiApplication

fun main(args: Array<String>) {
    runApplication<SampleApiApplication>(*args)
}