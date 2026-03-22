package de.shcreative.pensieve.collection.api

import de.shcreative.pensieve.collection.api.dto.CollectionRequest
import de.shcreative.pensieve.collection.api.dto.CollectionResponse
import de.shcreative.pensieve.collection.domain.Collection
import java.time.Instant
import java.util.UUID

fun Collection.toResponse() = CollectionResponse(
    id = id,
    name = name,
    description = description,
    bookmarkCount = bookmarkCount,
    createdAt = createdAt
)

fun CollectionRequest.toDomain() = Collection(
    id = UUID.randomUUID(),
    name = name,
    description = description,
    bookmarkCount = 0,
    createdAt = Instant.now(),
    updatedAt = Instant.now()
)
