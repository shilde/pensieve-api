package de.shcreative.pensieve.tag.api

import de.shcreative.pensieve.tag.api.dto.TagRequest
import de.shcreative.pensieve.tag.api.dto.TagResponse
import de.shcreative.pensieve.tag.domain.TagService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/tags")
class TagController(
    private val tagService: TagService
) {

    @GetMapping(version = "1.0")
    fun findAll(
        @PageableDefault(size = 50, sort = ["name"]) pageable: Pageable
    ): Page<TagResponse> =
        tagService.findAll(pageable).map { it.toResponse() }

    @GetMapping("/{id}", version = "1.0")
    fun findById(@PathVariable id: UUID): TagResponse =
        tagService.findById(id).toResponse()

    @GetMapping("/by-name/{name}", version = "1.0")
    fun findByName(@PathVariable name: String): TagResponse =
        tagService.findByName(name).toResponse()

    @PostMapping(version = "1.0")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: TagRequest): TagResponse =
        tagService.findOrCreate(request.name).toResponse()

    @DeleteMapping("/{id}", version = "1.0")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) =
        tagService.delete(id)
}
