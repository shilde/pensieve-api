package de.shcreative.pensieve.tag.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TagService(
    private val tagRepository: TagRepository
) {
    fun findAll(pageable: Pageable): Page<Tag> =
        tagRepository.findAll(pageable)

    fun findById(id: UUID): Tag =
        tagRepository.findById(id) ?: throw NoSuchElementException("Tag $id not found")

    fun findByName(name: String): Tag =
        tagRepository.findByName(name)
            ?: throw NoSuchElementException("Tag '$name' not found")

    fun delete(id: UUID) {
        findById(id)
        tagRepository.delete(id)
    }
}
