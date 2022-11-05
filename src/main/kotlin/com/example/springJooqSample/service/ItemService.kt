package com.example.springJooqSample.service

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

}
