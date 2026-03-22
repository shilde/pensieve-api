package de.shcreative.pensieve.tag.domain

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TagService(
    private val tagRepository: TagRepository
) {
    fun findAll(): List<Tag> =
        tagRepository.findAll()

    fun findById(id: UUID): Tag =
        tagRepository.findById(id) ?: throw NoSuchElementException("Tag $id not found")

    fun findOrCreate(name: String): Tag =
        tagRepository.findOrCreate(name)

    fun delete(id: UUID) {
        findById(id)
        tagRepository.delete(id)
    }
}
