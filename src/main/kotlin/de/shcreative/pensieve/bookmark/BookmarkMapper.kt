package de.shcreative.pensieve.bookmark

import de.shcreative.pensieve.bookmark.api.dto.BookmarkRequest
import de.shcreative.pensieve.bookmark.api.dto.BookmarkResponse
import de.shcreative.pensieve.bookmark.domain.Bookmark
import java.time.Instant
import java.util.UUID

fun BookmarkRequest.toDomain() = Bookmark(
    id = UUID.randomUUID(),
    url = url,
    title = title ?: "",
    description = description,
    content = null,
    embeddingId = null,
    tags = emptySet(),
    collection = null,
    createdAt = Instant.now(),
    updatedAt = Instant.now()
)

fun Bookmark.toResponse() = BookmarkResponse(
    id = id,
    url = url,
    title = title,
    description = description,
    tags = tags.map { it.name }.toSet(),
    collectionId = collection?.id,
    createdAt = createdAt
)
