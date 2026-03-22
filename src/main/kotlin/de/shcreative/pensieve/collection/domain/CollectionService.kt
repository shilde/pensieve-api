package de.shcreative.pensieve.collection.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

@Service
class CollectionService(
    private val collectionRepository: CollectionRepository
) {
    fun create(collection: Collection): Collection {
        if (collectionRepository.existsByName(collection.name)) {
            throw IllegalArgumentException("Collection with name '${collection.name}' already exists")
        }
        return collectionRepository.save(collection)
    }

    fun findById(id: UUID): Collection =
        collectionRepository.findById(id)
            ?: throw NoSuchElementException("Collection $id not found")

    fun findAll(pageable: Pageable): Page<Collection> =
        collectionRepository.findAll(pageable)

    fun update(id: UUID, updated: Collection): Collection {
        val existing = findById(id)
        if (existing.name != updated.name && collectionRepository.existsByName(updated.name)) {
            throw IllegalArgumentException("Collection with name '${updated.name}' already exists")
        }
        return collectionRepository.save(
            existing.copy(
                name = updated.name,
                description = updated.description,
                updatedAt = Instant.now()
            )
        )
    }

    fun delete(id: UUID) {
        findById(id) // throws if not found
        collectionRepository.delete(id)
    }
}