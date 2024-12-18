package dev.n0roo.toy.authenticate.rest.commons

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class CommonController {

    @GetMapping("/health-check")
    fun healthCheck(): Mono<String> {
        return Mono.just("OK")
    }
}