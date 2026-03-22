package de.shcreative.pensieve.collection.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface CollectionRepository {
    fun save(collection: Collection): Collection
    fun findById(id: UUID): Collection?
    fun findAll(pageable: Pageable): Page<Collection>
    fun existsByName(name: String): Boolean
    fun delete(id: UUID)
}