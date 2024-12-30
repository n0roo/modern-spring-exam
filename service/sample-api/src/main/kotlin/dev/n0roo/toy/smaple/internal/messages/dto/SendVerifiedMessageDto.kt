package dev.n0roo.toy.smaple.internal.messages.dto

data class SendVerifiedMessageDto(
    val receiver: String,
    val pinCode: String,
)
