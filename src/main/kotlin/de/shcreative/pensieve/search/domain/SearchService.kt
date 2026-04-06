package de.shcreative.pensieve.search.domain

import de.shcreative.pensieve.bookmark.domain.Bookmark
import de.shcreative.pensieve.bookmark.domain.BookmarkRepository
import de.shcreative.pensieve.mind.MindClient
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SearchService(
    private val bookmarkRepository: BookmarkRepository,
    private val mindClient: MindClient
) {

    suspend fun search(query: String, collectionId: UUID? = null, pageable: Pageable): Page<Bookmark> {
        val maxResults = pageable.offset.toInt() + pageable.pageSize
        val searchResponse = mindClient.search(
            query = query,
            limit = maxResults,
            collectionId = collectionId
        )

        val allIds = searchResponse.results.map { it.bookmarkId }
        val pageIds = allIds.drop(pageable.offset.toInt()).take(pageable.pageSize)
        val bookmarks = bookmarkRepository.findAllById(pageIds)

        val bookmarkMap = bookmarks.associateBy { it.id }
        val ordered = pageIds.mapNotNull { bookmarkMap[it] }

        return PageImpl(ordered, pageable, allIds.size.toLong())
    }
}
