package de.shcreative.pensieve.tag.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TagJpaRepository : JpaRepository<TagEntity, UUID> {
    fun findByName(name: String): TagEntity?
}
