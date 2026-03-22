package de.shcreative.pensieve.collection.persistence

import de.shcreative.pensieve.bookmark.persistence.BookmarkEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "collections")
class CollectionEntity(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false, unique = true)
    val name: String,

    @Column
    val description: String? = null,

    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY)
    val bookmarks: MutableList<BookmarkEntity> = mutableListOf(),

    @Column(nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(nullable = false)
    val updatedAt: Instant = Instant.now()
)