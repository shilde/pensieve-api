package de.shcreative.pensieve.tag.domain

import java.util.UUID

interface TagRepository {
    fun findOrCreate(name: String): Tag
    fun findById(id: UUID): Tag?
    fun findAll(): List<Tag>
    fun delete(id: UUID)
}
