package de.shcreative.pensieve.tag.api

import de.shcreative.pensieve.tag.api.dto.TagResponse
import de.shcreative.pensieve.tag.domain.TagService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/tags")
class TagController(
    private val tagService: TagService
) {

    @GetMapping
    fun findAll(
        @PageableDefault(size = 50, sort = ["name"]) pageable: Pageable
    ): Page<TagResponse> =
        tagService.findAll(pageable).map { it.toResponse() }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): TagResponse =
        tagService.findById(id).toResponse()

    @GetMapping("/by-name/{name}")
    fun findByName(@PathVariable name: String): TagResponse =
        tagService.findByName(name).toResponse()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) =
        tagService.delete(id)
}
