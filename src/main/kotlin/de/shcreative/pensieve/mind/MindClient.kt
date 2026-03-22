package de.shcreative.pensieve.mind

import de.shcreative.pensieve.mind.dto.EmbedResponse
import de.shcreative.pensieve.mind.dto.SearchResponse
import java.util.UUID

interface MindClient {
    suspend fun enqueueEmbedding(bookmarkId: UUID, url: String): EmbedResponse
    suspend fun search(query: String, limit: Int = 10, collectionId: UUID? = null): SearchResponse
    suspend fun deleteEmbedding(bookmarkId: UUID)
}
