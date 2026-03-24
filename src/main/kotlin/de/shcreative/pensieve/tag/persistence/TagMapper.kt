package de.shcreative.pensieve.tag.persistence

import de.shcreative.pensieve.tag.domain.Tag

fun TagEntity.toDomain(bookmarkCount: Int = 0) = Tag(
    id = id,
    name = name,
    bookmarkCount = bookmarkCount,
    createdAt = createdAt,
)