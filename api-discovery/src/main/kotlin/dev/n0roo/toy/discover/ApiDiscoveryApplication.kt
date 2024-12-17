package dev.n0roo.toy.discover

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class ApiDiscoveryApplication
fun main(args: Array<String>) {
    runApplication<ApiDiscoveryApplication>(*args)
}