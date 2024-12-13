package dev.n0roo.toy.service.authenticate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthenticateApiApplication

fun main(args: Array<String>) {
    runApplication<AuthenticateApiApplication>(*args)
}