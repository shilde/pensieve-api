package de.shcreative.pensieve.collection.api

import de.shcreative.pensieve.collection.api.dto.CollectionRequest
import de.shcreative.pensieve.collection.api.dto.CollectionResponse
import de.shcreative.pensieve.collection.domain.CollectionService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/collections")
class CollectionController(
    private val collectionService: CollectionService
) {

    @PostMapping(version = "1.0")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CollectionRequest): CollectionResponse =
        collectionService.create(request.toDomain()).toResponse()

    @GetMapping(version = "1.0")
    fun findAll(pageable: Pageable): Page<CollectionResponse> =
        collectionService.findAll(pageable).map { it.toResponse() }

    @GetMapping("/{id}", version = "1.0")
    fun findById(@PathVariable id: UUID): CollectionResponse =
        collectionService.findById(id).toResponse()

    @PutMapping("/{id}", version = "1.0")
    fun update(@PathVariable id: UUID, @Valid @RequestBody request: CollectionRequest): CollectionResponse =
        collectionService.update(id, request.toDomain()).toResponse()

    @DeleteMapping("/{id}", version = "1.0")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) =
        collectionService.delete(id)
}
