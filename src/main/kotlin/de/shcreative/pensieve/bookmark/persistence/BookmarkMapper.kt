package de.shcreative.pensieve.bookmark.persistence

import de.shcreative.pensieve.bookmark.domain.Bookmark
import de.shcreative.pensieve.collection.domain.Collection
import de.shcreative.pensieve.collection.persistence.CollectionEntity
import de.shcreative.pensieve.tag.persistence.toDomain

private fun CollectionEntity.toDomain() = Collection(
    id = id,
    name = name,
    description = description,
    bookmarkCount = 0,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun BookmarkEntity.toDomain() = Bookmark(
    id = id,
    url = url,
    title = title,
    description = description,
    content = content,
    embeddingId = embeddingId,
    tags = tags.map { it.toDomain() }.toSet(),
    collection = collection?.toDomain(),
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Bookmark.toEntity() = BookmarkEntity(
    id = id,
    url = url,
    title = title,
    description = description,
    content = content,
    embeddingId = embeddingId
)
