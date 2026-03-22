package de.shcreative.pensieve.mind.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class EmbedResponse (
    @JsonProperty("bookmark_id") val bookmarkId: UUID,
    @JsonProperty("embedding_id") val embeddingId: String,
    val title: String?,
    val description: String?,
    @JsonProperty("content_snippet") val contentSnippet: String?
)