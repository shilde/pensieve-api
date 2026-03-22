package de.shcreative.pensieve.collection.persistence

import de.shcreative.pensieve.collection.domain.Collection
import de.shcreative.pensieve.collection.domain.CollectionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class CollectionRepositoryImpl(
    private val jpa: CollectionJpaRepository
) : CollectionRepository {

    override fun save(collection: Collection): Collection {
        val entity = jpa.save(collection.toEntity())
        val count = jpa.countBookmarks(entity.id)
        return entity.toDomain(bookmarkCount = count)
    }

    override fun findById(id: UUID): Collection? =
        jpa.findById(id).orElse(null)?.let { entity ->
            val count = jpa.countBookmarks(entity.id)
            entity.toDomain(bookmarkCount = count)
        }

    override fun findAll(pageable: Pageable): Page<Collection> =
        jpa.findAll(pageable).map { entity ->
            val count = jpa.countBookmarks(entity.id)
            entity.toDomain(bookmarkCount = count)
        }

    override fun existsByName(name: String): Boolean =
        jpa.existsByName(name)

    override fun delete(id: UUID) =
        jpa.deleteById(id)
}