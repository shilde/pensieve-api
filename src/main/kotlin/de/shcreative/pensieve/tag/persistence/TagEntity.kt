package de.shcreative.pensieve.tag.persistence

import de.shcreative.pensieve.bookmark.persistence.BookmarkEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "tags")
class TagEntity(
    @Id val id: UUID = UUID.randomUUID(),
    val name: String,
    @ManyToMany(mappedBy = "tags")
    val bookmarks: MutableSet<BookmarkEntity> = mutableSetOf(),
    val createdAt: Instant = Instant.now()
)