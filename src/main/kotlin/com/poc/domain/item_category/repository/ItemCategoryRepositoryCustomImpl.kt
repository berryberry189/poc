package com.poc.domain.item_category.repository

import com.poc.domain.item_category.QItemCategory
import com.poc.domain.item_category.QItemCategory.itemCategory
import com.poc.domain.item_category.dto.ItemCategoryProjectionDto
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ItemCategoryRepositoryCustomImpl (
    val queryFactory: JPAQueryFactory
) : ItemCategoryRepositoryCustom{


    override fun getItemCategoryList(): List<ItemCategoryProjectionDto> {

        val parentItemCategory = QItemCategory("parentItemCategory")

        return queryFactory
            .select(
                Projections.constructor(
                    ItemCategoryProjectionDto::class.java,
                    itemCategory.id.`as`("itemCategoryId"),
                    itemCategory.name,
                    parentItemCategory.id.`as`("parentItemCategoryId"),
                    parentItemCategory.name.`as`("parentName")
            ))
            .from(itemCategory)
            .leftJoin(itemCategory.parentItemCategory, parentItemCategory)
            .fetch()
    }


}