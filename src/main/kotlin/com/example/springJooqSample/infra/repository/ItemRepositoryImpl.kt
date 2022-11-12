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

    // https://github.com/nargok/book-manager-backend-kotlin/blob/master/src/main/kotlin/com/example/bookmanager/infrastructure/repository/BookRepositoryImpl.kt
    /**
     * 指定のItemを取得します
     */
    override fun find(id: Long): Item? {
        return dslContext
            .select()
            .from(ITEM)
            .where(ITEM.ID.eq(id))
            .limit(1)
            .fetch()
            .into(ItemRecord::class.java)
            .firstOrNull()?.let { toModel(it) }

    }

    // https://github.com/bastman/spring-kotlin-jooq/blob/master/rest-api/src/main/kotlin/com/example/api/tweeter/domain/TweeterRepo.kt
    override fun store(name: String) {
        val itemRecord = ItemRecord(name = name)
        dslContext.insertInto(ITEM).set(itemRecord).execute()
    }

    private fun toModel(record: ItemRecord): Item {
        return Item(record.id!!, record.name!!)
    }

}