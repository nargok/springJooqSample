package com.example.springJooqSample.service

import com.example.springJooqSample.infra.jooq.tables.Item
import org.jooq.DSLContext
import org.springframework.stereotype.Service

@Service
class ItemService(private val dslContext: DSLContext) {

    // todo 使い方が違う
    fun list(): org.jooq.Result<org.jooq.Record> {
        return dslContext.select()
            .from(Item.ITEM)
            .fetch()
    }
}