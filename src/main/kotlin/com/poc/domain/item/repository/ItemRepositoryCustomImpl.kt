package com.poc.domain.item.repository

import com.poc.domain.item.Item
import com.poc.domain.item.QItem.item
import com.poc.domain.item.dto.ItemProjectionDto
import com.poc.domain.item.enum_type.ItemCategory
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.support.PageableExecutionUtils
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryCustomImpl (
    val queryFactory: JPAQueryFactory
) : ItemRepositoryCustom{

    override fun getItemList(itemCategory: ItemCategory?, pageRequest: PageRequest): Page<ItemProjectionDto> {
        val content = queryFactory
            //.select(item)
            .select(
                Projections.constructor(
                    ItemProjectionDto::class.java,
                    item.id.`as`("itemId"),
                    item.itemCategory,
                    item.fileUrl,
                    item.originName,
                    item.createdAt,
                    item.updatedAt
            ))
            .from(item)
            .where(searchItemCategory(itemCategory))
            .offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .fetch()

        val countQuery: JPAQuery<Long> = queryFactory
            .select(item.count())
            .from(item)
            .where(searchItemCategory(itemCategory))

        return PageableExecutionUtils.getPage(
            content, pageRequest
        ) { countQuery.fetchOne()!! }
    }

    fun searchItemCategory(itemCategory: ItemCategory?) : BooleanExpression? {
        return if(itemCategory == null) {
            null
        } else {
            item.itemCategory.eq(itemCategory)
        }
    }


}