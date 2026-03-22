package de.shcreative.pensieve.collection.api.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CollectionRequest (
    @field:NotBlank(message = "Name must not be blank")
    @field:Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    val name: String,

    @field:Size(max = 500, message = "Description must not exceed 500 characters")
    val description: String? = null
)