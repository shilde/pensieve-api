package de.shcreative.pensieve.tag.persistence

import de.shcreative.pensieve.tag.domain.Tag
import de.shcreative.pensieve.tag.domain.TagRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class TagRepositoryImpl(
    private val jpa: TagJpaRepository
) : TagRepository {

    override fun findAll(pageable: Pageable): Page<Tag> =
        jpa.findAll(pageable).map { entity ->
            entity.toDomain(bookmarkCount = jpa.countBookmarks(entity.id))
        }

    override fun findById(id: UUID): Tag? =
        jpa.findById(id).orElse(null)?.let { entity ->
            entity.toDomain(bookmarkCount = jpa.countBookmarks(entity.id))
        }

    override fun findByName(name: String): Tag? =
        jpa.findByName(name)?.let { entity ->
            entity.toDomain(bookmarkCount = jpa.countBookmarks(entity.id))
        }

    override fun delete(id: UUID) =
        jpa.deleteById(id)
}
