package dev.n0roo.toy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan

@EnableFeignClients
@SpringBootApplication(scanBasePackages = ["dev.n0roo.toy.*"])
@ComponentScan(basePackages = ["dev.n0roo.toy.*"])
class SampleApiApplication

fun main(args: Array<String>) {
    runApplication<SampleApiApplication>(*args)
}