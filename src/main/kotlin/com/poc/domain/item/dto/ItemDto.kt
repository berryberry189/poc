package com.poc.domain.item.dto

import com.poc.domain.item.Item
import com.poc.domain.item.enum_type.ItemCategory
import java.time.LocalDateTime

data class ItemDto (
    val itemId: Long? = null,
    val itemCategory: ItemCategory,
    val itemCategoryValue: String = itemCategory.value,
    val originName: String,
    val fileUrl: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {


    companion object {
        fun fromEntity(item: Item) : ItemDto {
            return item.run {
                ItemDto(
                    itemId = id,
                    itemCategory = itemCategory,
                    itemCategoryValue = itemCategory.value,
                    originName = originName,
                    fileUrl = fileUrl,
                    createdAt = createdAt,
                    updatedAt = updatedAt)
            }
        }
    }
}