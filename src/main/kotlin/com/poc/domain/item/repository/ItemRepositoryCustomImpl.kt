package com.poc.domain.item.repository

import com.poc.domain.item.QItem.item
import com.poc.domain.item.dto.ItemProjectionDto
import com.poc.domain.item_category.QItemCategory
import com.poc.domain.item_category.QItemCategory.itemCategory
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
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

    val parentItemCategory = QItemCategory("parentItemCategory")

    override
    fun getItemList(itemCategoryId: Long?, pageRequest: PageRequest): Page<ItemProjectionDto> {
        val content = queryFactory
            .select(
                Projections.constructor(
                    ItemProjectionDto::class.java,
                    item.id.`as`("itemId"),
                    itemCategory.id.`as`("itemCategoryId"),
                    itemCategory.name.`as`("itemCategoryName"),
                    itemCategory.zIndex,
                    parentItemCategory.id.`as`("parentItemCategoryId"),
                    parentItemCategory.name.`as`("parentItemCategoryName"),
                    item.originName,
                    item.fileUrl,
                    item.createdAt,
                    item.updatedAt
            ))
            .from(item)
            .innerJoin(item.itemCategory, itemCategory)
            .leftJoin(itemCategory.parentItemCategory, parentItemCategory)
            .where(searchItemCategory(itemCategoryId))
            .orderBy(item.id.desc())
            .offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .fetch()

        val countQuery: JPAQuery<Long> = queryFactory
            .select(item.count())
            .from(item)
            .where(searchItemCategory(itemCategoryId))

        return PageableExecutionUtils.getPage(
            content, pageRequest
        ) { countQuery.fetchOne()!! }
    }

    fun searchItemCategory(itemCategoryId: Long?) : BooleanBuilder {
        val builder = BooleanBuilder()

        itemCategoryId?.let { builder.or(itemCategory.id.eq(it)) }
        itemCategoryId?.let { builder.or(parentItemCategory.id.eq(it)) }

        return builder
    }


}