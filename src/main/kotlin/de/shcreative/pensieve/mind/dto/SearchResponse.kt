package de.shcreative.pensieve.mind.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class SearchResultDto(
    @JsonProperty("bookmark_id") val bookmarkId: UUID,
    val score: Double
)

data class SearchResponse(
    val results: List<SearchResultDto>,
    val query: String
)