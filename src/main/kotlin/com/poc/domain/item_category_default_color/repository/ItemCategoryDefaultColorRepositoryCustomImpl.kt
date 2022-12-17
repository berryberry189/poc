package com.poc.domain.item_category_default_color.repository

import com.poc.domain.item_category.QItemCategory.itemCategory
import com.poc.domain.item_category_default_color.QItemCategoryDefaultColor.itemCategoryDefaultColor
import com.poc.domain.item_category_default_color.dto.ItemCategoryDefaultColorDto
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.support.PageableExecutionUtils
import org.springframework.stereotype.Repository

@Repository
class ItemCategoryDefaultColorRepositoryCustomImpl (
    val queryFactory: JPAQueryFactory
): ItemCategoryDefaultColorRepositoryCustom{

    override fun getItemCategoryDefaultColorList(pageRequest: PageRequest): Page<ItemCategoryDefaultColorDto> {
        val content = queryFactory
            .select(
                Projections.constructor(
                    ItemCategoryDefaultColorDto::class.java,
                    itemCategoryDefaultColor.id.`as`("itemCategoryDefaultColorId"),
                    itemCategory.id.`as`("itemCategoryId"),
                    itemCategory.name.`as`("itemCategoryName"),
                    itemCategoryDefaultColor.color
            ))
            .from(itemCategoryDefaultColor)
            .innerJoin(itemCategoryDefaultColor.itemCategory, itemCategory)
            .orderBy(itemCategory.id.asc(), itemCategoryDefaultColor.id.desc())
            .offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .fetch()

        val countQuery: JPAQuery<Long> = queryFactory
            .select(itemCategoryDefaultColor.count())
            .from(itemCategoryDefaultColor)
            .innerJoin(itemCategoryDefaultColor.itemCategory, itemCategory);

        return PageableExecutionUtils.getPage(
            content, pageRequest
        ) { countQuery.fetchOne()!! }
    }
}