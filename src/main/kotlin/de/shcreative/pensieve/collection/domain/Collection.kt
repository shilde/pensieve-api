package de.shcreative.pensieve.collection.domain

import java.time.Instant
import java.util.UUID

data class Collection (
    val id: UUID,
    val name: String,
    val description: String?,
    val bookmarkCount: Int,
    val createdAt: Instant,
    val updatedAt: Instant
)