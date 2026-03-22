package de.shcreative.pensieve.bookmark.domain

import de.shcreative.pensieve.tag.domain.Tag
import de.shcreative.pensieve.collection.domain.Collection
import java.util.UUID
import java.time.Instant

data class Bookmark (
    val id: UUID,
    val url: String,
    val title: String,
    val description: String?,
    val content: String?,
    val embeddingId: UUID?,
    val tags: Set<Tag>,
    val collection: Collection?,
    val createdAt: Instant,
    val updatedAt: Instant
)