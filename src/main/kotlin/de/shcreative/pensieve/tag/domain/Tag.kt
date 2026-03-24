package de.shcreative.pensieve.tag.domain

import java.time.Instant
import java.util.UUID

data class Tag(
    val id: UUID,
    val name: String,
    val bookmarkCount: Int,
    val createdAt: Instant
)
