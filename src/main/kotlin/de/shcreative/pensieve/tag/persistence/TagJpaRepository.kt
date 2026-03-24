package de.shcreative.pensieve.tag.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface TagJpaRepository : JpaRepository<TagEntity, UUID> {

    fun findByName(name: String): TagEntity?

    // Count bookmarks per tag without loading them all (avoids N+1)
    @Query("""
        SELECT t, COUNT(b) as bookmarkCount
        FROM TagEntity t
        LEFT JOIN t.bookmarks b
        GROUP BY t
    """)
    fun findAllWithBookmarkCount(pageable: Pageable): Page<TagEntity>

    @Query("""
        SELECT COUNT(b) FROM BookmarkEntity b JOIN b.tags t WHERE t.id = :tagId
    """)
    fun countBookmarks(@Param("tagId") tagId: UUID): Int
}
