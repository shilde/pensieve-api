package de.shcreative.pensieve.bookmark.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface BookmarkJpaRepository : JpaRepository<BookmarkEntity, UUID> {

    // Alle Bookmarks einer Collection
    fun findAllByCollectionId(collectionId: UUID, pageable: Pageable): Page<BookmarkEntity>

    // Alle Bookmarks eines Tags
    @Query("SELECT b FROM BookmarkEntity b JOIN b.tags t WHERE t.id = :tagId")
    fun findAllByTagId(@Param("tagId") tagId: UUID, pageable: Pageable): Page<BookmarkEntity>

    // Alle Bookmarks anhand einer Liste von IDs (für Suchergebnisse aus pensieve-mind)
    @Query("SELECT b FROM BookmarkEntity b WHERE b.id IN :ids")
    fun findAllByIdIn(@Param("ids") ids: List<UUID>): List<BookmarkEntity>

    // Existiert bereits ein Bookmark mit dieser URL?
    fun existsByUrl(url: String): Boolean

    // Volltextsuche auf Titel und Beschreibung (Fallback ohne Embeddings)
    @Query("""
        SELECT b FROM BookmarkEntity b 
        WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(b.description) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    fun findAllByTitleOrDescriptionContaining(
        @Param("query") query: String,
        pageable: Pageable
    ): Page<BookmarkEntity>
}