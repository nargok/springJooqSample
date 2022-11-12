package com.example.springJooqSample.controller

import com.example.springJooqSample.domain.model.Item
import com.example.springJooqSample.service.ItemService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/items")
class ItemController(private val itemService: ItemService) {

    @GetMapping
    fun list(): List<Item> {
        return itemService.list()
    }

    @GetMapping("/{id:[0-9]+}")
    fun retrieve(@PathVariable("id") id: Long): GetItemResponse {
        val item = itemService.find(id)
        return GetItemResponse(item)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody request: CreateItemForm) {
        return itemService.register(request.name)
    }

    @PostMapping("/update")
    fun update(@RequestBody request: UpdateItemForm) {
        return itemService.update(request)
    }

}

data class CreateItemForm(
    val name: String
)

data class GetItemResponse(
    val id: Long,
    val name: String,
) {
    constructor(model: Item) : this(
        model.id,
        model.name,
    )
}

data class UpdateItemForm(
    val id: Long,
    val name: String
)