package dev.n0roo.toy.components.common.exceptions

import com.fasterxml.jackson.annotation.JsonInclude

data class ConditionalMessage(
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val code: Int? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val message: String? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val result: Any? = null
)
