package de.shcreative.pensieve.search.api

import de.shcreative.pensieve.bookmark.api.dto.BookmarkResponse
import de.shcreative.pensieve.bookmark.toResponse
import de.shcreative.pensieve.search.api.dto.SearchResponse
import de.shcreative.pensieve.search.domain.SearchService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/search")
class SearchController(
    private val searchService: SearchService
) {

    @GetMapping
    suspend fun search(
        @RequestParam q: String,
        @RequestParam(required = false) collectionId: UUID?,
        pageable: Pageable
    ): List<BookmarkResponse> =
        searchService.search(q, collectionId).map { it.toResponse() }
}
