package dev.n0roo.toy.authenticate.internal.messages

import dev.n0roo.toy.authenticate.internal.messages.dto.SendVerifiedMessageDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "msg-broker", dismiss404 = true)
interface ExternalMessageBrokerService {
    @GetMapping("/msg/health-checker")
    suspend fun helloWorld(): String

    @Async
    @PostMapping("/msg/mails/verified", consumes = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun sendMails(
        @RequestBody params: SendVerifiedMessageDto
    )

    @PostMapping("/msg/sms/verified", consumes = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun sendSms(
        @RequestBody params: SendVerifiedMessageDto
    )
}
