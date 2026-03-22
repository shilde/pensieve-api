package de.shcreative.pensieve.bookmark.api.dto

import jakarta.validation.constraints.NotBlank
import java.util.UUID

data class BookmarkRequest(
    @field:NotBlank val url: String,
    val title: String?,
    val description: String?,
    val tags: Set<String> = emptySet(),
    val collectionId: UUID? = null
)