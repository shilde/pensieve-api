package de.shcreative.pensieve.mind.dto

import java.util.UUID

data class SearchResultDto(
    val bookmarkId: UUID,
    val score: Double
)

data class SearchResponse(
    val results: List<SearchResultDto>,
    val query: String
)