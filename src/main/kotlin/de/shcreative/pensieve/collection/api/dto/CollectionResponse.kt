package de.shcreative.pensieve.collection.api.dto

import java.time.Instant
import java.util.UUID

data class CollectionResponse(
    val id: UUID,
    val name: String,
    val description: String?,
    val bookmarkCount: Int,
    val createdAt: Instant
)
