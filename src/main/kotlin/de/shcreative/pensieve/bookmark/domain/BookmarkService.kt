package de.shcreative.pensieve.bookmark.domain

import de.shcreative.pensieve.mind.MindClient
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

@Service
class BookmarkService(
    private val bookmarkRepository: BookmarkRepository,
    private val mindClient: MindClient
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    suspend fun create(bookmark: Bookmark): Bookmark {
        val saved = bookmarkRepository.save(bookmark)

        try {
            val embeddingResponse = mindClient.enqueueEmbedding(saved.id, saved.url)
            logger.info("Embedding created: ${embeddingResponse.embeddingId}")
        } catch (e: Exception) {
            logger.warn("Embedding failed for bookmark ${saved.id}: ${e.message}")
        }
        return saved
    }

    fun findById(id: UUID): Bookmark =
        bookmarkRepository.findById(id) ?: throw NoSuchElementException("Bookmark $id not found")

    fun findAll(pageable: Pageable): Page<Bookmark> =
        bookmarkRepository.findAll(pageable)

    fun findAllByCollectionId(collectionId: UUID, pageable: Pageable): Page<Bookmark> =
        bookmarkRepository.findAllByCollectionId(collectionId, pageable)

    fun update(id: UUID, updated: Bookmark): Bookmark {
        val existing = findById(id)
        return bookmarkRepository.save(
            existing.copy(
                url = updated.url,
                title = updated.title,
                description = updated.description,
                updatedAt = Instant.now()
            )
        )
    }

    suspend fun delete(id: UUID) {
        bookmarkRepository.delete(id)
        try {
            mindClient.deleteEmbedding(id)
        } catch (e: Exception) {
            logger.warn("Embedding deletion failed for $id: ${e.message}")
        }
    }
}
