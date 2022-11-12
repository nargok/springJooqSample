package com.example.springJooqSample.domain.repository

import com.example.springJooqSample.domain.model.Item

interface ItemRepository {

    /**
     * Itemの一覧を取得します
     */
    fun list(): List<Item>

    fun findById(id: Long): Item

    fun store(name: String)

    fun update(id: Long, name: String)

    fun delete(id: Long)
}