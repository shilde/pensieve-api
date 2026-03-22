package de.shcreative.pensieve.mind.dto

import java.util.UUID

data class EmbedRequest(
    val bookmarkId: UUID,
    val url: String
)
