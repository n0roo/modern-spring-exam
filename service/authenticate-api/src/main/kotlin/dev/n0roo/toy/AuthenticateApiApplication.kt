package dev.n0roo.toy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackages = ["dev.n0roo.toy.*"])
@ComponentScan(basePackages = ["dev.n0roo.toy.*"])
class AuthenticateApiApplication

fun main(args: Array<String>) {
    runApplication<AuthenticateApiApplication>(*args)
}