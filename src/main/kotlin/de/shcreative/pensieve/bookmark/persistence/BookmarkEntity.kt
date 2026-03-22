package de.shcreative.pensieve.bookmark.persistence

import de.shcreative.pensieve.collection.persistence.CollectionEntity
import de.shcreative.pensieve.tag.persistence.TagEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "bookmarks")
class BookmarkEntity(
    @Id val id: UUID = UUID.randomUUID(),
    val url: String,
    val title: String,
    val description: String? = null,
    val content: String? = null,
    val embeddingId: UUID? = null,
    @ManyToMany
    @JoinTable(
        name = "bookmark_tags",
        joinColumns = [JoinColumn(name = "bookmark_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    val tags: MutableSet<TagEntity> = mutableSetOf(),
    @ManyToOne
    @JoinColumn(name = "collection_id")
    val collection: CollectionEntity? = null,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)
