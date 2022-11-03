package com.example.springJooqSample.controller

import com.example.springJooqSample.service.ItemService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/items")
class ItemController(private val itemService: ItemService) {

    @GetMapping
    fun list() : org.jooq.Result<org.jooq.Record> {
        return itemService.list()
    }

}