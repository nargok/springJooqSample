package com.example.springJooqSample.infra.repository

import com.example.springJooqSample.domain.model.Item
import com.example.springJooqSample.domain.repository.ItemRepository
import com.example.springJooqSample.infra.jooq.tables.references.ITEM
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryImpl(private val dslContext: DSLContext) : ItemRepository{

    /**
     * Itemの一覧を取得します
     */
    override fun list(): List<Item> {
        return dslContext.select().from(ITEM).fetch().map {
            val id = requireNotNull(it.getValue(ITEM.ID))
            val name = requireNotNull(it.getValue(ITEM.NAME))
            Item(id, name)
        }
    }

}