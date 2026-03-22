package de.shcreative.pensieve.tag.persistence

import de.shcreative.pensieve.tag.domain.Tag

fun TagEntity.toDomain() = Tag(
    id = id,
    name = name
)

fun Tag.toEntity() = TagEntity(
    id = id,
    name = name
)
