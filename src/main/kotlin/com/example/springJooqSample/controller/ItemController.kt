package com.example.springJooqSample.controller

import com.example.springJooqSample.domain.model.Item
import com.example.springJooqSample.service.ItemService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/items")
class ItemController(private val itemService: ItemService) {

    @GetMapping
    fun list() : List<Item> {
        return itemService.list()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody request: CreateItemForm) {
        return itemService.register(request.name)
    }

}

data class CreateItemForm(
    val name: String
)