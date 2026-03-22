package de.shcreative.pensieve.collection.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface CollectionJpaRepository : JpaRepository<CollectionEntity, UUID> {

    fun existsByName(name: String): Boolean

    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<CollectionEntity>

    // Anzahl der Bookmarks pro Collection ohne N+1
    @Query("SELECT COUNT(b) FROM BookmarkEntity b WHERE b.collection.id = :collectionId")
    fun countBookmarks(@Param("collectionId") collectionId: UUID): Int
}
