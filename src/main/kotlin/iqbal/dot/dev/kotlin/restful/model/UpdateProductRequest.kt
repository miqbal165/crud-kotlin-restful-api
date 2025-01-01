package iqbal.dot.dev.kotlin.restful.model

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateProductRequest(
    @field:NotBlank(message = "Name must have a value")
    val name: String?,

    @field:NotNull(message = "Price must have a value")
    @field:Min(value = 1, message = "Price must greater than or equal 1")
    val price: Long?,

    @field:NotNull(message = "Quantity must have a value")
    @field:Min(value = 0, message = "Quantity must greater than or equal 0")
    val quantity: Int?
)
