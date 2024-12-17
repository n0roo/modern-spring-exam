package dev.n0roo.toy.service.authenticate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["dev.n0roo.toy"])
@SpringBootApplication
class AuthenticateApiApplication

fun main(args: Array<String>) {
    runApplication<AuthenticateApiApplication>(*args)
}