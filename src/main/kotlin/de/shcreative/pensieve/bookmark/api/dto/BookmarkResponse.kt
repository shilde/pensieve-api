package de.shcreative.pensieve.bookmark.api.dto

import java.time.Instant
import java.util.UUID

data class BookmarkResponse(
    val id: UUID,
    val url: String,
    val title: String,
    val description: String?,
    val tags: Set<String>,
    val collectionId: UUID?,
    val createdAt: Instant
)
