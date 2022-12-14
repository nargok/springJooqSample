package com.example.springJooqSample.application.service

import com.example.springJooqSample.presentation.controller.UpdateItemForm
import com.example.springJooqSample.domain.model.Item
import com.example.springJooqSample.domain.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemService(private val repository: ItemRepository) {

    /**
     * Itemの一覧を取得します
     */
    @Transactional
    fun list(): List<Item> = repository.list()

    @Transactional
    fun find(id: Long): Item {
        val item = repository.findById(id)
        requireNotNull(item) {"存在しないitem: $id"}
        return item
    }

    @Transactional
    fun register(name: String) = repository.store(name)

    @Transactional
    fun update(form: UpdateItemForm) {
        val id = form.id
        val name = form.name
        repository.update(id, name)
    }

    @Transactional
    fun delete(id: Long) = repository.delete(id)
}
