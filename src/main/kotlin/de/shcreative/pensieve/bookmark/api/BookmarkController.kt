package de.shcreative.pensieve.bookmark.api

import de.shcreative.pensieve.bookmark.api.dto.BookmarkRequest
import de.shcreative.pensieve.bookmark.api.dto.BookmarkResponse
import de.shcreative.pensieve.bookmark.domain.BookmarkService
import de.shcreative.pensieve.bookmark.toDomain
import de.shcreative.pensieve.bookmark.toResponse
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/bookmarks")
class BookmarkController(
    private val bookmarkService: BookmarkService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun create(@Valid @RequestBody request: BookmarkRequest): BookmarkResponse =
        bookmarkService.create(request.toDomain()).toResponse()

    @GetMapping
    fun findAll(
        @PageableDefault(size = 20, sort = ["createdAt"]) pageable: Pageable,
        @RequestParam(required = false) collectionId: UUID?
    ): Page<BookmarkResponse> =
        if (collectionId != null) {
            bookmarkService.findAllByCollectionId(collectionId, pageable).map { it.toResponse() }
        } else {
            bookmarkService.findAll(pageable).map { it.toResponse() }
        }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): BookmarkResponse =
        bookmarkService.findById(id).toResponse()

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @Valid @RequestBody request: BookmarkRequest
    ): BookmarkResponse =
        bookmarkService.update(id, request.toDomain()).toResponse()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun delete(@PathVariable id: UUID) =
        bookmarkService.delete(id)
}
