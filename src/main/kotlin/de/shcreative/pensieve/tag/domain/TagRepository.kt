package de.shcreative.pensieve.tag.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface TagRepository {
    fun findAll(pageable: Pageable): Page<Tag>
    fun findById(id: UUID): Tag?
    fun findByName(name: String): Tag?
    fun delete(id: UUID)
}
