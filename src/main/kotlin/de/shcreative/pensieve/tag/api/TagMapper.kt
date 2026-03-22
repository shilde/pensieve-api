package de.shcreative.pensieve.tag.api

import de.shcreative.pensieve.tag.api.dto.TagResponse
import de.shcreative.pensieve.tag.domain.Tag

fun Tag.toResponse() = TagResponse(
    id = id,
    name = name
)
