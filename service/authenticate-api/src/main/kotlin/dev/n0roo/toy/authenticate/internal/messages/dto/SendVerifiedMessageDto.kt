package dev.n0roo.toy.authenticate.internal.messages.dto

data class SendVerifiedMessageDto(
    val receiver: String,
    val pinCode: String,
)
