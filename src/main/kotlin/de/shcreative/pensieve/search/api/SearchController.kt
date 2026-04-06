package de.shcreative.pensieve.search.api

import de.shcreative.pensieve.bookmark.api.dto.BookmarkResponse
import de.shcreative.pensieve.bookmark.toResponse
import de.shcreative.pensieve.search.domain.SearchService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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

    @GetMapping(version = "1.0")
    suspend fun search(
        @RequestParam q: String,
        @RequestParam(required = false) collectionId: UUID?,
        @PageableDefault(size = 20) pageable: Pageable
    ): Page<BookmarkResponse> =
        searchService.search(q, collectionId, pageable).map { it.toResponse() }
}
