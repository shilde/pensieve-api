package de.shcreative.pensieve.collection.persistence

import de.shcreative.pensieve.collection.domain.Collection

fun CollectionEntity.toDomain(bookmarkCount: Int = bookmarks.size) = Collection(
    id = id,
    name = name,
    description = description,
    bookmarkCount = bookmarkCount,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Collection.toEntity() = CollectionEntity(
    id = id,
    name = name,
    description = description,
    createdAt = createdAt,
    updatedAt = updatedAt
)