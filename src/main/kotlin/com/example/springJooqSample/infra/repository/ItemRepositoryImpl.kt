package com.example.springJooqSample.infra.repository

import com.example.springJooqSample.domain.model.Item
import com.example.springJooqSample.domain.repository.ItemRepository
import com.example.springJooqSample.infra.jooq.tables.records.ItemRecord
import com.example.springJooqSample.infra.jooq.tables.references.ITEM
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryImpl(private val dslContext: DSLContext) : ItemRepository {

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

    // https://github.com/bastman/spring-kotlin-jooq/blob/master/rest-api/src/main/kotlin/com/example/api/tweeter/domain/TweeterRepo.kt
    override fun store(name: String) {
        val itemRecord = ItemRecord(name = name)
        dslContext.insertInto(ITEM).set(itemRecord).execute()
    }

}