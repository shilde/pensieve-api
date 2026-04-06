package de.shcreative.pensieve.bookmark.persistence

import de.shcreative.pensieve.bookmark.domain.Bookmark
import de.shcreative.pensieve.tag.persistence.toDomain
import de.shcreative.pensieve.tag.persistence.toEntity

fun BookmarkEntity.toDomain() = Bookmark(
    id = id,
    url = url,
    title = title,
    description = description,
    content = content,
    embeddingId = embeddingId,
    tags = tags.map { it.toDomain() }.toSet(),
    collectionId = collection?.id,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Bookmark.toEntity() = BookmarkEntity(
    id = id,
    url = url,
    title = title,
    description = description,
    content = content,
    embeddingId = embeddingId,
    tags = tags.map { it.toEntity() }.toMutableSet()
)
