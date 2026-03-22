package de.shcreative.pensieve.mind

import de.shcreative.pensieve.mind.dto.EmbedRequest
import de.shcreative.pensieve.mind.dto.EmbedResponse
import de.shcreative.pensieve.mind.dto.SearchResponse
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.util.UUID

@Component
class MindClientImpl(
    private val mindWebClient: WebClient
) : MindClient {

    private val logger = LoggerFactory.getLogger(javaClass)

    override suspend fun enqueueEmbedding(
        bookmarkId: UUID,
        url: String
    ): EmbedResponse {
        logger.info("Requesting embedding for bookmark $bookmarkId")

        return mindWebClient
            .post()
            .uri("/api/embed")
            .bodyValue(EmbedRequest(bookmarkId = bookmarkId, url = url))
            .retrieve()
            .bodyToMono<EmbedResponse>()
            .awaitSingle()
    }

    override suspend fun search(
        query: String,
        limit: Int,
        collectionId: UUID?
    ): SearchResponse {
        logger.info("Requesting semantic search: '$query'")

        return mindWebClient
            .get()
            .uri { builder ->
                builder.path("/api/search")
                    .queryParam("q", query)
                    .queryParam("limit", limit)
                    .apply {
                        if (collectionId != null) queryParam("collection_id", collectionId)
                    }
                    .build()
            }
            .retrieve()
            .bodyToMono<SearchResponse>()
            .awaitSingle()
    }

    override suspend fun deleteEmbedding(bookmarkId: UUID) {
        logger.info("Deleting embedding for bookmark $bookmarkId")

        mindWebClient
            .delete()
            .uri("/api/embed/$bookmarkId")
            .retrieve()
            .bodyToMono<Void>()
            .awaitSingle()

    }
}
