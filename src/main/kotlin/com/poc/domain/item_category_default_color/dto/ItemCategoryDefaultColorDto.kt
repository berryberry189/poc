package com.poc.domain.item_category_default_color.dto

import com.poc.domain.item_category_default_color.ItemCategoryDefaultColor

data class ItemCategoryDefaultColorDto(
    val itemCategoryDefaultColorId: Long? = null,
    val itemCategoryId: Long? = null,
    val itemCategoryName: String,
    val color: String
) {

    companion object {
        fun fromEntity(itemCategoryDefaultColor: ItemCategoryDefaultColor) : ItemCategoryDefaultColorDto {
            return itemCategoryDefaultColor.run {
                ItemCategoryDefaultColorDto(
                    itemCategoryDefaultColorId = id,
                    itemCategoryId = itemCategory.id,
                    itemCategoryName = itemCategory.name,
                    color = color
                    )
            }
        }
    }
}