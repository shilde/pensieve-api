package de.shcreative.pensieve.bookmark.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface BookmarkRepository {
    fun save(bookmark: Bookmark): Bookmark
    fun findById(id: UUID): Bookmark?
    fun findAll(pageable: Pageable): Page<Bookmark>
    fun findAllById(ids: List<UUID>): List<Bookmark>
    fun findAllByCollectionId(collectionId: UUID, pageable: Pageable): Page<Bookmark>
    fun search(query: String, pageable: Pageable): List<Bookmark>
    fun existsByUrl(url: String): Boolean
    fun delete(id: UUID)
}
