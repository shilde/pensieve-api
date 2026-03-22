package de.shcreative.pensieve.tag.persistence

import de.shcreative.pensieve.tag.domain.Tag
import de.shcreative.pensieve.tag.domain.TagRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class TagRepositoryImpl(
    private val jpa: TagJpaRepository
) : TagRepository {

    override fun findOrCreate(name: String): Tag =
        jpa.findByName(name)?.toDomain()
            ?: jpa.save(TagEntity(name = name)).toDomain()

    override fun findById(id: UUID): Tag? =
        jpa.findById(id).orElse(null)?.toDomain()

    override fun findAll(): List<Tag> =
        jpa.findAll().map { it.toDomain() }

    override fun delete(id: UUID) =
        jpa.deleteById(id)
}
