package de.shcreative.pensieve.search.api.dto

import de.shcreative.pensieve.bookmark.api.dto.BookmarkResponse

data class SearchResponse(
    val query: String,
    val total: Int,
    val results: List<BookmarkResponse>
)
