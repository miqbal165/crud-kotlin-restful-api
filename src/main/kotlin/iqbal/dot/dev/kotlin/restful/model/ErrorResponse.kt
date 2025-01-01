package iqbal.dot.dev.kotlin.restful.model

data class ErrorResponse(
    val code: Int,
    val status: String,
    val message: Any
)
