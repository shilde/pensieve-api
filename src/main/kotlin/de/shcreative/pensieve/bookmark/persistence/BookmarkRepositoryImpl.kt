package de.shcreative.pensieve.bookmark.persistence

import de.shcreative.pensieve.bookmark.domain.Bookmark
import de.shcreative.pensieve.bookmark.domain.BookmarkRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class BookmarkRepositoryImpl(
    private val jpa: BookmarkJpaRepository
) : BookmarkRepository {

    override fun save(bookmark: Bookmark): Bookmark =
        jpa.save(bookmark.toEntity()).toDomain()

    override fun findById(id: UUID): Bookmark? =
        jpa.findById(id).orElse(null)?.toDomain()

    override fun findAll(pageable: Pageable): Page<Bookmark> =
        jpa.findAll(pageable).map { it.toDomain() }

    override fun findAllById(ids: List<UUID>): List<Bookmark> =
        jpa.findAllByIdIn(ids).map { it.toDomain() }

    override fun findAllByCollectionId(
        collectionId: UUID,
        pageable: Pageable
    ): Page<Bookmark> =
        jpa.findAllByCollectionId(collectionId, pageable).map { it.toDomain() }

    override fun search(query: String, pageable: Pageable): List<Bookmark> =
        jpa.findAllByTitleOrDescriptionContaining(query, pageable).content.map { it.toDomain() }

    override fun existsByUrl(url: String): Boolean =
        jpa.existsByUrl(url)

    override fun delete(id: UUID) =
        jpa.deleteById(id)
}
