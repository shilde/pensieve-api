package de.shcreative.pensieve.search.domain

import de.shcreative.pensieve.bookmark.domain.Bookmark
import de.shcreative.pensieve.bookmark.domain.BookmarkRepository
import de.shcreative.pensieve.mind.MindClient
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SearchService(
    private val bookmarkRepository: BookmarkRepository,
    private val mindClient: MindClient
) {

    suspend fun search(query: String, collectionId: UUID? = null): List<Bookmark> {
        val searchResponse = mindClient.search(
            query = query,
            limit = 20,
            collectionId = collectionId
        )

        val bookmarkIds = searchResponse.results.map { it.bookmarkId }
        return bookmarkRepository.findAllById(bookmarkIds)
    }
}
